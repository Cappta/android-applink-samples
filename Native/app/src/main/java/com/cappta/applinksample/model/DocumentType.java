package com.cappta.applinksample.model;

public enum DocumentType {
    CNPJ(1, "CNPJ"),
    CPF(2, "CPF");

    private int value;
    private String displayName;

    private DocumentType(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {return this.value;}

    @Override
    public String toString() {return this.displayName;}
}
