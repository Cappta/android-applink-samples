package com.cappta.applinksample.model;

public enum InstallmentType {

    CREDIT(1, "ADM (Com Juros)"),
    DEBIT(2, "Lojista (Sem Juros)");

    private int value;

    private String displayName;

    private InstallmentType(int value, String displayName) {
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
