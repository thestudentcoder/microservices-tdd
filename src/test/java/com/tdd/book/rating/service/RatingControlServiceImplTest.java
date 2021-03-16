package com.tdd.book.rating.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class RatingControlServiceImplTest {

    private static final String VALID_URL_BOOK_SERVICE = "https://my-third-party.service.com/fetch/book/rating/{book_Id}";
    private static final String CUSTOMER_RATING_LEVEL_CODE_12 = "12";
    private static final String TEST_BOOK_ID = "M1211";
    private static final String BOOK_SERVICE_RATING_LEVEL_CODE_12 = "12";

    @Mock
    private RestTemplate restTemplate;          // mock restTemplate because we are mocking the call to the outside api

    private RatingControlServiceImpl ratingControlService;

    @Before
    public void setUp() throws Exception {
        ratingControlService = new RatingControlServiceImpl(restTemplate);
    }

    @Test
    public void shouldReturnTrue_whenBookCodeLevelReturns12_andCustomerProvidedRatingCodeIs12() throws Exception {
        Mockito.when(restTemplate.exchange(anyString(), any(), any(),
                Mockito.<Class<String>>any())).thenReturn(new ResponseEntity<>(BOOK_SERVICE_RATING_LEVEL_CODE_12,
                HttpStatus.OK));

        assertTrue("Read book eligibility is false",
                ratingControlService.canReadBook(CUSTOMER_RATING_LEVEL_CODE_12, TEST_BOOK_ID));
    }
}