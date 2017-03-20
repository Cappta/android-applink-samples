package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cappta.applinksample.R;
import com.cappta.applinksample.model.PaymentProduct;

public class PaymentActivity extends Activity implements View.OnClickListener {

    private Spinner paymentProductSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button sendPaymentButton = (Button)this.findViewById(R.id.button_send_payment);
        sendPaymentButton.setOnClickListener(this);

        this.paymentProductSpinner = (Spinner)this.findViewById(R.id.payment_product);
        paymentProductSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PaymentProduct.values()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View view) {
        EditText paymentId = (EditText)this.findViewById(R.id.payment_id);

        PaymentProduct paymentProduct = (PaymentProduct)this.paymentProductSpinner.getSelectedItem();

        EditText paymentAmountInput = (EditText)this.findViewById(R.id.payment_amount);
        float paymentAmount = Float.parseFloat(paymentAmountInput.getText().toString());
        int paymentAmountInCents = Math.round(paymentAmount * 100);

        Uri capptaAppLink = new Uri.Builder()
            .scheme("cappta")
            .authority("payment")
            .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
            .appendQueryParameter("paymentId", paymentId.getText().toString())
            .appendQueryParameter("paymentType", paymentProduct.getValue())
            .appendQueryParameter("amount", Integer.toString(paymentAmountInCents))
            .appendQueryParameter("scheme", getString(R.string.app_scheme))
            .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}