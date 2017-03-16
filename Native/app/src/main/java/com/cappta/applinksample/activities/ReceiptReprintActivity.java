package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cappta.applinksample.R;

public class ReceiptReprintActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_reprint);

        Button buttonReprintLast = (Button)this.findViewById(R.id.button_reprint_last_receipt);
        buttonReprintLast.setOnClickListener(this);

        Button buttonReprintSpecific = (Button)this.findViewById(R.id.button_reprint_specific);
        buttonReprintSpecific.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("cappta");
        builder.authority("receipt-reprint");
        builder.appendQueryParameter("authKey", getString(R.string.cappta_auth_key));
        builder.appendQueryParameter("scheme", getString(R.string.app_scheme));

        if (view.getId() == R.id.button_reprint_last_receipt) {
            builder.path("last");
        }

        if (view.getId() == R.id.button_reprint_specific) {
            EditText administrativeCode = (EditText)this.findViewById(R.id.administrative_code);
            builder.appendQueryParameter("administrativeCode", administrativeCode.getText().toString());
        }

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, builder.build());

        this.startActivityForResult(capptaIntent, 0);
    }
}
