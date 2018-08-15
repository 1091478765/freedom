package com.liulu.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 刘璐 on 2018/7/26.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    private static final String startTime = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        logger.info("请求地址是：{}",url);
        request.setAttribute(startTime,System.currentTimeMillis());
        Map map = request.getParameterMap();
        Iterator iterator = map.entrySet().iterator();
        StringBuffer buffer = new StringBuffer("");
        while (iterator.hasNext()){
            Map.Entry entry =(Map.Entry) iterator.next();
            String[] value = (String[])entry.getValue();
            buffer.append(entry.getKey()).append("&").append(value[0]);
        }
        logger.info("请求头参数为{}",buffer.toString());
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        logger.info("请求体参数为{}",responseStrBuilder.toString());
        return true;

        //return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long starTime = (long)request.getAttribute(startTime);
        long time = System.currentTimeMillis() - starTime;
        logger.info("方法执行时间为{}",time);

        super.afterCompletion(request, response, handler, ex);
    }
}
