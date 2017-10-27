package com.cappta.applinksample.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

/**
 * Created by JGabrielFreitas on 27/10/2017.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

  protected void addListener(int viewId) {
    findViewById(viewId).setOnClickListener(this);
  }

  protected void toast(String message) {
    makeText(this, message, LENGTH_LONG).show();
  }

  @Override public void onClick(View view) {
  }
}
