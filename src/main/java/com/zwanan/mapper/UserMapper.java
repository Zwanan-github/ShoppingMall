package com.zwanan.mapper;

import com.zwanan.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("""
                select * from user order by id desc limit #{pageNumber}, #{pageSize}
            """)
    List<User> getUserByPage(@Param("pageNumber") int pageNumber, @Param("pageSize") Integer pageSize);

    @Select("""
                select count(*) from user
            """)
    Integer getTotalPage();

    @Delete("""
                delete from user where id=#{id}
            """)
    int deleteUserById(Integer id);

    // 插入用户所有信息
    @Insert("""
                insert into user(username, email, password, gender, birthday, phone, address, personal)
                VALUES(#{username}, #{email}, #{password}, #{gender}, #{birthday}, #{phone}, #{address}, #{personal})
            """)
    int saveUser(User user);

    // 修改
    @Update("""
                update user set username=#{username}, email=#{email}, password=#{password}, gender=#{gender},
                birthday=#{birthday}, phone=#{phone}, address=#{address}, personal=#{personal} where id=#{id}
            """)
    int modifyUser(User user);
}
