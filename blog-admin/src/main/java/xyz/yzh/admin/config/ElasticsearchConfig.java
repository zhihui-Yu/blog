package xyz.yzh.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author simple
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "xyz.yzh.admin.repository.elasticsearch")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    // 高版本才能用 ElasticsearchConfiguration
    // refer https://docs.spring.io/spring-data/elasticsearch/docs/5.0.x/reference/html/#elasticsearch.clients.restclient
    @Bean
    @Override
    public ClientConfiguration clientConfiguration() {
//        HttpHeaders compatibilityHeaders = new HttpHeaders();
//        compatibilityHeaders.add("Accept", "application/json");
//        compatibilityHeaders.add("Content-Type", "application/json");

        return ClientConfiguration.builder()
            .connectedTo("localhost:9200")
//            .withProxy("localhost:8080")
//            .withBasicAuth("elastic","123456")
//            .withDefaultHeaders(compatibilityHeaders)    // this variant for imperative code
//            .withHeaders(() -> compatibilityHeaders)     // this variant for reactive code
            .build();
    }

    // refer https://docs.spring.io/spring-data/elasticsearch/docs/5.0.x/reference/html/#elasticsearch.clients.resthighlevelclient
//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//            .connectedTo("localhost:9200")
//            .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        List<Converter> converters= new ArrayList<>();
        converters.add(DateToLocalDateTimeConverter.INSTANCE);
        converters.add(StringToLocalDateTimeConverter.INSTANCE);
        converters.add(LongToLocalDateTimeConverter.INSTANCE);
        return new ElasticsearchCustomConversions(converters);
    }

    @ReadingConverter
    enum LongToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {

        INSTANCE;

        @Override
        public java.time.LocalDateTime convert(Long source) {
            return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

    }
    //格式化后保存结果为String类型
    @ReadingConverter
    enum StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        INSTANCE;

        @Override
        public java.time.LocalDateTime convert(String source) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(source,df);
        }

    }

    @WritingConverter
    enum DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

        INSTANCE;

        @Override
        public LocalDateTime convert(Date date) {
            Instant instant = date.toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

}
