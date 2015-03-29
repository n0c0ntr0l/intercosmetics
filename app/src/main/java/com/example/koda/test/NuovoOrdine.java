package com.example.koda.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class NuovoOrdine extends Activity {

    private Calendar calendar;
    private Order order = new Order();
    private int []date = order.getOrderDate();
    private int oYear = date[2];
    private int oMonth = date [1];
    private int oDay = date [0];
    private int pYear, pMonth, pDay;
    ExpandableListView c_list, p_list;
    ClientExpandableListViewAdapter c_adapter;
    ProductExpandableListViewAdapter p_adapter;
    EditText c_search,p_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_ordine);

        Agent agent = setAgent();
        ArrayList<Client> client = setClient(agent);
        ArrayList<Product> product = setProduct(agent);

        //Set order date
        calendar = Calendar.getInstance();
        order.setOrderDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        // DECIDE WHETHER TO IMPLEMENT
        // showDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

        //Set salesman name*************************************************************************************************
        order.setSalesman(agent.getName());

        //Set Pickup -> check stackoverflow*********************************************************************************
        order.setPickupDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

        //Set Clients*******************************************************************************************************
        c_list = (ExpandableListView) findViewById(R.id.contact_expandableListView);
        // Pass results to ListViewAdapter Class
        c_adapter = new ClientExpandableListViewAdapter(this, client);

        // Binds the Adapter to the ListView
        c_list.setAdapter(c_adapter);

        // Locate the EditText in listview_main.xml
        c_search = (EditText) findViewById(R.id.et_client);

        // Capture Text in EditText
        c_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
// TODO Auto-generated method stub
                String text = c_search.getText().toString().toLowerCase(Locale.getDefault());
                c_adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
// TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
// TODO Auto-generated method stub
            }
        });

        //Set products**********************************************************************************************************
        p_list = (ExpandableListView) findViewById(R.id.product_expandableListView);
        // Pass results to ListViewAdapter Class
        p_adapter = new ProductExpandableListViewAdapter(this, product);

        // Binds the Adapter to the ListView
        p_list.setAdapter(p_adapter);

        // Locate the EditText in listview_main.xml
        p_search = (EditText) findViewById(R.id.et_product);

        // Capture Text in EditText
        p_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
                String text = p_search.getText().toString().toLowerCase(Locale.getDefault());
                p_adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
        // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        // TODO Auto-generated method stub
            }
        });


    }

    @SuppressWarnings("deprecation")
    public void setOrderDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")  //copy and pasted from above method
    public void setPickupDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, oYear, oMonth, oDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int oYear, int oMonth, int oDay) {
            order.setOrderDate(oDay,oMonth,oYear);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nuovo_ordine, menu);
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


    public Agent setAgent(){
        try {


            File file = new File("info");
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String name = new String();
            name=buf.readLine();
            String pwd= new String();
            pwd=buf.readLine();
            ResultSet rs = connectAndGetDetailsInAgentDatabase(name, pwd);
            Agent agent = new Agent();
            agent.setName(rs.getString("Name"));
            agent.setId(rs.getInt("ID"));
            agent.setPwd(rs.getString("Password"));
            return (agent);


        } catch (IOException e) {
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder
                    .setMessage( "There was an error: " + e.getMessage() )
                    .setCancelable( false )
                    .setNeutralButton( "Ok.", new DialogInterface.OnClickListener()
                    {
                        public void onClick ( DialogInterface dialog, int which )
                        {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    } );
            AlertDialog error = builder.create();
            error.show();
            return null;
        } catch (SQLException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder
                    .setMessage( "There was an error: " + e.getMessage() )
                    .setCancelable( false )
                    .setNeutralButton( "Ok.", new DialogInterface.OnClickListener()
                    {
                        public void onClick ( DialogInterface dialog, int which )
                        {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    } );
            AlertDialog error = builder.create();
            error.show();
            return null;
        }

    }

    public ResultSet connectAndGetDetailsInAgentDatabase(String name, String pwd) throws SQLException {
        String serverName = "localhost";
        String mydatabase = "intercosmetics";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        Connection con = DriverManager.getConnection(
                url,
                name,
                pwd); //ADDRESS OF DATABASE
        Statement stmt = con.createStatement(); //create statement, comand sent to database
        ResultSet rs = stmt.executeQuery("SELECT "+name+" FROM salesmen");   //it returns an array of lists conatining the results
        return rs;
    }

    public ArrayList<Client> setClient(Agent agent) {

        try {


            ResultSet rs = connectAndGetDetailsInClientDatabase(agent);
            ArrayList<Client> contactList = new ArrayList<Client>();
            while (rs.next()) {
                contactList.add(new Client(rs.getInt("ID"), rs.getString("Name"), rs.getString("Address"), rs.getInt("Zip_Code"), rs.getInt("SalesmanID")));
            }
            return (contactList);

        }catch (SQLException e){
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder
                    .setMessage( "There was an error: " + e.getMessage() )
                    .setCancelable( false )
                    .setNeutralButton( "Ok.", new DialogInterface.OnClickListener()
                    {
                        public void onClick ( DialogInterface dialog, int which )
                        {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    } );
            AlertDialog error = builder.create();
            error.show();
            return null;
        }


    }

    public ResultSet connectAndGetDetailsInClientDatabase(Agent agent) throws SQLException {
        String serverName = "localhost";
        String mydatabase = "intercosmetics";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        Connection con = DriverManager.getConnection(
                url,
                agent.getName(),
                agent.getPwd()); //ADDRESS OF DATABASE
        Statement stmt = con.createStatement(); //create statement, comand sent to database
        ResultSet rs = stmt.executeQuery("SELECT * FROM client_table WHERE SalesmanID='"+agent.getId()+"'");
        return rs;
    }
    //start createing prodcut class based on sql

    public  ArrayList<Product> setProduct(Agent agent){
        try {


            ResultSet rs = connectAndGetDetailsInProductDatabase(agent);
            ArrayList<Product> productList = new ArrayList<Product>();
            while (rs.next()) {
                productList.add(new Product(rs.getInt("Code"), rs.getString("Name"), rs.getString("Family"), rs.getFloat("Price")));
            }
            return (productList);

        }catch (SQLException e){
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder
                    .setMessage( "There was an error: " + e.getMessage() )
                    .setCancelable( false )
                    .setNeutralButton( "Ok.", new DialogInterface.OnClickListener()
                    {
                        public void onClick ( DialogInterface dialog, int which )
                        {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    } );
            AlertDialog error = builder.create();
            error.show();
            return null;
        }
    }
    public ResultSet connectAndGetDetailsInProductDatabase(Agent agent) throws SQLException{
        String serverName = "localhost";
        String mydatabase = "intercosmetics";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        Connection con = DriverManager.getConnection(
                url,
                agent.getName(),
                agent.getPwd()); //ADDRESS OF DATABASE
        Statement stmt = con.createStatement(); //create statement, comand sent to database
        ResultSet rs = stmt.executeQuery("SELECT * FROM product_table");
        return rs;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String code =data.getStringExtra("code");
            String family=data.getStringExtra("family");
            String name=data.getStringExtra("name");
            String price=data.getStringExtra("price");
            String quantity=data.getStringExtra("quantity");
            String discount=data.getStringExtra("discount");
            String finalprice=data.getStringExtra("finalprice");
            // TODO Update your TextView.
        }
    }
}
