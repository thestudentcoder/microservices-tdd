package com.tdd.book.rating.service;

import org.springframework.stereotype.Service;

@Service
public class RatingControlServiceImpl implements RatingControlService{
    @Override
    public boolean canReadBook(String customerRatingControlLevel, String bookId) {
        return false;
    }
}
