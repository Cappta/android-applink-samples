package com.cappta.applinksample.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cappta.applinksample.R;

public class ResultActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {

      setContentView(R.layout.activity_result);

      Uri appLinkUri = Uri.parse(getIntent().getDataString());

      String responseCode = appLinkUri.getQueryParameter("responseCode");

      if (responseCode.equals("0")) {
        this.showReceipts(appLinkUri);
        addListener(R.id.button_back);

      } else {
        String reason = appLinkUri.getQueryParameter("reason");
        toast(reason);
        finish();
      }
    } catch (Exception e) {
      toast(e.getMessage());
      this.finish();
    }
  }

  private void showReceipts(Uri appLinkUri) {
    TextView cupomLojista = (TextView) findViewById(R.id.via_lojista);
    TextView cupomCliente = (TextView) findViewById(R.id.via_cliente);

    cupomLojista.setText(appLinkUri.getQueryParameter("merchantReceipt"));
    cupomCliente.setText(appLinkUri.getQueryParameter("customerReceipt"));
  }

  @Override public void onClick(View view) {
    finish();
  }
}