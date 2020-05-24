package com.cm.web.component.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @ClassName: MyFilter
 * @Description: 自定义过滤器
 * @author: yangzhiyuan
 * @Date: 2020/5/10 21:23
 */
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String uri = request.getRequestURI();
        log.info("MyFilter.doFilter执行中，过滤uri={}", uri);
        if(uri.contains("/index") || uri.contains("/welcome")
                || uri.contains("/online") || uri.contains("/login")
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            wrapper.sendRedirect("/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter.init执行中");
    }

    @Override
    public void destroy() {
        log.info("MyFilter.destroy执行中");
    }
}
