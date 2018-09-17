package com.cappta.applinksample.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cappta.applinksample.R;

public class ResultActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_result);

            Uri appLinkUri = Uri.parse(this.getIntent().getDataString()) ;

            String responseCode = appLinkUri.getQueryParameter("responseCode");

            if (responseCode.equals("0")) {
                this.showReceipts(appLinkUri);

                Button buttonBack = (Button) this.findViewById(R.id.button_back);
                buttonBack.setOnClickListener(this);

            }


            else {
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

    private void showReceipts(Uri appLinkUri) {
        TextView cupomLojista = (TextView) this.findViewById(R.id.via_lojista);
        TextView cupomCliente = (TextView) this.findViewById(R.id.via_cliente);

        cupomLojista.setText(appLinkUri.getQueryParameter("merchantReceipt"));
        cupomCliente.setText(appLinkUri.getQueryParameter("customerReceipt"));

        if (appLinkUri.toString().contains("token-creation")) {
            TextView tokenResponse = (TextView) this.findViewById(R.id.token_response);

            String message = "name: " + appLinkUri.getQueryParameter("name");
            message += "\n document: " + appLinkUri.getQueryParameter("document");
            message += "\n documentType: " + appLinkUri.getQueryParameter("documentType");
            message += "\n email: " + appLinkUri.getQueryParameter("email");

            tokenResponse.setText(message);
        }
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}