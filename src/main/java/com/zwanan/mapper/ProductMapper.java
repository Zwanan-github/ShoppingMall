package com.zwanan.mapper;

import com.zwanan.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {


    @Select("""
            select * from product where id=#{id}
            """)
    Product getProductById(Integer id);

    /**
     * 模糊查询，当keyword为“”时，查询所有
     * @param keyword
     * @param pageNumber
     * @param size
     * @return
     */
    @Select("""
            select * from product where category like concat('%',#{keyword},'%') limit #{pageNumber},#{size}
            """)
    List<Product> getProductByPage(@Param("keyword") String keyword, @Param("pageNumber") Integer pageNumber, @Param("size") Integer size);

    @Update("""
            update product set number = number - 1 where id = #{id}
            """)
    int reduceNumber(@Param("id") Integer id);

    @Select("""
            select count(*) from product where category like concat('%',#{keyword},'%')
            """)
    Integer getTotalPage(@Param("keyword") String keyword);
    @Update("""
            update product set number = number + 1 where id = #{id}
            """)
    int addNumber(Integer buy);

    @Select("""
            select category from product group by category
            """)
    List<String> getCategories();

    @Delete("""
            delete from product where id = #{id}
            """)
    int deleteProductById(Integer id);

    @Update("""
            update product set productName=#{productName},pic=#{pic},category=#{category},price=#{price},number=#{number},memo=#{memo} where id=#{id}
            """)
    int modifyProduct(Product product);

    @Insert("""
            insert into product(productName,pic,category,price,number,memo) values(#{productName},#{pic},#{category},#{price},#{number},#{memo})
            """)
    int saveProduct(Product product);
}
