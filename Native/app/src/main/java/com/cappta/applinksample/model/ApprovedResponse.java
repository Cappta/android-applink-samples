package com.cappta.applinksample.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApprovedResponse {

    public static final String ACQUIRER_AFFILIATION_KEY = "acquirerAffilizationKey";

    public static final String ACQUIRER_AUTHORIZATION_CODE = "acquirerAuthorizationCode";

    public static final String AUTHORIZATION_DATETIME = "authorizationDateTime";

    public static final String ACQUIRER_NAME = "acquirerName";

    public static final String ACQUIRER_UNIQUE_SEQUENTIAL_NUMBER = "acquirerUniqueSequentialNumber";

    public static final String ADMINISTRATIVE_CODE = "administrativeCode";

    public static final String CARD_BRAND_NAME = "cardBrandName";

    public static final String CUSTOMER_CARD_PAN = "customerCardPan";

    public static final String INSTALLMENT_MODEL = "installmentModel";

    public static final String INSTALLMENTS = "installments";

    public static final String PAYMENT_PRODUCT_NAME = "paymentProductName";

    public static final String UNIQUE_SEQUENTIAL_NUMBER = "uniqueSequentialNumber";

    public static final String CUSTOMER_RECEIPT = "customerReceipt";

    public static final String MERCHANT_RECEIPT = "merchantReceipt";

    public ApprovedResponse(String jsonString) throws JSONException, ParseException {

        JSONObject json = new JSONObject(jsonString);

        if (json.isNull(PAYMENT_PRODUCT_NAME) == false) {
            this.paymentProductName = json.getString(PAYMENT_PRODUCT_NAME);
        }

        if (json.isNull(INSTALLMENTS) == false) {
            this.installments = json.getInt(INSTALLMENTS);
        }

        if (json.isNull(ACQUIRER_AFFILIATION_KEY) == false) {
            this.acquirerAffiliationKey = json.getString(ACQUIRER_AFFILIATION_KEY);
        }

        if (json.isNull(ACQUIRER_NAME) == false) {
            this.acquirerName = json.getString(ACQUIRER_NAME);
        }

        if (json.isNull(CARD_BRAND_NAME) == false) {
            this.cardBrandName = json.getString(CARD_BRAND_NAME);
        }

        if (json.isNull(ACQUIRER_AUTHORIZATION_CODE) == false) {
            this.acquirerAuthorizationCode = json.getString(ACQUIRER_AUTHORIZATION_CODE);
        }

        if (json.isNull(UNIQUE_SEQUENTIAL_NUMBER) == false) {
            this.uniqueSequentialNumber = json.getString(UNIQUE_SEQUENTIAL_NUMBER);
        }

        if (json.isNull(ACQUIRER_UNIQUE_SEQUENTIAL_NUMBER) == false) {
            this.acquirerUniqueSequentialNumber = json.getString(ACQUIRER_UNIQUE_SEQUENTIAL_NUMBER);
        }

        if (json.isNull(ADMINISTRATIVE_CODE) == false) {
            this.administrativeCode = json.getString(ADMINISTRATIVE_CODE);
        }

        if (json.isNull(INSTALLMENT_MODEL) == false) {
            this.installmentModel = json.getInt(INSTALLMENT_MODEL);
        }

        if (json.isNull(CUSTOMER_CARD_PAN) == false) {
            this.customerCardPan = json.getString(CUSTOMER_CARD_PAN);
        }

        if (json.isNull(AUTHORIZATION_DATETIME) == false) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            this.authorizationDateTime = dateFormat.parse(json.getString(AUTHORIZATION_DATETIME));
        }

        JSONObject receipt = json.getJSONObject("receipt");

        if (receipt.isNull(CUSTOMER_RECEIPT) == false) {
            this.customerReceipt = receipt.getString(CUSTOMER_RECEIPT);
        }

        if (receipt.isNull(MERCHANT_RECEIPT) == false) {
            this.merchantReceipt = receipt.getString(MERCHANT_RECEIPT);
        }
    }

    private int installments;

    private int installmentModel;

    private String acquirerAffiliationKey;

    private String acquirerName;

    private String cardBrandName;

    private String acquirerAuthorizationCode;

    private String uniqueSequentialNumber;

    private String acquirerUniqueSequentialNumber;

    private String administrativeCode;

    private String paymentProductName;

    private String customerCardPan;

    private String merchantReceipt;

    private String customerReceipt;

    private Date authorizationDateTime;

    public String getPaymentProductName() {
        return paymentProductName;
    }

    public int getInstallments() {
        return installments;
    }

    public String getAcquirerAffiliationKey() {
        return acquirerAffiliationKey;
    }

    public String getAcquirerName() {
        return acquirerName;
    }

    public String getCardBrandName() {
        return cardBrandName;
    }

    public String getAcquirerAuthorizationCode() {
        return acquirerAuthorizationCode;
    }

    public String getUniqueSequentialNumber() {
        return uniqueSequentialNumber;
    }

    public String getAcquirerUniqueSequentialNumber() {
        return acquirerUniqueSequentialNumber;
    }

    public Date getAuthorizationDateTime() {
        return authorizationDateTime;
    }

    public String getAdministrativeCode() {
        return administrativeCode;
    }

    public String getCustomerCardPan() { return customerCardPan; }

    public String getMerchantReceipt() {
        return merchantReceipt;
    }

    public String getCustomerReceipt() {
        return customerReceipt;
    }
}