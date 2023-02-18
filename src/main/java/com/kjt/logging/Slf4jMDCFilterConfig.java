package com.kjt.logging;

import lombok.Data;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Slf4jMDCFilterConfig {

    @Bean
    public FilterRegistrationBean<Slf4jMDCFilter> servletRegistrationBean() {
        final FilterRegistrationBean<Slf4jMDCFilter> registrationBean = new FilterRegistrationBean<>();
        final Slf4jMDCFilter slf4jMDCFilter = new Slf4jMDCFilter();
        registrationBean.setFilter(slf4jMDCFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
