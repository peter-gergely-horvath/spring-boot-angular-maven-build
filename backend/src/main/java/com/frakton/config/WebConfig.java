package com.frakton.config;

import com.frakton.commands.CommandRoot;
import org.commandmosaic.api.configuration.CommandDispatcherConfiguration;
import org.commandmosaic.api.server.CommandDispatcherServer;
import org.commandmosaic.spring.web.CommandDispatcherRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/index.html");
                    }
                });
    }

    @Bean
    public CommandDispatcherConfiguration springCommandDispatcherConfiguration() {
        return CommandDispatcherConfiguration.builder()
                .rootPackageFromClass(CommandRoot.class)
                .build();
    }

    @Bean(name = "/commands")
    public HttpRequestHandler httpRequestHandler(CommandDispatcherServer commandDispatcherServer) {
        return new CommandDispatcherRequestHandler(commandDispatcherServer);
    }
}
