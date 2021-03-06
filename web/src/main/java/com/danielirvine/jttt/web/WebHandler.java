package com.danielirvine.jttt.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

public class WebHandler {

    public static void main(String[] args) throws Exception {

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);

        final WebAppContext context = new WebAppContext();
        context.setResourceBase("target/assets/");
        context.setContextPath("/");
        context.setSessionHandler(new SessionHandler(new HashSessionManager()));


        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        context.addServlet(servletHolder, "/game/*");

        final ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("dirAllowed","false");
        context.addServlet(defaultServlet,"/");
        int port = 8080;
        final Server server = new Server(port);

        server.setHandler(context);

        server.start();
        server.join();
    }

}
