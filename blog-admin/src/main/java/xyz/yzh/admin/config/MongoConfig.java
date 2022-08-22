package xyz.yzh.admin.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

/**
 * @author simple
 */
@Configuration
@EnableMongoRepositories(basePackages = {"xyz.yzh.admin.repository.mongo"})
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private MongoProperties properties;

    /**
     * 如果不设置，默认会生成一个_class属性在doc中
     * refer https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.type-mapping
     */
    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory, MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
        MappingMongoConverter mmc = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
        mmc.setTypeMapper(defaultMongoTypeMapper());
        return mmc;
    }

    @Bean
    public MongoTypeMapper defaultMongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    /**
     * 如果使用了config，那properties中的配置不生效，只有手动取设置才生效
     * refer https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.mongo-db-factory-java
     */
    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {

        builder
//            .credential(MongoCredential.createCredential("name", "db", "pwd".toCharArray()))
            .applyToClusterSettings(settings  -> {
                settings.hosts(singletonList(new ServerAddress(properties.getHost(), properties.getPort())));
            });
    }
}
