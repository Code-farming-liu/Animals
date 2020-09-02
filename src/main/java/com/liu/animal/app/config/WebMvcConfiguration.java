package com.liu.animal.app.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    //    @Autowired
//    private AuthInterceptor authInterceptor;
    /**
     * @Author Admin
     * @Description 解决跨域
     * @Date 8:38 2020/5/5
     * @param registry
     * @return void
     **/
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600);
//    }
    /**
     * @Author Admin
     * @Description 注入拦截器
     * @Date 8:40 2020/5/5
     * @param null
     * @return
     **/
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/error");
//        super.addInterceptors(registry);
//    }

}
