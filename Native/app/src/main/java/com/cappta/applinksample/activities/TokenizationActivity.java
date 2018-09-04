package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.cappta.applinksample.R;

public class TokenizationActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokenization);

        Button buttonPayment = (Button)this.findViewById(R.id.button_payment_with_token_creation);
        buttonPayment.setOnClickListener(this);

        Button buttonPaymentReversal = (Button)this.findViewById(R.id.button_token_creation);
        buttonPaymentReversal.setOnClickListener(this);

        Button buttonTokenPayment = (Button)this.findViewById(R.id.button_token_payment);
        buttonTokenPayment.setOnClickListener(this);

        Button buttonCancelTokenPayment = (Button)this.findViewById(R.id.button_cancel_token_payment);
        buttonCancelTokenPayment.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_token_creation:
                startActivity(TokenCreationActivity.class);
                break;

            case R.id.button_token_payment:
                startActivity(TokenPaymentActivity.class);
        }
    }

    private void startActivity(Class activityClass)
    {
        Intent intent = new Intent(this, activityClass);

        startActivity(intent);
    }
}
