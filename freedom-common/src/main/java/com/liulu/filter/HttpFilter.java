package com.liulu.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 *   （1）、Filter需要在web.xml中配置，依赖于Servlet；
 *   （2）、Interceptor需要在SpringMVC中配置，依赖于框架；
 *   （3）、Filter的执行顺序在Interceptor之前，具体的流程见下图
 *
 * Created by 刘璐 on 2018/9/20.
 */

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入了过滤器");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
