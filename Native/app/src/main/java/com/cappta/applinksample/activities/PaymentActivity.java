package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cappta.applinksample.R;
import com.cappta.applinksample.model.PaymentType;

public class PaymentActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button sendCreditPaymentButton = (Button)this.findViewById(R.id.button_send_credit_payment);
        sendCreditPaymentButton.setOnClickListener(this);

        Button sendDebitPaymentButton = (Button)this.findViewById(R.id.button_send_debit_payment);
        sendDebitPaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.button_send_credit_payment:
                SendPayment(PaymentType.CREDIT);
                break;
            case R.id.button_send_debit_payment:
                SendPayment(PaymentType.DEBIT);
                break;
        }
    }

    private void SendPayment(PaymentType paymentType) {
        EditText paymentId = (EditText)this.findViewById(R.id.payment_id);

        EditText paymentAmountInput = (EditText)this.findViewById(R.id.payment_amount);
        float paymentAmount = Float.parseFloat(paymentAmountInput.getText().toString());
        int paymentAmountInCents = Math.round(paymentAmount * 100);

        EditText installments = (EditText)this.findViewById(R.id.installments);

        Uri capptaAppLink = new Uri.Builder()
            .scheme("cappta")
            .authority("payment")
            .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
            .appendQueryParameter("paymentId", paymentId.getText().toString())
            .appendQueryParameter("amount", Integer.toString(paymentAmountInCents))
            .appendQueryParameter("paymentType", paymentType.getValue())
            .appendQueryParameter("installments", installments.getText().toString())
            .appendQueryParameter("scheme", getString(R.string.app_scheme))
            .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}