package com.zwanan.controller;

import com.zwanan.entity.Product;
import com.zwanan.entity.User;
import com.zwanan.mapper.ProductMapper;
import com.zwanan.mapper.UserMapper;
import com.zwanan.utils.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Integer PAGE_SIZE = 8;

    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public AdminController(UserMapper userMapper, ProductMapper productMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping
    public String index() {
        return "admin/index";
    }

    /**
     * 用户后台
     *
     * @return
     */
    @GetMapping("/user/{userPageNumber}")
    public String user(Model model, HttpSession session, @PathVariable("userPageNumber") Integer userPageNumber) {
        if (!AuthUtil.isAuthorize(session)) {
            model.addAttribute("loginStatus", "请登录管理员账号");// 数据回显
            return "admin/index";
        }
        // 分页查询用户列表
        // 页数不合法直接返回
        userPageNumber = Objects.isNull(userPageNumber) ? 1 : userPageNumber;
        List<User> userByPage =
                userMapper.getUserByPage((userPageNumber - 1) * PAGE_SIZE, PAGE_SIZE);
        // 拿出总页数
        Integer totalUserPage = userMapper.getTotalPage();
        totalUserPage = totalUserPage % PAGE_SIZE == 0 ?
                totalUserPage / PAGE_SIZE : totalUserPage / PAGE_SIZE + 1;
        // 保存总页码
        session.setAttribute("totalUserPage", totalUserPage);
        session.setAttribute("userPageNumber", userPageNumber);
        model.addAttribute("users", userByPage);
        return "admin/user";
    }

    @GetMapping("/doLogin")
    public String doLogin(Model model, HttpSession session, String username, String password) {
        User target = userMapper.findByUsername(username);
        // 判断登录成功与否
        if (null == target || !password.equals(target.getPassword())) {
            String loginStatus = "账号或密码有误!";
            model.addAttribute("loginStatus", loginStatus);// 数据回显
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "admin/index";
        }
        if (target.getRole() != 1) {
            String loginStatus = "您不是管理员!";
            model.addAttribute("loginStatus", loginStatus);// 数据回显
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "admin/index";
        }
        session.setAttribute("curUser", target);
        return "user/welcome";
    }

    /**
     * 用户删除修改操作
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/user/operation")
    public String userOperation(Model model, HttpSession session, HttpServletRequest request) {
        if (!AuthUtil.isAuthorize(session)) {
            model.addAttribute("loginStatus", "请登录管理员账号");// 数据回显
            return "admin/index";
        }
        String userPageNumber = session.getAttribute("userPageNumber").toString();
        String method = request.getParameter("method");
        if (Objects.isNull(method)) {
            model.addAttribute("userStatus", "操作异常");
        }
        // 从 req 拿到表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String personal = request.getParameter("personal");
        String address = request.getParameter("address");
        if (Objects.equals(method, "delete")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            if (userMapper.deleteUserById(id) == 1) {
                model.addAttribute("userStatus", "删除成功");
            } else {
                model.addAttribute("userStatus", "删除失败");
            }
        } else if (Objects.equals(method, "modify")){
            Integer id = Integer.valueOf(request.getParameter("id"));
            User user = new User(id, username, email, password, gender, birthday, phone, address, personal, 0);
            // 插入用户
            if (userMapper.modifyUser(user) == 1) {
                model.addAttribute("userStatus", "添加成功");
            } else {
                model.addAttribute("userStatus", "添加失败");
            }
        } else if (Objects.equals(method, "add")) {
            User user = new User(0, username, email, password, gender, birthday, phone, address, personal, 0);
            // 插入用户
            if (userMapper.saveUser(user) == 1) {
                model.addAttribute("userStatus", "添加成功");
            } else {
                model.addAttribute("userStatus", "添加失败");
            }
        }
        return user(model, session, Integer.valueOf(userPageNumber));
    }


    @GetMapping("/product/{keyword}/{productPageNumber}")
    public String product(Model model, HttpSession session,
                          @PathVariable("productPageNumber") Integer productPageNumber,
                          @PathVariable("keyword") String keyword) {
        // 页数不合法直接返回
        productPageNumber = Objects.isNull(productPageNumber) ? 1 : productPageNumber;
        // 获取所有 category
        List<String> categories = productMapper.getCategories();
        List<Product> productByPage =
                productMapper.getProductByPage(
                        Objects.equals(keyword, "all") ? "" : keyword,
                        (productPageNumber - 1) * PAGE_SIZE,
                        PAGE_SIZE
                );
        // 拿出总页数
        Integer totalProductPage = productMapper.getTotalPage(Objects.equals(keyword, "all") ? "" : keyword);
        totalProductPage = totalProductPage % PAGE_SIZE == 0 ?
                totalProductPage / PAGE_SIZE : totalProductPage / PAGE_SIZE + 1;
        // 保存总页码
        session.setAttribute("totalProductPage", totalProductPage);
        session.setAttribute("productPageNumber", productPageNumber);
        session.setAttribute("productKeyword", keyword);
        model.addAttribute("categories", categories);
        model.addAttribute("products", productByPage);
        return "admin/product";
    }


    /**
     * 用户删除修改操作
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/product/operation")
    public String productOperation(Model model, HttpSession session, HttpServletRequest request) {
        if (!AuthUtil.isAuthorize(session)) {
            model.addAttribute("loginStatus", "请登录管理员账号");// 数据回显
            return "admin/index";
        }
        String userPageNumber = session.getAttribute("userPageNumber").toString();
        String keyword = session.getAttribute("productKeyword").toString();
        String method = request.getParameter("method");
        if (Objects.isNull(method)) {
            model.addAttribute("productStatus", "操作异常");
        }
        // 从 req 拿到表单商品数据
        String productName = request.getParameter("productName");
        String pic = request.getParameter("pic");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String number = request.getParameter("number");
        String memo = request.getParameter("memo");
        if (Objects.equals(method, "delete")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            if (productMapper.deleteProductById(id) == 1) {
                model.addAttribute("productStatus", "删除成功");
            } else {
                model.addAttribute("productStatus", "删除失败");
            }
        } else if (Objects.equals(method, "modify")){
            Integer id = Integer.valueOf(request.getParameter("id"));
            Product product = new Product(id, productName, pic, category, new BigDecimal(price), Integer.valueOf(number), memo);
            // 插入用户
            if (productMapper.modifyProduct(product) == 1) {
                model.addAttribute("productStatus", "添加成功");
            } else {
                model.addAttribute("productStatus", "添加失败");
            }
        } else if (Objects.equals(method, "add")) {
            Product product = new Product(0, productName, pic, category, new BigDecimal(price), Integer.valueOf(number), memo);
            // 插入用户
            if (productMapper.saveProduct(product) == 1) {
                model.addAttribute("productStatus", "添加成功");
            } else {
                model.addAttribute("productStatus", "添加失败");
            }
        }
        return product(model, session, Integer.valueOf(userPageNumber), keyword);
    }

}
