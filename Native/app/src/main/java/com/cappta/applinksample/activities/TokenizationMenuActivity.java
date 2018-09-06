package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.cappta.applinksample.R;

public class TokenizationMenuActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokenization_menu);

        Button buttonCreditPaymentAndCardToken = (Button)this.findViewById(R.id.button_credit_payment_and_create_card_token);
        buttonCreditPaymentAndCardToken.setOnClickListener(this);

        Button buttonCreateCardToken = (Button)this.findViewById(R.id.button_create_card_token);
        buttonCreateCardToken.setOnClickListener(this);

        Button buttonTokenPayment = (Button)this.findViewById(R.id.button_token_payment);
        buttonTokenPayment.setOnClickListener(this);

        Button buttonTokenPaymentReversal = (Button)this.findViewById(R.id.button_token_payment_reversal);
        buttonTokenPaymentReversal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_credit_payment_and_create_card_token:
                startActivity(CreditPaymentAndCreateCardTokenActivity.class);
                break;

            case R.id.button_create_card_token:
                startActivity(CreateCardTokenActivity.class);
                break;

            case R.id.button_token_payment:
                startActivity(TokenPaymentActivity.class);
                break;

            case R.id.button_token_payment_reversal:
                startActivity(TokenPaymentReversalActivity.class);
                break;
        }
    }

    private void startActivity(Class activityClass)
    {
        Intent intent = new Intent(this, activityClass);

        startActivity(intent);
    }
}
