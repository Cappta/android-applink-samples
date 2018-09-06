package com.cappta.applinksample.model;

public enum DocumentType {
    CNPJ("company", "CNPJ"),
    CPF("individual", "CPF");

    private String value;
    private String displayName;

    private DocumentType(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
