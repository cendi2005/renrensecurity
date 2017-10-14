package io.renren.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebMvc
public class Add extends WebMvcConfigurerAdapter{
//    @Override
//    public void addInterceptors(InterceptorRegistry var1){
//        var1.addInterceptor(new SpringMVCInterceptor()).addPathPatterns("/sys/*");
//    }
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptorAdapter() {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws Exception {
            System.out.println("interceptor====222");
            return true;
        }
    }).addPathPatterns("/*");
}
}
