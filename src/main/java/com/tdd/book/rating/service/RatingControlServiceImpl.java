package com.tdd.book.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return false;
    }
}
