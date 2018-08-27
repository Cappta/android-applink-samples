package com.cappta.applinksample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cappta.applinksample.model.DocumentType;

import com.cappta.applinksample.R;

import org.w3c.dom.Document;

public class TokenCreationActivity extends Activity implements View.OnClickListener{

   private Spinner documentTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_creation);

        this.documentTypeSpinner = (Spinner)this.findViewById(R.id.document_type);

        this.documentTypeSpinner.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, DocumentType.values()));

        Button executeOperation = (Button)this.findViewById(R.id.executeCreateToken);
        executeOperation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.executeCreateToken:
                break;
        }
    }
}
