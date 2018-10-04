package com.kk.thw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:epc.properties"})
public class Epc {

    @Value("${add.as}")
    public static String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Epc{" +
                "value='" + value + '\'' +
                '}';
    }
}
