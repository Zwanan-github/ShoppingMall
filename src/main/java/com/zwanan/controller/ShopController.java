package com.zwanan.controller;

import com.zwanan.entity.Cart;
import com.zwanan.entity.Order;
import com.zwanan.entity.Product;
import com.zwanan.entity.User;
import com.zwanan.mapper.CartMapper;
import com.zwanan.mapper.ProductMapper;
import com.zwanan.mapper.OrderMapper;
import com.zwanan.utils.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;

    public ShopController(ProductMapper productMapper, OrderMapper orderMapper, CartMapper cartMapper) {
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
        this.cartMapper = cartMapper;
    }

    private static final Integer PAGE_SIZE = 8;

    /**
     * 商品页面视图
     * @param model
     * @param session
     * @param productPageNumber 分页页码
     * @return
     */
    @GetMapping("/{keyword}/{productPageNumber}")
    public String shop(Model model, HttpSession session,
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
        return "shop/product_list";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(Model model, HttpSession session, @PathVariable("id") Integer id) {
        Product product = productMapper.getProductById(id);
        model.addAttribute("product", product);
        return "shop/detail";
    }


    /**
     * 购买成功跳转订单页
     * 购买失败跳转商品页
     * 加购物车成功跳转购物车页
     * 加购物车失败跳转商品页
     * @param model
     * @param session
     * @param buy
     * @param cart
     * @return
     */
    @GetMapping("/product")
    public String product(Model model, HttpSession session,
                          @RequestParam(required = false) Integer buy,
                          @RequestParam(required = false) Integer cart) {
        String productPageNumber = session.getAttribute("productPageNumber").toString();
        String keyword = session.getAttribute("productKeyword").toString();
        // 预处理刚刚过来的页面
        String productStatus = "业务异常";
        if (Objects.equals(buy, cart)) {
            model.addAttribute("productStatus", productStatus);
            return shop(model, session, Integer.valueOf(productPageNumber), "");
        }
        if (!AuthUtil.isAuthorize(session)) {
            productStatus = "请登录之后再购物!";
            model.addAttribute("loginStatus", productStatus);
            return "user/login";
        }
        Integer uid = ((User) session.getAttribute("curUser")).getId();
        if (!Objects.isNull(buy)) {
            // 购买
            // 数据 -1
            if (0 == productMapper.reduceNumber(buy)) {
                productStatus = "购买失败，请联系管理员查看后台故障!";
                model.addAttribute("productStatus", productStatus);
                return shop(model, session, Integer.valueOf(productPageNumber), keyword);
            }
            // 成功
            if (0 == orderMapper.bugProduct(uid, buy)) {
                // 数据 +1
                productMapper.addNumber(buy);
                productStatus = "购买失败，请联系管理员查看后台故障!";
                model.addAttribute("productStatus", productStatus);
                return shop(model, session, Integer.valueOf(productPageNumber), keyword);
            }
            // 获取刚刚添加的时间
            Order justNowOrder = orderMapper.getJustNowOrder();
            Product product = productMapper.getProductById(buy);
            productStatus = "购买[ " + product.getProductName() + " ]成功, " + justNowOrder.getSubmitTime();
            model.addAttribute("orderStatus", productStatus);
            // 购买成功，跳转订单页
            return order(model, session, 1);
            // 响应
        } else {
            // 加入购物车
            if (0 == cartMapper.cartProduct(uid, cart)) {
                productStatus = "添加失败，请联系管理员查看后台故障!";
                model.addAttribute("productStatus", productStatus);
                return shop(model, session, Integer.valueOf(productPageNumber), keyword);
            }
            // 获取刚刚添加的时间
            Cart justNowCart = cartMapper.getJustNowCart();
            Product product = productMapper.getProductById(cart);
            productStatus = "添加[ " + product.getProductName() + " ]到购物车成功, " + justNowCart.getSubmitTime();
            model.addAttribute("cartStatus", productStatus);
            return cart(model, session, 1);
        }
    }

    /**
     * 购物车页面视图
     * @param model
     * @param session
     * @param cartPageNumber
     * @return
     */
    @GetMapping("/cart/{cartPageNumber}")
    public String cart(Model model, HttpSession session, @PathVariable("cartPageNumber") Integer cartPageNumber) {
        String cartStatus;
        if (!AuthUtil.isAuthorize(session)) {
            cartStatus = "请登录之后再查看购物车!";
            model.addAttribute("loginStatus", cartStatus);
            return "user/login";
        }
        Integer uid = ((User) session.getAttribute("curUser")).getId();
        // 页数不合法直接返回
        cartPageNumber = Objects.isNull(cartPageNumber) ? 1 : cartPageNumber;
        List<Cart> cartByPage = cartMapper.getCartByPage(uid, (cartPageNumber - 1) * PAGE_SIZE, PAGE_SIZE);
        // 获取总页数
        Integer totalCartPage = cartMapper.getTotalPage(uid);
        totalCartPage = totalCartPage % PAGE_SIZE == 0 ?
                totalCartPage / PAGE_SIZE : totalCartPage / PAGE_SIZE + 1;
        // 保存总页码
        session.setAttribute("totalCartPage", totalCartPage);
        session.setAttribute("cartPageNumber", cartPageNumber);
        model.addAttribute("carts", cartByPage);
        return "shop/cart_list";
    }

    /**
     * 购物车页面产品的购买/删除操作
     * @param model
     * @param session
     * @param buy 要购买的购物车id
     * @param delete 要删除的购物车id
     * @return
     */
    @GetMapping("/cart/product")
    public String cartProduct(Model model, HttpSession session,
                              @RequestParam(required = false) Integer buy,
                              @RequestParam(required = false) Integer delete) {
        String cartPageNumber = session.getAttribute("cartPageNumber").toString();
        // 预处理刚刚过来的页面
        String cartStatus = "业务异常";
        if (Objects.equals(buy, delete)) {
            model.addAttribute("cartStatus", cartStatus);
            return cart(model, session, Integer.valueOf(cartPageNumber));
        }
        if (!AuthUtil.isAuthorize(session)) {
            cartStatus = "请登录之后再购物!";
            model.addAttribute("loginStatus", cartStatus);
            return "user/login";
        }
        Integer uid = ((User) session.getAttribute("curUser")).getId();
        // 是购买
        if (!Objects.isNull(buy)) {
            // 查出商品id
            Integer pid = cartMapper.getCartById(buy);
            // 购买
            if (0 == productMapper.reduceNumber(pid)) {
                cartStatus = "购买失败，请联系管理员查看后台故障!";
                model.addAttribute("cartStatus", cartStatus);
                return cart(model, session, Integer.valueOf(cartPageNumber));
            }
            // 响应
            // 买了并且删除了这个购物车
            if (0 != orderMapper.bugProduct(uid, pid) && 0 != cartMapper.deleteCart(buy)) {
                // 获取刚刚添加的时间
                Order justNowOrder = orderMapper.getJustNowOrder();
                Product product = productMapper.getProductById(pid);
                cartStatus = "购买[ " + product.getProductName() + " ]成功, " + justNowOrder.getSubmitTime();
                model.addAttribute("orderStatus", cartStatus);
                // 购买成功，跳转订单页
                return order(model, session, 1);
            } else {
                cartStatus = "购买失败，请联系管理员查看后台故障!";
                model.addAttribute("cartStatus", cartStatus);
                return cart(model, session, Integer.valueOf(cartPageNumber));
            }
        } else {
            // 删除购物车
            if (0 == cartMapper.deleteCart(delete)) {
                cartStatus = "删除失败，请联系管理员查看后台故障!";
                model.addAttribute("cartStatus", cartStatus);
                return cart(model, session, Integer.valueOf(cartPageNumber));
            }
            cartStatus = "删除购物车成功";
            model.addAttribute("cartStatus", cartStatus);
            return cart(model, session, Integer.valueOf(cartPageNumber));
        }
    }

    /**
     * 订单页面视图
     * @param model
     * @param session
     * @param orderPageNumber 分页页码
     * @return
     */
    @GetMapping("/order/{orderPageNumber}")
    public String order(Model model, HttpSession session, @PathVariable("orderPageNumber") Integer orderPageNumber) {
        String orderStatus;
        if (!AuthUtil.isAuthorize(session)) {
            orderStatus = "请登录之后再查看订单!";
            model.addAttribute("loginStatus", orderStatus);
            return "user/login";
        }
        Integer uid = ((User) session.getAttribute("curUser")).getId();
        // 页数不合法直接返回
        orderPageNumber = Objects.isNull(orderPageNumber) ? 1 : orderPageNumber;
        List<Order> orderByPage = orderMapper.getOrderByPage(uid, (orderPageNumber - 1) * PAGE_SIZE, PAGE_SIZE);
        // 获取总页数
        Integer totalOrderPage = orderMapper.getTotalPage(uid);
        totalOrderPage = totalOrderPage % PAGE_SIZE == 0 ?
                totalOrderPage / PAGE_SIZE : totalOrderPage / PAGE_SIZE + 1;
        // 保存总页码
        session.setAttribute("totalOrderPage", totalOrderPage);
        session.setAttribute("orderPageNumber", orderPageNumber);
        model.addAttribute("orders", orderByPage);
        return "shop/order_list";
    }

    /**
     * 订单页面的删除操作
     * @param model
     * @param session
     * @param id 要删除的订单id
     * @return
     */
    @GetMapping("/order/clear")
    public String orderClear(Model model, HttpSession session, @RequestParam(required = false) Integer id) {
        String orderPageNumber = session.getAttribute("orderPageNumber").toString();
        // 预处理刚刚过来的页面
        String orderStatus;
        if (!AuthUtil.isAuthorize(session)) {
            orderStatus = "请登录之后再查看订单!";
            model.addAttribute("loginStatus", orderStatus);
            return "user/login";
        }
        // 删除订单
        if (0 != orderMapper.deleteOrder(id)) {
            orderStatus = "删除订单成功";
        } else {
            orderStatus = "删除失败，请联系管理员查看后台故障!";
        }
        model.addAttribute("orderStatus", orderStatus);
        return order(model, session, Integer.valueOf(orderPageNumber));
    }

}
