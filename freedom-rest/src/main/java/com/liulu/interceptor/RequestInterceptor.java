package com.liulu.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liulu.Enums.RsEnum;
import com.liulu.annotation.LoginCheck;
import com.liulu.common.RsBody;
import com.liulu.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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

        //登陆注解校验
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(LoginCheck.class)) {
            // 使用@LoginCheck注解，则进行登录验证
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return true;
            } else {
                PrintWriter out = response.getWriter();
                RsBody rsBody = new RsBody();
                rsBody.setStatus(RsEnum.LOGIN_EXCEPTION.getStatus());
                rsBody.setMsg(RsEnum.LOGIN_EXCEPTION.getMsg());
                response.setContentType("application/json; charset=utf-8");
                String rsBodyStr = JSONObject.toJSONString(rsBody);
                out.print(rsBodyStr);
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long starTime = (long)request.getAttribute(startTime);
        long time = System.currentTimeMillis() - starTime;
        logger.info("方法执行时间为{}",time);

        super.afterCompletion(request, response, handler, ex);
    }
}
