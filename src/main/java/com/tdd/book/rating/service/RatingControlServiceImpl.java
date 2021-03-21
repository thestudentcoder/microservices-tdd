package com.tdd.book.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingControlServiceImpl implements RatingControlService{

    @Autowired
    private RestTemplate restTemplate;

    public RatingControlServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean canReadBook(String customerRatingControlLevel, String bookId) {
        HttpEntity<?> requestEntity = new HttpEntity<>(generateHeader());
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://my-third-party.service.com/fetch/book/rating" + bookId,
                HttpMethod.GET,
                requestEntity,
                String.class);

        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            String ratingControlLevel = responseEntity.getBody();
            if (ratingControlLevel != null) {
                return Integer.parseInt(ratingControlLevel) <= Integer.parseInt(customerRatingControlLevel);
            }
        }

        return false;
    }

    private MultiValueMap<String, String> generateHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("content-type", "application/json");
        return headers;
    }
}
