package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cappta.applinksample.R;

public class TokenPaymentReversalActivity extends Activity implements View.OnClickListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_payment_reversal);

        Button executeOperation = (Button)this.findViewById(R.id.executeTokenPaymentReversal);
        executeOperation.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {
        this.SendTokenPaymentReversalRequest();
    }

    public void SendTokenPaymentReversalRequest(){
        EditText tokenPaymentReversalKey = (EditText) this.findViewById(R.id.payment_key);

        Uri capptaAppLink = new Uri.Builder()
                .scheme("cappta")
                .authority("token-payment-reversal")
                .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                .appendQueryParameter("payment_key", tokenPaymentReversalKey.getText().toString())
                .appendQueryParameter("scheme", getString(R.string.app_scheme))
                .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}
