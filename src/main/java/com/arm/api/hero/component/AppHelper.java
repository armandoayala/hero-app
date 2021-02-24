package com.arm.api.hero.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class AppHelper {

    public String objectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapperJson = null;
        try {
            if (obj == null) {
                return null;
            }
            mapperJson = new ObjectMapper();

            return mapperJson.writeValueAsString(obj);
        } catch (Exception ex) {
            throw ex;
        } finally {
            mapperJson = null;
        }

    }
}
