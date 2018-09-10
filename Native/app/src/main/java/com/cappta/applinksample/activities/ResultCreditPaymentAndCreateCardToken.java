package com.cappta.applinksample.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cappta.applinksample.R;

public class ResultCreditPaymentAndCreateCardToken extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try
        {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_result_credit_payment_and_create_card_token);

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
        cardToken.setText(appLinkUri.getQueryParameter("cardToken"));

        TextView cardBrandName = (TextView) this.findViewById(R.id.card_brand_name);
        cardBrandName.setText(appLinkUri.getQueryParameter("cardBrandName"));

        TextView cardMonthExp = (TextView) this.findViewById(R.id.card_month_exp);
        cardMonthExp.setText(appLinkUri.getQueryParameter("cardMonthExp"));

        TextView cardYearExp = (TextView) this.findViewById(R.id.card_year_exp);
        cardYearExp.setText(appLinkUri.getQueryParameter("cardYearExp"));

        TextView customerId = (TextView) this.findViewById(R.id.customer_id);
        customerId.setText(appLinkUri.getQueryParameter("customerId"));

        TextView customerName = (TextView) this.findViewById(R.id.customer_name);
        customerName.setText(appLinkUri.getQueryParameter("customerName"));

        TextView customerDocumentType = (TextView) this.findViewById(R.id.customer_document_type);
        customerDocumentType.setText(appLinkUri.getQueryParameter("customerDocumentType"));

        TextView customerDocument = (TextView) this.findViewById(R.id.customer_document);
        customerDocument.setText(appLinkUri.getQueryParameter("customerDocument"));
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
