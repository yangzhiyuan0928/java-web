package com.cm.web.component.config;

import com.cm.web.component.filter.MyFilter;
import com.cm.web.component.interceptor.MyInterceptor;
import com.cm.web.component.listener.MyHttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MyWebConfig
 * @Description:
 * @author: yangzhiyuan
 * @Date: 2020/5/10 22:02
 */
@Slf4j
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    /**
     * 新增Controller定义，实现无业务逻辑跳转，直接渲染foo.html或foo.jsp，减少控制器代码编写
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/add/foo").setViewName("foo");
    }

    /**
     * 新增拦截器，拦截请求/welcome/**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/welcome/**");
        log.info("注册拦截器");
    }

    /**
     * Filter注册，过滤所有请求(/*)
     * @return
     */
    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        log.info("注册过滤器，filter={}", frBean.getFilter());
        return frBean;
    }

    /**
     * 监听器注册
     * @return
     */
    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        log.info("注册监听器，listener={}", srb.getListener());
        return srb;
    }
}
