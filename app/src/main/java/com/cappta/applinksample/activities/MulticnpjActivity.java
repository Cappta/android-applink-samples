package com.cappta.applinksample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cappta.applinksample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MulticnpjActivity extends Activity implements View.OnClickListener {

    private Spinner cnpjSpinner;

    @Override
    protected void onCreate(Bundle savedInstance){
         super.onCreate(savedInstance);
         setContentView(R.layout.activity_multicnpj);

         cnpjSpinner = (Spinner) this.findViewById(R.id.spinner_cnpj_list);

         Button sendFetchCheckout = (Button)this.findViewById(R.id.button_fetch_checkout);
         sendFetchCheckout.setOnClickListener(this);

         Button sendActivateCheckout = (Button)this.findViewById(R.id.button_activate_selected_cnpj);
         sendActivateCheckout.setOnClickListener(this);

         onNewIntent(getIntent());
    }

    @Override
    public void onClick(View view){
         switch (view.getId())
         {
             case R.id.button_fetch_checkout:
                 createGetCheckoutUriRequest();
                 break;
             case R.id.button_activate_selected_cnpj:
                 createActivateCheckoutRequest();
                 break;
         }
    }

    @Override
    public void onNewIntent(Intent intent)
    {
        try{
            if(intent.getDataString() == null)
                return;

            Uri appLinkUri = Uri.parse(intent.getDataString());

            switch (appLinkUri.getAuthority())
            {
                case "get-cnpj-list":
                    this.displayCheckoutList(appLinkUri);
                    break;
            }
        }
        catch(Exception e){
            return;
        }
    }

    private void createGetCheckoutUriRequest() {
         Uri capptaAppLink = new Uri.Builder()
                 .scheme("cappta")
                 .authority("get-cnpj-list")
                 .appendQueryParameter("authKey", getString(R.string.cappta_auth_key))
                 .appendQueryParameter("scheme", getString(R.string.app_scheme))
                 .build();

         Intent capptaIntent = new Intent(Intent.ACTION_VIEW, capptaAppLink);
         this.startActivityForResult(capptaIntent, 0);
    }

    private void createActivateCheckoutRequest(){

    }

    private void displayCheckoutList(Uri uri){
        try {
            JSONObject jobj = new JSONObject(uri.getQueryParameter("checkouts"));
            JSONArray jarray = jobj.getJSONArray("checkouts");

            ArrayList<String> cnpjList = new ArrayList<String>();

            for (int i = 0; i < jarray.length(); i++) {
                cnpjList.add(jarray.getJSONObject(i).getString("merchant_cnpj"));
            }

            Spinner spin = (Spinner)this.findViewById(R.id.spinner_cnpj_list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cnpjList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
        }
        catch (Exception e){
            String msg = e.getMessage();
        }
    }
}