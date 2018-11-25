package com.and.springbootapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by vamshikirangullapelly on 24/11/2018.
 */
public class GlobalUtil {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
