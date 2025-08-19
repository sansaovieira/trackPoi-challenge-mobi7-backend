// src/main/java/com/mobi7/trackpoi/config/RedisConfig.java
package com.mobi7.trackpoi.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {
  @Bean public LettuceConnectionFactory redisConnectionFactory() { return new LettuceConnectionFactory(); }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory cf) {
    RedisTemplate<String, Object> t = new RedisTemplate<>();
    t.setConnectionFactory(cf);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer(om);
    t.setKeySerializer(new StringRedisSerializer());
    t.setValueSerializer(json);
    t.setHashKeySerializer(new StringRedisSerializer());
    t.setHashValueSerializer(json);
    t.afterPropertiesSet();
    return t;
  }
}
