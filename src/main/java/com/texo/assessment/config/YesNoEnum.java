package com.texo.assessment.config;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum YesNoEnum {

    YES("yes", true),
    NO("no", false);

    private final String value;
    private final boolean bool;

    YesNoEnum(String value, boolean bool) {
        this.value = value;
        this.bool = bool;
    }

    public static boolean strToBoolean(String value) {
        return Arrays.stream(values())
                    .filter(v -> v.getValue().equalsIgnoreCase(value))
                    .findFirst()
                    .orElse(NO)
                    .isBool();
    }

}
