package com.nikitagru.photostream.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * MVC configure class
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                    .addResourceHandler("img/usersImage/**")
                    .addResourceLocations("file:F:\\JavaProjects\\photostream2\\photostream\\src\\main\\resources\\static\\img\\usersImage\\");
    }
}
