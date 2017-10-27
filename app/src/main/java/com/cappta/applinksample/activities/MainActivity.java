package com.cappta.applinksample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cappta.applinksample.R;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    addListener(R.id.button_payment);
    addListener(R.id.button_payment_reversal);
    addListener(R.id.button_receipt_reprint);
    addListener(R.id.button_configuration);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
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
    }
  }

  private void startActivity(Class activityClass) {
    Intent intent = new Intent(this, activityClass);
    startActivity(intent);
  }
}