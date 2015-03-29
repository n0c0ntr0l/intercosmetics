package com.example.koda.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import junit.framework.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


        /************CREATE AND DISPLAY ELEMENT FROM JAVA*******/
        //TextView nameLabel= (TextView) findViewById(R.id.name_label);
       // nameLabel.setText(userinfo[1]+" "+userinfo[2]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void nuovoOrdineOnClick (View view){
        Intent intent = new Intent(OtherActivity.this, NuovoOrdine.class);
        startActivity(intent);
    }

    /*public void ordiniPrecedentiOnClick (View view){
        Intent intent = new Intent (OtherActivity.this, OrdiniPrecedenti.class);
        startActivity(intent);
    }

    public void contattiOnClick (View view){
        Intent intent = new Intent (OtherActivity.this, ContactList.class);
        startActivity(intent);
    }*/

}
