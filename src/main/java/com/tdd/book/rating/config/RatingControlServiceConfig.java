package com.tdd.book.rating.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RatingControlServiceConfig {

    @Value("${api.bookService.endpoint}")
    private String bookServiceEndpoint;
}
