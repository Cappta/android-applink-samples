package com.cappta.applinksample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cappta.applinksample.R;
import com.cappta.applinksample.model.DocumentType;
import com.cappta.applinksample.model.InstallmentType;
import com.cappta.applinksample.model.PaymentType;

public class PaymentWithTokenCreationActivity extends Activity implements View.OnClickListener {

    private Spinner installmentTypeSpinner;

    private Spinner documentTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_with_token_creation);

        this.installmentTypeSpinner = (Spinner)this.findViewById(R.id.installment_type);
        installmentTypeSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, InstallmentType.values()));

        this.documentTypeSpinner = (Spinner)this.findViewById(R.id.document_type);
        documentTypeSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DocumentType.values()));

        Button sendCreditPaymentButton = (Button)this.findViewById(R.id.button_send_credit_payment);
        sendCreditPaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_send_credit_payment:
                SendPayment(PaymentType.CREDIT);
                break;
        }
    }

    private void SendPayment(PaymentType paymentType){
        EditText paymentId = (EditText)this.findViewById(R.id.payment_id);

        EditText paymentAmountInput = (EditText)this.findViewById(R.id.payment_amount);
        float paymentAmount = Float.parseFloat(paymentAmountInput.getText().toString());
        int paymentAmountInCents = Math.round(paymentAmount * 100);

        EditText installments = (EditText)this.findViewById(R.id.installments);

        InstallmentType installmentType = (InstallmentType)this.installmentTypeSpinner.getSelectedItem();

        EditText cardholderNameInput = (EditText)this.findViewById(R.id.nameInput);
        String cardholderName = cardholderNameInput.getText().toString();

        EditText cardholderEmailInput = (EditText)this.findViewById(R.id.emailInput);
        String cardholderEmail = cardholderEmailInput.getText().toString();

        EditText cardholderDocumentInput = (EditText)this.findViewById(R.id.documentInput);
        String cardholderDocument = cardholderDocumentInput.getText().toString();

        DocumentType documentType = (DocumentType)this.documentTypeSpinner.getSelectedItem();

        Uri capptaAppLink = new Uri.Builder()
                .scheme("cappta")
                .authority("paymentWithTokenCreation")
                .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                .appendQueryParameter("paymentId", paymentId.getText().toString())
                .appendQueryParameter("amount", Integer.toString(paymentAmountInCents))
                .appendQueryParameter("paymentType", paymentType.getValue())
                .appendQueryParameter("installments", installments.getText().toString())
                .appendQueryParameter("installmentType", Integer.toString(installmentType.getValue()))
                .appendQueryParameter("cardholderName", cardholderName)
                .appendQueryParameter("cardholderEmail", cardholderEmail)
                .appendQueryParameter("cardholderDocument", cardholderDocument)
                .appendQueryParameter("documentType", documentType.toString())
                .appendQueryParameter("scheme", getString(R.string.app_scheme))
                .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent,  0);
    }

}
