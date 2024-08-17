

package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	
	
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean(name="dataSource")
	public DriverManagerDataSource getdatasource() {
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/behaviourprediction");
		datasource.setUsername("root");
		datasource.setPassword("ashok");
		return datasource;
	}
	
	
	@Bean(name="template")
	public JdbcTemplate getTemplate() {
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(this.getdatasource());
		return template;
	}
	
	// file upload
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getfileResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
}
