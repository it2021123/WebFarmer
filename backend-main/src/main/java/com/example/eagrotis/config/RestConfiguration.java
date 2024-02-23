package com.example.eagrotis.config;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.Application;
import com.example.eagrotis.entity.Employee;
import com.example.eagrotis.entity.Farmer;
import com.example.eagrotis.entity.User;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Admin.class);
        config.exposeIdsFor(Application.class);
        config.exposeIdsFor(Employee.class);
        config.exposeIdsFor(Farmer.class);
        config.exposeIdsFor(User.class);
    }
}
