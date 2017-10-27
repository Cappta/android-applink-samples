package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cappta.applinksample.R;

public class ConfigurationActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Button sendConfigurationButton = (Button)this.findViewById(R.id.button_send_configuration);
        sendConfigurationButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.getIntent().getDataString() == null) { return; }

        Uri appLinkUri = Uri.parse(this.getIntent().getDataString()) ;

        String responseCode = appLinkUri.getQueryParameter("responseCode");

        if (responseCode.equals("0")) {
            Toast.makeText(this, "PDV ativado com sucesso =)", Toast.LENGTH_LONG).show();

            this.finish();

        } else {

            String reason = appLinkUri.getQueryParameter("reason");
            Toast.makeText(this, reason, Toast.LENGTH_LONG).show();

            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        EditText cnpj = (EditText)this.findViewById(R.id.cnpj);
        EditText checkoutNumber = (EditText)this.findViewById(R.id.checkoutNumber);

        Uri capptaAppLink = new Uri.Builder()
                .scheme("cappta")
                .authority("configuration")
                .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                .appendQueryParameter("scheme", getString(R.string.app_scheme))
                .appendQueryParameter("cnpj", cnpj.getText().toString())
                .appendQueryParameter("checkoutNumber", checkoutNumber.getText().toString())
                .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}
