package com.arm.api.hero.util;

public class Constant {
    public static final String BEAN_ID_DOZER_MAPPER = "HERO_DOZER_MAPPER";
    public static final String LAYER_CONTROLLER = "Controller";

    public enum AppEnum {
        MSG_SUCCESS("Success"),
        LOGGER_FIELD_ELAPSED_MS("ElapsedMs"),
        LOGGER_FIELD_OPERATION("Operation"),
        LOGGER_HTTP_CODE("HttpCode"),
        LOGGER_RESULT("Result");

        private String value;

        private AppEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
