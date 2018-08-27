package com.cappta.applinksample.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cappta.applinksample.model.DocumentType;

import com.cappta.applinksample.R;

import org.w3c.dom.Document;

public class TokenCreationActivity extends Activity implements View.OnClickListener {

    private Spinner documentTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_creation);

        this.documentTypeSpinner = (Spinner) this.findViewById(R.id.document_type);

        this.documentTypeSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, DocumentType.values()));

        Button executeOperation = (Button) this.findViewById(R.id.executeCreateToken);
        executeOperation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SendTokenCreationRequest();
    }

    public void SendTokenCreationRequest() {
        EditText customerName = (EditText) this.findViewById(R.id.customer_name);
        EditText customerDocument = (EditText) this.findViewById(R.id.customer_Document);
        EditText customerEmail = (EditText) this.findViewById(R.id.customer_Email);

        DocumentType documentType = (DocumentType) this.documentTypeSpinner.getSelectedItem();

        Uri capptaAppLink = new Uri.Builder()
                .scheme("cappta")
                .authority("tokenization")
                .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                .appendQueryParameter("name", customerName.getText().toString())
                .appendQueryParameter("document", customerDocument.getText().toString())
                .appendQueryParameter("documentType", documentType.toString())
                .appendQueryParameter("email", customerEmail.getText().toString())
                .appendQueryParameter("scheme", getString(R.string.app_scheme))
                .build();

        Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);

        this.startActivityForResult(capptaIntent, 0);
    }
}
