package com.ruiec.config;

import java.lang.reflect.Method;
import java.time.Duration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 * @author luo_wei<br>
 * @date 2019年5月27日 下午7:07:05
 */
@Configuration
@EnableCaching // //继承CachingConfigurerSupport并重写方法，配合该注解实现spring缓存框架的使用
public class RedisConfig extends CachingConfigurerSupport {

	@Autowired
	RedisConnectionFactory factory;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Bean
	RedisTemplate<String, Object> getRedisTemplate() {
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}

	@Override
	@Bean
	public CacheManager cacheManager() {
		return RedisCacheManager.builder(factory).cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(5))).transactionAware().build();
	}

	/**
	 * 重写缓存key生成策略，可根据自身业务需要进行自己的配置生成条件
	 * @author luo_wei<br>
	 * @date 2019年5月27日 下午7:17:26
	 */
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
	/**
	 * 解决键值乱码问题
	 * @author luo_wei<br>
	 * @date 2019年5月28日 上午8:45:00
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
	    RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setValueSerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    redisTemplate.setHashValueSerializer(stringSerializer);
	    this.redisTemplate = redisTemplate;
	}

}
