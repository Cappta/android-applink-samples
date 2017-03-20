package com.cappta.applinksample.model;

public enum PaymentProduct {

    CREDIT("credit", "Crédito"),
    DEBIT("debit", "Débito");

    private String value;

    private String displayName;

    private PaymentProduct(String value, String displayName) {
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
