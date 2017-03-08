package com.cappta.applinksample.model;

public enum PaymentProduct {

    CREDIT(1, "Crédito"),
    DEBIT(2, "Débito"),
    PRIVATE(3, "Private"),
    VOUCHER(4, "Voucher"),
    SPLITED_DEBIT(5, "Crediário");

    private int value;

    private String displayName;

    private PaymentProduct(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
