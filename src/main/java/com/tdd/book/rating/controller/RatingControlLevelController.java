package com.tdd.book.rating.controller;

import com.tdd.book.rating.service.RatingControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingControlLevelController {

    private static final String ALPHABETA_REGEX = "[a-zA-Z]+";
    private static final String NUMBER_REGEX = "[0-9]*";
    private static final String SPECIAL_CHAR_REGEX = "[a-zA-Z0-9 ]*";

    @Autowired
    private RatingControlService ratingControlService;

    @GetMapping("/rcl/book/v1/read/eligibility/{control_level}/{book_id}")
    public ResponseEntity<Boolean> getControlAccess(@PathVariable("control_level") String control_level,
                                                    @PathVariable("book_id") String bookId) {

        if (!isValidControllerLevel(control_level) || containsSpecialCharactrs(bookId)) {
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));
        }

        boolean canRead = ratingControlService.canReadBook(control_level, bookId);

        return new ResponseEntity<>(canRead, HttpStatus.OK);
    }

    private boolean containsSpecialCharactrs(String bookId) {
        return !bookId.matches(SPECIAL_CHAR_REGEX);
    }

    private boolean isValidControllerLevel(String control_level) {
        return control_level.matches(ALPHABETA_REGEX) || control_level.matches(NUMBER_REGEX);
    }
}
