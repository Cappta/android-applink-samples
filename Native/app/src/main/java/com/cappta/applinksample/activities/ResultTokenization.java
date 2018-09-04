package com.cappta.applinksample.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cappta.applinksample.R;

public class ResultTokenization extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_tokenization_result);

            Uri appLinkUri = Uri.parse(this.getIntent().getDataString());

            String responseCode = appLinkUri.getQueryParameter("responseCode");
            if (responseCode.equals("0")) {
                this.showTokenPaymentResponse(appLinkUri);

                Button buttonBack = (Button) this.findViewById(R.id.button_back);
                buttonBack.setOnClickListener(this);
            } else {
                String reason = appLinkUri.getQueryParameter("reason");
                Toast.makeText(this, reason, Toast.LENGTH_LONG).show();

                this.finish();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            this.finish();
        }
    }

    private void showTokenPaymentResponse(Uri appLinkUri) {
        TextView paymentKey = (TextView) this.findViewById(R.id.payment_key);
        paymentKey.setText(appLinkUri.getQueryParameter("paymentKey"));

        TextView acquirerName = (TextView) this.findViewById(R.id.acquirer_name);
        acquirerName.setText(appLinkUri.getQueryParameter("acquirerName"));

        TextView cardBrandName = (TextView) this.findViewById(R.id.card_brand_name);
        cardBrandName.setText(appLinkUri.getQueryParameter("cardBrandName"));

        TextView acquirerUniqueSequentialNumber = (TextView) this.findViewById(R.id.acquirer_unique_sequential_number);
        acquirerUniqueSequentialNumber.setText(appLinkUri.getQueryParameter("acquirerUniqueSequentialNumber"));

        TextView status = (TextView) this.findViewById(R.id.status);
        status.setText(appLinkUri.getQueryParameter("status"));

        TextView amount = (TextView) this.findViewById(R.id.amount);
        amount.setText(appLinkUri.getQueryParameter("amount"));
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
