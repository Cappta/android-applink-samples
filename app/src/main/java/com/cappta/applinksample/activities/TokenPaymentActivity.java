package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.cappta.applinksample.R;

public class TokenPaymentActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_payment);

        Button executeOperation = (Button) this.findViewById(R.id.executeCreateTokenPayment);
        executeOperation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SendTokenCreationRequest();
    }

    public void SendTokenCreationRequest() {
        EditText cardToken = (EditText) this.findViewById(R.id.card_token);
        EditText orderId = (EditText) this.findViewById(R.id.order_id);
        EditText customerId = (EditText) this.findViewById(R.id.customer_id);

        EditText paymentAmountInput = (EditText)this.findViewById(R.id.payment_amount);
        float paymentAmount = Float.parseFloat(paymentAmountInput.getText().toString());
        int paymentAmountInCents = Math.round(paymentAmount * 100);

        EditText installments = (EditText)this.findViewById(R.id.installments);

        Uri capptaAppLink = new Uri.Builder()
                .scheme("cappta")
                .authority("token-payment")
                .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                .appendQueryParameter("cardToken", cardToken.getText().toString())
                .appendQueryParameter("orderId", orderId.getText().toString())
                .appendQueryParameter("customerId", customerId.getText().toString())
                .appendQueryParameter("amount", Integer.toString(paymentAmountInCents))
                .appendQueryParameter("installments", installments.getText().toString())
                .appendQueryParameter("scheme", getString(R.string.app_scheme))
                .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}
