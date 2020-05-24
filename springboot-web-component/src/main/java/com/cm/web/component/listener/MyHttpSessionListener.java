package com.cm.web.component.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: MyHttpSessionListener
 * @Description: 监听器-监听Servlet Session声明周期
 * @author: yangzhiyuan
 * @Date: 2020/5/10 21:59
 */
@Slf4j
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("创建session");
        //设置Session超时时间，超时时间一到，就会自动销毁
        se.getSession().setMaxInactiveInterval(5);
        online ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("销毁session");
    }
}
