package com.frakton.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.commandmosaic.core.conversion.DefaultTypeConversionService;

import java.util.Map;

public class CustomTypeConversionService extends DefaultTypeConversionService {

    /**
     * Mapper instances are fully thread-safe provided that ALL configuration of the
     * instance occurs before ANY read or write calls.
     */
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <S, T> T convert(S value, Class<T> targetType) {

        if(value instanceof Map) {
            return mapper.convertValue(value, targetType);
        } else {
            return super.convert(value, targetType);
        }
    }
}
