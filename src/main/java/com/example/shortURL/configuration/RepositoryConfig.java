package com.example.shortURL.configuration;

import com.example.shortURL.domain.Url;
import com.example.shortURL.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RepositoryConfig {
    @Bean
    @Profile("collection")
    public DataRepository<T> collectionRepository() {
        return new CollectionDataRepository();
    }

    @Bean
    @Profile("db")
    public Repository<T> dbRepository() {
        return new DbDataRepository();
    }

}
