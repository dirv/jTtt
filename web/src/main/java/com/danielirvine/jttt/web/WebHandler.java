package com.danielirvine.jttt.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebHandler {

    public static void main(String[] args) throws Exception {

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);

        final WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");


        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        context.addServlet(servletHolder, "/game/*");

        final ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("dirAllowed","true");
        context.addServlet(defaultServlet,"/");
        int port = 8080;
        final Server server = new Server(port);

        server.setHandler(context);

        server.start();
        server.join();
    }

}
