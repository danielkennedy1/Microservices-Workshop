package com.johara.product.model;

public class IdentityDTO {
    private String id;

    private Float multiplier;
    public IdentityDTO(String id, Float multiplier) {
        this.id = id;
        this.multiplier = multiplier;
    }

    public String getId() {
        return id;
    }

}
