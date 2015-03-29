package com.example.koda.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    String[] userinfo = new String[2];
    Connection con = null;
    Context thisConext = null;
    MyActivity currentActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        thisConext = this.getApplicationContext();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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


    public void start(View view) {

        Boolean weHaveFile = checkFile();
        System.out.println(userinfo[0] + "\n" + userinfo[1]);
        if (weHaveFile) {
            Intent intent = new Intent(MyActivity.this, OtherActivity.class);
            startActivity(intent);
        } else {
            String query = "SELECT Password FROM salesmen WHERE Name ='" + userinfo[0] + "'";
            String[] fakeArray = new String[1];
            fakeArray[0] = query;
            this.currentActivity = this;
            new CreateFileConnectToDatabase(this).execute(fakeArray);
        }
/*        String query = "SELECT" + userinfo[0] + "FROM salesman";
        String[] fakeArray = new String[1];
        fakeArray[0] = query;
        new SimpleConnectToDatabase().execute(fakeArray);*/
    }

    public void startTheNextActivity(){
        Intent intent = new Intent(this, OtherActivity.class);
        this.startActivity(intent);
    }


    public Boolean checkFile() {
        try {//file exists
            File file = new File("info");
            BufferedReader buf = new BufferedReader(new FileReader(file));

            userinfo[0] = buf.readLine();
            userinfo[1] = buf.readLine();

            return true;

        } catch (IOException e) {
            //check usrname pwd in database if exist go to next if not try again -> DIFFERENT THREAD
            EditText editText = (EditText) findViewById(R.id.edit_name);
            String name = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_pwd);
            String pwd = editText.getText().toString();
            userinfo[0] = name;
            userinfo[1] = pwd;
            return (false);


        }
    }

    class CreateFileConnectToDatabase extends AsyncTask<String, Void, Integer> {

        Activity activity;
        Context context;    //ELIMINA COGLIONE
        String qResult = "";

        public CreateFileConnectToDatabase(Activity activity){
            this.activity = activity;
            this.context = activity.getApplicationContext();
        }

        @Override
        protected Integer doInBackground(String... query) {
            String link = "http://192.168.0.21/intercosmetics.php";
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(link);

            BasicNameValuePair queryPair = new BasicNameValuePair("query", query[0]);
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            nameValuePairList.add(queryPair);

            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                httpPost.setEntity(urlEncodedFormEntity);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    InputStream inputStream = httpResponse.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String bufferedStrChunk = null;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    String queryResult = stringBuilder.toString();
                    qResult = queryResult;
                    System.out.println(queryResult + "THIS IS THE QUERY RESULT XOXO");
                    if (queryResult.equals("NULL")) {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(thisConext);//ELIMINA COGLIONE
                        dlgAlert.setMessage("wrong password or username");
                        dlgAlert.setTitle("Error Message...");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                    }

                } catch (ClientProtocolException cpe) {
                    System.out.println("First Exception caz of HttpResponese :" + cpe);
                    cpe.printStackTrace();
                } catch (IOException ioe) {
                    System.out.println("Second Exception caz of HttpResponse :" + ioe);
                    ioe.printStackTrace();
                }
            } catch (UnsupportedEncodingException uee) {
                System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                uee.printStackTrace();
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            try {
                if (qResult.equals(userinfo[1])) {
                    System.out.println("WE FUCKING GOT HERE XOXO");
                    File file = new File("info");
                    FileWriter fw = new FileWriter(file);
                    fw.write(userinfo[0] + "\n" + userinfo[1]);
                    context.startActivity(new Intent(context,OtherActivity.class));
                } else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(thisConext);     //ELIMINA COGLIONE
                    dlgAlert.setMessage("wrong password or username");
                    dlgAlert.setTitle("Error Message...");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                }
            } catch (IOException ex) {

            }

        }
    }
}

/* class SimpleConnectToDatabase extends AsyncTask <String,Void,Void>{ //we are not sending in query from start()
        String[] lines = null;
        @Override
        protected Void doInBackground(String... query) {
            String link =  "http://138.37.234.63/intercosmetics.php";
            String queryData = "";
            try {
                URL url = new URL(link);
                try {
                     queryData = URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query[0].toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    URLConnection conn = url.openConnection();
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(queryData);
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                            break;
                    }
                    lines = line.split("/n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }catch (MalformedURLException e){
                System.out.println(e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Connection mycon){
            Boolean found = false;
            for (int i=0; i< lines.length;i++) {
                if (lines[i].contains(userinfo[0]))
                    found = true;
            }
            if(found)
            {
                Intent intent = new Intent(MyActivity.this,OtherActivity.class);
                startActivity(intent);

            }else{
                System.out.println("*******PORCODIO*************** NO CONNECTION TO DATABASE");
            }
        }
    }*/



    /*public void connectToDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println("Cannot Create Connection");
        }


        String serverName = "100.65.217.66";
        String mydatabase = "intercosmetics";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String userDataName = "mobileapp";
        String userDataPwd = "intercosmetics";
        con = DriverManager.getConnection(
                url,
                userDataName,
                userDataPwd); //ADDRESS OF DATABASE
    }

     public boolean connectAndControlDetailsInDatabase(String name, String pwd) throws SQLException {


        Statement stmt = con.createStatement(); //create statement, comand sent to database
        ResultSet rs = stmt.executeQuery("SELECT " + name + " FROM salesman");   //it returns an array of lists conatining the results

        if (rs.first()) { //.first moves cursor to first row of result set
            String pass = rs.getString("Password");
            if (pass.equals(pwd)) {
                stmt.close();
                return true;
            } else
                stmt.close();
            return false;
        } else
            stmt.close();
        return false;
    }
*/
