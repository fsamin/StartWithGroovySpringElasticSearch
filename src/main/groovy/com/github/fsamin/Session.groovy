package com.github.fsamin

import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.http.SessionRepositoryFilter
import org.springframework.web.filter.DelegatingFilterProxy
import redis.clients.jedis.JedisPoolConfig

import javax.servlet.*

@Configuration
@EnableRedisHttpSession
class SessionConfig {

    @Bean
    @Order(value = 0)
    public FilterRegistrationBean sessionRepositoryFilterRegistration(SessionRepositoryFilter springSessionRepositoryFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy(springSessionRepositoryFilter));
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public JedisPoolConfig poolConfig() {
        return new JedisPoolConfig(maxTotal: 200, maxIdle: 50, maxWaitMillis: 3000, testOnBorrow: true);
    }

    @Bean
    public FilterRegistrationBean filter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new Filter() {
            void init(FilterConfig config){}
            void doFilter(ServletRequest req, ServletResponse res,
                          FilterChain chain) {
                println("SessionId:" + req.getSession(true).getId());
                chain.doFilter(req, res);
            }
            void destroy(){}
        });
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        filterRegBean.setUrlPatterns(urlPatterns);
        return filterRegBean;
    }

}