package com.example.aop.AOP.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;


@SpringBootApplication
public class AopProjectApplication {


	/** Shallow ETags adds ETags reference number in response header. This ETags is MD5 hash, ETags runs MD5 digest on response.
	 Client application now take this ETags and add this in request header with if-* header "If-None-Match"
	 when submitting GET request for same resource.
	 Backend will not perform any computation instead return HTTP 304 NOT Modified status with empty response body
	 to the  client if the resource state hasn't changed.
	 */

	@Bean
	public Filter shallowEtagFilter(){
		return new ShallowEtagHeaderFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(AopProjectApplication.class, args);
	}

}
