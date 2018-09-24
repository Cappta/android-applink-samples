package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cappta.applinksample.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPayment = (Button)this.findViewById(R.id.button_payment);
        buttonPayment.setOnClickListener(this);

        Button buttonPaymentReversal = (Button)this.findViewById(R.id.button_payment_reversal);
        buttonPaymentReversal.setOnClickListener(this);

        Button buttonReceiptReprint = (Button)this.findViewById(R.id.button_receipt_reprint);
        buttonReceiptReprint.setOnClickListener(this);

        Button buttonConfiguration = (Button)this.findViewById(R.id.button_configuration);
        buttonConfiguration.setOnClickListener(this);

        Button buttonTokenization = (Button)this.findViewById(R.id.button_tokenization);
        buttonTokenization.setOnClickListener(this);

        Button buttonMulticnpj = (Button)this.findViewById(R.id.button_multicnpj);
        buttonMulticnpj.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_payment:
                startActivity(PaymentActivity.class);
                break;
            case R.id.button_receipt_reprint:
                startActivity(ReceiptReprintActivity.class);
                break;
            case R.id.button_payment_reversal:
                startActivity(PaymentReversalActivity.class);
                break;
            case R.id.button_configuration:
                startActivity(ConfigurationActivity.class);
                break;
            case R.id.button_tokenization:
                startActivity(TokenizationMenuActivity.class);
                break;
            case R.id.button_multicnpj:
                startActivity(MulticnpjActivity.class);
                break;
        }
    }

    private void startActivity(Class activityClass)
    {
        Intent intent = new Intent(this, activityClass);

        startActivity(intent);
    }
}