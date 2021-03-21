package com.tdd.book.rating.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TechnicalFailureExceptionTest {

    @Test
    public void shouldThrowTechnicalFailureExceptionWithMessage_whenTechnicalFailureIsThrown() {
        assertThatThrownBy(() -> {
            throw new TechnicalFailureException("System error");
        }).isInstanceOf(TechnicalFailureException.class)
                .hasMessageContaining("System error");
    }

}