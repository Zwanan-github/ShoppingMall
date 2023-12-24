package com.zwanan.controller;

import com.zwanan.entity.User;
import com.zwanan.mapper.UserMapper;
import com.zwanan.utils.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 跳转登陆页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    /**
     * 跳转注册页面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

    /**
     * 欢迎界面
     * @param session
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession session) {
        // 当前没有登录上，直接跳转登录
        if (!AuthUtil.isAuthorize(session)) {
            String loginStatus = "请登录之后进入系统";
            model.addAttribute("loginStatus", loginStatus);
            return "/user/login";
        }
        return "/user/welcome";
    }

    /**
     *
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/show")
    public String show(Model model, HttpSession session) {
        if (!AuthUtil.isAuthorize(session)) {
            String loginStatus = "请登录之后查看自己的信息!";
            model.addAttribute("loginStatus", loginStatus);
            return "/user/login";
        }
        return "/user/show";
    }


    @GetMapping("/edit")
    public String edit(Model model, HttpSession session) {
        if (!AuthUtil.isAuthorize(session)) {
            String loginStatus = "请登录之后修改自己的信息!";
            model.addAttribute("loginStatus", loginStatus);
            return "/user/login";
        }
        return "user/edit";
    }

    /**
     * 登录操作
     * @param model
     * @param session
     * @param username
     * @param password
     * @return 返回首页
     */
    @GetMapping("/doLogin")
    public String doLogin(Model model, HttpSession session, String username, String password) {
        User target = userMapper.findByUsername(username);
        // 判断登录成功与否
        if (null == target || !password.equals(target.getPassword())) {
            String loginStatus = "账号或密码有误!";
            model.addAttribute("loginStatus", loginStatus);// 数据回显
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "user/login";
        }
        session.setAttribute("curUser", target);
        return "user/welcome";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        // 获取登录的用户的名字
        String logoutName = ((User) session.getAttribute("curUser")).getUsername();
        // 登出并清除 session
        session.removeAttribute("curUser");
        model.addAttribute("logoutName", logoutName);
        return "user/logout";
    }

    /**
     * 注册操作
     * @param model
     * @param request
     * @return 注册页面
     */
    @PostMapping("/doRegister")
    public String doRegister(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        // 数据回显
        model.addAttribute("username", username);
        model.addAttribute("password1", password1);
        model.addAttribute("password2", password2);
        model.addAttribute("email", email);
        // 业务开始
        String registerStatus = "两次密码不一致!";
        if (!Objects.equals(password2, password1)) {
            model.addAttribute("registerStatus", registerStatus);
            return "user/register";
        }
        // 先判断用户是否存在
        User target = userMapper.findByUsername(username);
        // 不存在则注册
        if (null == target) {
            int success = userMapper.save(username, password1, email);
            if (0 == success) {
                registerStatus = "注册失败，请联系管理员查看后台故障!";
                model.addAttribute("registerStatus", registerStatus);
                return "user/register";
            }
        }
        registerStatus = null == target ? "注册成功!" : "该用户名已存在!";
        model.addAttribute("registerStatus", registerStatus);
        return "user/register";
    }

    @PostMapping("/doEdit")
    public String doEdit(Model model, HttpSession session, User user) {
        User curUser = (User)session.getAttribute("curUser");
        user.setId(curUser.getId());
        String editStatus = "密码不能为空";
        // 判断必填密码是否为空
        if (null == user.getPassword()) {
            model.addAttribute("editStatus", editStatus);
            return "user/edit";
        }
        // 更新业务开始
        int success = userMapper.update(user);
        if (0 == success) {
            editStatus = "修改失败，请联系管理员查看后台故障!";
            model.addAttribute("editStatus", editStatus);
            return "user/edit";
        }
        editStatus = "修改成功!";
        model.addAttribute("editStatus", editStatus);
        // 把新用户的数据传给 curUser
        curUser = userMapper.findByUsername(curUser.getUsername());
        session.setAttribute("curUser", curUser);
        return "/user/edit";
    }
}
