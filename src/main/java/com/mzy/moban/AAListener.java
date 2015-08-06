package com.mzy.moban; /**
 * Created by mengzy on 2015/7/16.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Set;

public class AAListener extends ContextLoaderListener{

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("aaaaa");
        String a=      event.getServletContext().getServerInfo();
        System.out.println(a);



        String logicNodeLogStr = "";
        InetAddress addr = null;
        String ip = "";
        try {
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString();
        } catch (UnknownHostException e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }
        MBeanServer mBeanServer = null;
        ArrayList<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);
        if (mBeanServers.size() > 0) {
            for (MBeanServer _mBeanServer : mBeanServers) {
                mBeanServer = _mBeanServer;
                break;
            }
        }
        if (mBeanServer == null) {
            throw new IllegalStateException("没有发现JVM中关联的MBeanServer.");
        }
        Set<ObjectName> objectNames = null;
        try {

            objectNames = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (objectNames == null || objectNames.size() <= 0) {
            throw new IllegalStateException("没有发现JVM中关联的MBeanServer : "
                    + mBeanServer.getDefaultDomain() + " 中的对象名称.");
        }
        try {
            for (ObjectName objectName : objectNames) {
                String protocol = (String) mBeanServer.getAttribute(objectName, "protocol");
                if (protocol.equals("HTTP/1.1")) {
                    int port = (Integer) mBeanServer.getAttribute(objectName, "port");
                    logicNodeLogStr = ip + ":" + port;
                }
// String scheme = (String) mBeanServer.getAttribute(objectName,
// "scheme");
// int port = (Integer) mBeanServer.getAttribute(objectName,
// "port");

            }
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        System.out.println(logicNodeLogStr);


        super.contextInitialized(event);


        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
    }
}
