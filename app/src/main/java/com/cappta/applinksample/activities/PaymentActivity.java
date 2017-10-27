package com.cappta.applinksample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.cappta.applinksample.R;
import com.cappta.applinksample.model.InstallmentType;
import com.cappta.applinksample.model.PaymentType;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.content.Intent.ACTION_VIEW;
import static com.cappta.applinksample.model.InstallmentType.values;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {

  private Spinner installmentTypeSpinner;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);

    installmentTypeSpinner = (Spinner) findViewById(R.id.installment_type);
    installmentTypeSpinner.setAdapter(new ArrayAdapter<>(this, simple_spinner_dropdown_item, values()));
    addListener(R.id.button_send_credit_payment);
    addListener(R.id.button_send_debit_payment);
  }

  @Override public void onClick(View view) {

    switch (view.getId()) {
      case R.id.button_send_credit_payment:
        SendPayment(PaymentType.CREDIT);
        break;
      case R.id.button_send_debit_payment:
        SendPayment(PaymentType.DEBIT);
        break;
    }
  }

  private void SendPayment(PaymentType paymentType) {

    EditText paymentId = (EditText) findViewById(R.id.payment_id);
    EditText paymentAmountInput = (EditText) findViewById(R.id.payment_amount);
    EditText installments = (EditText) findViewById(R.id.installments);
    InstallmentType installmentType = (InstallmentType) installmentTypeSpinner.getSelectedItem();

    float paymentAmount = Float.parseFloat(paymentAmountInput.getText().toString());
    int paymentAmountInCents = Math.round(paymentAmount * 100);

    Uri capptaAppLink = new Uri.Builder().scheme("cappta")
                                         .authority("payment")
                                         .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                                         .appendQueryParameter("paymentId", paymentId.getText().toString())
                                         .appendQueryParameter("amount", Integer.toString(paymentAmountInCents))
                                         .appendQueryParameter("paymentType", paymentType.getValue())
                                         .appendQueryParameter("installments", installments.getText().toString())
                                         .appendQueryParameter("installmentType", Integer.toString(installmentType.getValue()))
                                         .appendQueryParameter("scheme", getString(R.string.app_scheme))
                                         .build();

    Intent capptaIntent = new Intent(ACTION_VIEW, capptaAppLink);
    startActivityForResult(capptaIntent, 0);
  }
}