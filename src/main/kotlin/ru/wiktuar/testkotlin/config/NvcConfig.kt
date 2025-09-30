package ru.wiktuar.testkotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class NvcConfig : WebMvcConfigurer {

    @Value("\${upload.path}")
    private lateinit var uploadPath: String

    override fun addViewControllers(registry: ViewControllerRegistry){
        registry.addViewController("/login").setViewName("index");
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry){
        registry.addResourceHandler("/js/**")
            .addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/");

        registry.addResourceHandler("/img/**")
            .addResourceLocations("classpath:/static/img/");

        registry.addResourceHandler(("/uploads/**"))
            .addResourceLocations("file://$uploadPath/")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PutUserInModelInterceptor())
    }
}