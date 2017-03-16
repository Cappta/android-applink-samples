package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cappta.applinksample.R;

public class PaymentReversalActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_reversal);

        Button buttonSendReversal = (Button)this.findViewById(R.id.button_send_reversal);
        buttonSendReversal.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("cappta");
        builder.authority("payment-reversal");
        builder.appendQueryParameter("authKey", getString(R.string.cappta_auth_key));
        builder.appendQueryParameter("scheme", getString(R.string.app_scheme));

        EditText administrativeCode = (EditText)this.findViewById(R.id.administrative_code);
        builder.appendQueryParameter("administrativeCode", administrativeCode.getText().toString());

        EditText administrativePassword = (EditText)this.findViewById(R.id.administrative_password);
        builder.appendQueryParameter("administrativePassword", administrativePassword.getText().toString());

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, builder.build());

        this.startActivityForResult(capptaIntent, 0);
    }
}
