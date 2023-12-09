package com.innotice.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NumberUtilsTest {

    @Test
    void isNumeric_whenNullProvided_thenReturnFalse() {
        // given
        String arg = null;

        // when
        boolean result = NumberUtils.isNumeric(arg);

        // then
        assertFalse(result);
    }

    @Test
    void isNumeric_whenNonNumericStringProvided_thenReturnFalse() {
        // given
        String arg = "null";

        // when
        boolean result = NumberUtils.isNumeric(arg);

        // then
        assertFalse(result);
    }

    @Test
    void isNumeric_whenNumericStringProvided_thenReturnTrue() {
        // given
        String arg = "1234567890";

        // when
        boolean result = NumberUtils.isNumeric(arg);

        // then
        assertTrue(result);
    }

  
}