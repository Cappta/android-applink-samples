package com.cappta.applinksample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.cappta.applinksample.R;

import static android.content.Intent.ACTION_VIEW;

public class PaymentReversalActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_reversal);
    addListener(R.id.button_send_reversal);
  }

  @Override public void onClick(View view) {
    Uri.Builder builder = new Uri.Builder();
    builder.scheme("cappta");
    builder.authority("payment-reversal");
    builder.appendQueryParameter("authKey", getString(R.string.cappta_auth_key));
    builder.appendQueryParameter("scheme", getString(R.string.app_scheme));

    EditText administrativeCode = (EditText) findViewById(R.id.administrative_code);
    builder.appendQueryParameter("administrativeCode", administrativeCode.getText().toString());

    EditText administrativePassword = (EditText) findViewById(R.id.administrative_password);
    builder.appendQueryParameter("administrativePassword", administrativePassword.getText().toString());

    Intent capptaIntent = new Intent(ACTION_VIEW, builder.build());
    startActivityForResult(capptaIntent, 0);
  }
}
