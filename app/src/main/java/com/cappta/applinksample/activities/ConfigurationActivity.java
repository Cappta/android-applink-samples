package com.cappta.applinksample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.cappta.applinksample.R;

import static android.content.Intent.ACTION_VIEW;

public class ConfigurationActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_configuration);
    addListener(R.id.button_send_configuration);
  }

  @Override protected void onResume() {
    super.onResume();

    if (this.getIntent().getDataString() == null) {
      return;
    }

    Uri appLinkUri = Uri.parse(this.getIntent().getDataString());
    String responseCode = appLinkUri.getQueryParameter("responseCode");

    if (responseCode.equals("0")) {
      toast("PDV ativado com sucesso =)");
      finish();
    } else {

      String reason = appLinkUri.getQueryParameter("reason");
      toast(reason);
      finish();
    }
  }

  @Override public void onClick(View v) {
    EditText cnpj = (EditText) findViewById(R.id.cnpj);
    EditText checkoutNumber = (EditText) findViewById(R.id.checkoutNumber);

    Uri capptaAppLink = new Uri.Builder().scheme("cappta")
                                         .authority("configuration")
                                         .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                                         .appendQueryParameter("scheme", getString(R.string.app_scheme))
                                         .appendQueryParameter("cnpj", cnpj.getText().toString())
                                         .appendQueryParameter("checkoutNumber", checkoutNumber.getText().toString())
                                         .build();

    Intent capptaIntent = new Intent(ACTION_VIEW, capptaAppLink);
    startActivityForResult(capptaIntent, 0);
  }
}
