package com.zwanan.config;


import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import javax.sql.DataSource;

@MapperScan("com.zwanan.mapper")
@ComponentScans({
        @ComponentScan("com.zwanan.service")
})
@Configuration
@PropertySource("classpath:jdbc.properties")
public class RootConfiguration {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    //开启事务
    @Bean
    public TransactionManager transactionManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
