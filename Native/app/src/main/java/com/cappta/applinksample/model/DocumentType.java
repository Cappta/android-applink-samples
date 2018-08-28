package com.cappta.applinksample.model;

public enum DocumentType {

    documentCPF(1, "CPF"),
    documentCNPJ(2, "CNPJ");

    private int value;

    private String displayName;

    DocumentType(int value, String displayName) {
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
