package com.cappta.applinksample.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cappta.applinksample.R;
import com.cappta.applinksample.model.ApprovedResponse;

import org.json.JSONObject;

public class ResultActivity extends Activity implements View.OnClickListener {

    private ApprovedResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_result);

            Uri appLinkUri = Uri.parse(this.getIntent().getDataString()) ;

            String encodedResponse = appLinkUri.getQueryParameter("response");
            byte[] data = Base64.decode(encodedResponse, Base64.DEFAULT);
            String decodedResponse = new String(data, "UTF-8");

            String responseCode = appLinkUri.getQueryParameter("responseCode");

            if (responseCode.equals("0")) {
                this.response = new ApprovedResponse(decodedResponse);

                this.showReceipts();

                Button buttonBack = (Button) this.findViewById(R.id.button_back);
                buttonBack.setOnClickListener(this);

            } else {

                JSONObject json = new JSONObject(decodedResponse);
                String reason = json.getString("reason");
                Toast.makeText(this, reason, Toast.LENGTH_LONG).show();

                this.finish();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            this.finish();
        }
    }

    private void showReceipts() {
        TextView cupomLojista = (TextView) this.findViewById(R.id.via_lojista);
        TextView cupomCliente = (TextView) this.findViewById(R.id.via_cliente);

        cupomLojista.setText(this.response.getMerchantReceipt());
        cupomCliente.setText(this.response.getCustomerReceipt());
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}