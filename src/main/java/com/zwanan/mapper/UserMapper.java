package com.zwanan.mapper;

import com.zwanan.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("""
                select * from user where username=#{username}
            """)
    User findByUsername(@Param("username") String username);

    @Insert("""
                insert into user(username, password, email) values(#{username}, #{password}, #{email})
            """)
    int save(@Param("username") String username, @Param("password") String password, @Param("email") String email);

    @Update("""
                update user set password=#{user.password}, gender=#{user.gender}, birthday=#{user.birthday},
                    email=#{user.email},phone=#{user.phone}, address=#{user.address}, personal=#{user.personal}
                where id=#{user.id}
            """)
    int update(@Param("user") User user);

}
