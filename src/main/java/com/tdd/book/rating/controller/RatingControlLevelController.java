package com.tdd.book.rating.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingControlLevelController {

    @GetMapping("/rcl/book/v1/read/eligibility/{control_level}/{book_id}")
    public ResponseEntity<Boolean> getControlAccess(@PathVariable("control_level") String control_level,
                                                    @PathVariable("book_id") String bookId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
