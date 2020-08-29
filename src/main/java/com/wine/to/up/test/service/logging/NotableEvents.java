package com.wine.to.up.test.service.logging;

public enum NotableEvents {
    DEFAULT("Default event"),
    EXCEPTION("Some exception occurred: {}"),
    ;

    private final String template;

    NotableEvents(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
