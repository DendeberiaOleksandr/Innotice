package com.innotice.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class URLUtilsTest {

    @Test
    void isUrl_whenNullProvided_thenReturnFalse() {
        // given
        String arg = null;

        // when
        boolean result = URLUtils.isUrl(arg);

        // then
        assertFalse(result);
    }

    @Test
    void isUrl_whenNonURLStringProvided_thenReturnFalse() {
        // given
        String arg = "null";

        // when
        boolean result = URLUtils.isUrl(arg);

        // then
        assertFalse(result);
    }

    @Test
    void isUrl_whenURLProvided_thenReturnTrue() {
        // given
        String arg = "http://google.com";

        // when
        boolean result = URLUtils.isUrl(arg);

        // then
        assertTrue(result);
    }

}