package com.cappta.applinksample.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cappta.applinksample.R;

public class ResultPaymentWithTokenCreation extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try
        {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_payment_with_token_creation_result);

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
        TextView cupomLojista = (TextView) this.findViewById(R.id.via_lojista);
        cupomLojista.setText(appLinkUri.getQueryParameter("merchantReceipt"));

        TextView cupomCliente = (TextView) this.findViewById(R.id.via_cliente);
        cupomCliente.setText(appLinkUri.getQueryParameter("customerReceipt"));

        TextView cardToken = (TextView) this.findViewById(R.id.card_token);
        cardToken.setText(appLinkUri.getQueryParameter("card_token"));

        TextView cardLastDigits = (TextView) this.findViewById(R.id.card_last_digits);
        cardLastDigits.setText(appLinkUri.getQueryParameter("customer_card_last_four_digits"));

        TextView cardBrandName = (TextView) this.findViewById(R.id.card_brand_name);
        cardBrandName.setText(appLinkUri.getQueryParameter("card_brand_name"));

        TextView cardMonthExp = (TextView) this.findViewById(R.id.card_month_exp);
        cardMonthExp.setText(appLinkUri.getQueryParameter("card_month_exp"));

        TextView cardYearExp = (TextView) this.findViewById(R.id.card_year_exp);
        cardYearExp.setText(appLinkUri.getQueryParameter("card_year_exp"));

        String customerNameValue = appLinkUri.getQueryParameter("customer_name");
        String customerEmailValue = appLinkUri.getQueryParameter("customer_email");
        String customerDocNumbervalue = appLinkUri.getQueryParameter("customer_doc_number");
        String customerDocTypeValue = appLinkUri.getQueryParameter("customer_doc_type");

        String customerDetails =  customerNameValue
                .concat("\n")
                .concat(customerEmailValue)
                .concat("\n")
                .concat(customerDocNumbervalue)
                .concat("\n")
                .concat(customerDocTypeValue);

        TextView customer = (TextView) this.findViewById(R.id.customer_data);
        customer.setText(customerDetails);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
