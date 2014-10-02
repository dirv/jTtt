package com.danielirvine.jttt.web;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.danielirvine.jttt.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/");
      resolver.setSuffix(".jsp");
      return resolver;
    }
}
