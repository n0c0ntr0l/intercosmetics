package com.example.koda.test;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * Created by koda on 02/03/2015.
 */
public class SingleItemView extends Activity {
    // Declare Variables
    TextView txtcode;
    TextView txtfamily;
    TextView txtname;
    TextView txtprice;
    TextView txtquantity;
    TextView txtdiscount;
    TextView txtpercentage;
    TextView txtfinalprice;
    String code;
    String family;
    String name;
    String price;
    String quantity;
    String discount;
    String finalprice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_listview_item);

        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        code = i.getStringExtra("code");
        family = i.getStringExtra("family");
        name = i.getStringExtra("name");
        price = i.getStringExtra("price");
        quantity = i.getStringExtra("quantity");
        discount = i.getStringExtra("discount");
        finalprice = i.getStringExtra("finalPrice");

        // Locate the TextViews in singleitemview.xml
        txtcode = (TextView) findViewById(R.id.code);
        txtfamily = (TextView) findViewById(R.id.family);
        txtname = (TextView) findViewById(R.id.name);
        txtprice = (TextView) findViewById(R.id.price);
        txtquantity = (TextView) findViewById(R.id.quantity);
        txtdiscount = (TextView) findViewById(R.id.discount);
        txtfinalprice = (TextView) findViewById(R.id.finalprice);

// Load the results into the TextViews
        txtcode.setText(code);
        txtfamily.setText(family);
        txtname.setText(name);
        txtprice.setText(price);
        txtquantity.setText(quantity);
        txtdiscount.setText(discount);
        txtfinalprice.setText(finalprice);
    }
    public void insertProduct(){
        Intent intent = new Intent();
        intent.putExtra("quantity",quantity); //create these methods in product class
        intent.putExtra("price",price);
        intent.putExtra("discount",discount);
        intent.putExtra("finalPrice", finalprice);
        intent.putExtra("code",code);
        intent.putExtra("name",name);
        intent.putExtra("family",family);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}


