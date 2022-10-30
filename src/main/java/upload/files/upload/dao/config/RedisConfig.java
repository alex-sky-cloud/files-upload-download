package upload.files.upload.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.database}")
    private int indexDataBase;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName(this.hostName);
        /*Указываем номер индекса базы, который мы будем использовать,
        для подключения фабрики подключений.*/
        redisStandaloneConfiguration.setDatabase(this.indexDataBase);

        LettuceConnectionFactory lettuceConnectionFactory =
                new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;
    }

    /* RedisTemplate взаимодействует с базой Redis как наш backend,
    по отношению к нашему приложению*/
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        final RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(lettuceConnectionFactory());

        template.setKeySerializer(new GenericToStringSerializer<>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        template.setEnableTransactionSupport(true);

        return template;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate() {

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(lettuceConnectionFactory());

        stringRedisTemplate.setEnableTransactionSupport(true);

        return stringRedisTemplate;
    }


}
