package com.frakton.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.commandmosaic.api.configuration.CommandDispatcherConfiguration;
import org.commandmosaic.api.conversion.TypeConversionService;
import org.commandmosaic.api.server.CommandDispatcherServer;
import org.commandmosaic.security.interceptor.AbstractSecurityCommandInterceptor;
import org.commandmosaic.security.jwt.config.JwtSecurityConfiguration;
import org.commandmosaic.spring.web.CommandDispatcherRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.crypto.SecretKey;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableJpaRepositories(basePackages = "com.frakton.repositories")
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
    public TypeConversionService typeConversionService() {
        return new CustomTypeConversionService();
    }

    @Bean
    public CommandDispatcherConfiguration springCommandDispatcherConfiguration() {
        return CommandDispatcherConfiguration.builder()
                .rootPackage("com.frakton.commands")
                .interceptor(AbstractSecurityCommandInterceptor.class)
                .build();
    }


    @Bean
    public JwtSecurityConfiguration jwtSecurityConfiguration() {
        // TODO: replace with actual key
        byte[] sampleKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        return JwtSecurityConfiguration.builder()
                .setJwtKey(sampleKey)
                .build();
    }

    @Bean(name = "/commands")
    public HttpRequestHandler httpRequestHandler(CommandDispatcherServer commandDispatcherServer) {
        return new CommandDispatcherRequestHandler(commandDispatcherServer);
    }

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.frakton.entities");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
