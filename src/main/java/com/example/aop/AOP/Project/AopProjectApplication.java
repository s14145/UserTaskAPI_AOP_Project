package com.example.aop.AOP.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;


@SpringBootApplication
@EnableCaching
public class AopProjectApplication {


	/**
	 * Shallow ETags adds ETags reference number in response header. This ETags is MD5 hash, ETags runs MD5 digest on response.
	 * Client application now take this ETags and add this in request header with if-* header "If-None-Match"
	 * when submitting GET request for same resource.
	 * Backend will not perform any computation instead return HTTP 304 NOT Modified status with empty response body
	 * to the  client if the resource state hasn't changed.
	 */

	@Bean
	public Filter shallowEtagFilter() {
		return new ShallowEtagHeaderFilter();
	}

	/**
	 * Define cache manager implementation so that Spring will save cached objects in it
	 * For now we will use Spring ConcurrentMapCacheManager which is in-memory cache manager good for non-production env or proof of concept but
	 * in production env we will have to use Guava cache manager as it is faster and better suited.
	 */
	@Bean
	public CacheManager cacheManager() {
		// ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("tasks");
		return new ConcurrentMapCacheManager("tasks");
	}


	/**
	 * Guava Cache Manager
	 *
	 * @Bean public CacheManager cacheManager() {
	 * GuavaCacheManager guavaCacheManager =  new GuavaCacheManager();
	 * guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES));
	 * return guavaCacheManager;
	 * }
	 * <p>
	 * Below is if caches doesn't have similar configuration: book cache to never expire but books cache to expire after 30 mins
	 * @Bean public CacheManager cacheManager() {
	 * SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
	 * GuavaCache cache1 = new GuavaCache("book", CacheBuilder.newBuilder().build());
	 * GuavaCache cache2 = new GuavaCache("books", CacheBuilder.newBuilder()
	 * .expireAfterAccess(30, TimeUnit.MINUTES)
	 * .build());
	 * simpleCacheManager.setCaches(Arrays.asList(cache1, cache2));
	 * return simpleCacheManager;
	 * }
	 */

	public static void main(String[] args) {
		SpringApplication.run(AopProjectApplication.class, args);
	}


}
