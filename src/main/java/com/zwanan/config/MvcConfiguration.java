package com.zwanan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@ComponentScan("com.zwanan.controller")
@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

//    //我们需要使用ThymeleafViewResolver作为视图解析器，并解析我们的HTML页面
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver(@Autowired SpringTemplateEngine springTemplateEngine){
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setOrder(1);
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setTemplateEngine(springTemplateEngine);
//        return resolver;
//    }

//    //配置模板解析器
//    @Bean
//    public SpringResourceTemplateResolver templateResolver(){
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setSuffix(".html");
//        resolver.setPrefix("/WEB-INF/template/");
//        return resolver;
//    }
//
//    //配置模板引擎Bean
//    @Bean
//    public SpringTemplateEngine springTemplateEngine(@Autowired ITemplateResolver resolver){
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(resolver);
//        engine.addDialect(new SpringSecurityDialect());     //添加SpringSecurityDialect方言
//        return engine;
//    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //开启静态资源处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //静态资源路径配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }
}
