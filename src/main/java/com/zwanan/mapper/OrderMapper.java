package com.zwanan.mapper;

import com.zwanan.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("""
            insert into `shopping-mall`.order(uid, pid, submitTime) values(#{uid}, #{pid}, TIMESTAMP(now()))
            """)
    int bugProduct(@Param("uid") Integer uid, @Param("pid") Integer pid);

    @Delete("""
            delete from `order` where id=#{id}
            """)
    int deleteOrder(@Param("id") Integer id);

    @Results(id = "orderList", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "product", column = "pid", one = @One(select = "com.zwanan.mapper.ProductMapper.getProductById")),
            @Result(property = "submitTime", column = "submitTime")
    })
    @Select("""
            select * from `order` where uid=#{uid} order by submitTime desc limit #{pageNumber}, #{size}
            """)
    List<Order> getOrderByPage(@Param("uid") Integer uid, @Param("pageNumber") Integer pageNumber, @Param("size") Integer size);

    @Select("""
            select count(*) from `order` where uid=#{uid}
            """)
    Integer getTotalPage(Integer uid);

    @Select("""
            select * from `order` order by submitTime desc limit 1
            """)
    Order getJustNowOrder();
}
