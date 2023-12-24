package com.zwanan.mapper;

import com.zwanan.entity.Cart;
import com.zwanan.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("""
            insert into cart(uid, pid, submitTime) values(#{uid}, #{pid}, TIMESTAMP(now()))
            """)
    int cartProduct(@Param("uid") Integer uid, @Param("pid") Integer pid);

    // 映射结果集然后查询cart表并把对应商品信息也查出来
    @Results(id = "cartList", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "product", column = "pid", one = @One(select = "com.zwanan.mapper.ProductMapper.getProductById")),
            @Result(property = "submitTime", column = "submitTime")
    })
    @Select("""
            select * from cart where uid=#{uid} order by submitTime desc limit #{pageNumber}, #{size}
            """)
    List<Cart> getCartByPage(@Param("uid") Integer uid, @Param("pageNumber") Integer pageNumber, @Param("size") Integer size);

    @Delete("""
            delete from cart where id=#{id}
            """)
    int deleteCart(@Param("id") Integer id);

    @Select("""
            select pid from cart where id=#{id}
            """)
    Integer getCartById(@Param("id") Integer id);

    @Select("""
            select count(*) from cart where uid=#{uid}
            """)
    Integer getTotalPage(Integer uid);

    // 获取时间最大的购物车记录
    @Select("""
            select * from cart order by submitTime desc limit 1
            """)
    Cart getJustNowCart();
}
