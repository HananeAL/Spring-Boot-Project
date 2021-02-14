package com.project.main.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean<LoginFilter> registerloginFilter() {

    FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new LoginFilter());
    registrationBean.addUrlPatterns("/candidate/*");
    registrationBean.addUrlPatterns("/company/*");
    registrationBean.setOrder(1); // set precedence
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<CompanyFilter> registerCompanyFilter() {

    FilterRegistrationBean<CompanyFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new CompanyFilter());
    registrationBean.addUrlPatterns("/company/*");
    registrationBean.setOrder(2); // set precedence
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<CandidateFilter> registerCandidateFilter() {

    FilterRegistrationBean<CandidateFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new CandidateFilter());
    registrationBean.addUrlPatterns("/candidate/*");
    registrationBean.setOrder(2); // set precedence
    return registrationBean;
  }
}
