package com.remotemode.internship3inventoryproduce.model;

import java.util.Objects;

public enum ProductType {
    CLOTHING("Clothing"),
    FURNITURE("Furniture"),
    COMPUTER("Computer"),
    TOOLS("Tools"),
    NA("NA");

    ProductType(String code) {

    }

    public static ProductType of(String value) {
        if (Objects.nonNull(value)) {
            for (ProductType type : values()) {
                if (type.name().equalsIgnoreCase(value)) {
                    return type;
                }
            }
        }

        return NA;
    }
}
