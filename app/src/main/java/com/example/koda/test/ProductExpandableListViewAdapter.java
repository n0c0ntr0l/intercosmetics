package com.example.koda.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by koda on 01/03/2015.
 */
public class ProductExpandableListViewAdapter extends BaseAdapter{

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Product> productList = null;
    private ArrayList<Product> arraylist;

    public ProductExpandableListViewAdapter(Context context, List<Product> productList) {
        mContext = context;
        this.productList = productList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Product>();
        this.arraylist.addAll(productList);
    }

    public class ViewHolder {
        TextView quantity;
        TextView price;
        TextView discount;
        TextView finalPrice;
        TextView name;
        TextView family;
        TextView code;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.p_listview_item, null); //p_listview_item.xml
            // Locate the TextViews in listview_item.xml
            holder.quantity = (TextView) view.findViewById(R.id.quantity);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.discount = (TextView) view.findViewById(R.id.discount);
            holder.finalPrice = (TextView) view.findViewById(R.id.finalprice);
            holder.code = (TextView) view.findViewById(R.id.code);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.family =(TextView) view.findViewById(R.id.family);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.quantity.setText(productList.get(position).getQuantity()); //create these methods in product class
        holder.price.setText(productList.get(position).getStringPrice()); //create getFloatPrice in Product()
        holder.discount.setText(productList.get(position).getStringDiscount());
        holder.finalPrice.setText(productList.get(position).getStringDiscount());
        holder.code.setText(productList.get(position).getCode());
        holder.name.setText(productList.get(position).getName());
        holder.family.setText(productList.get(position).getFamily());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);  //CREATE CLASS+++++++999

                // Pass all data
                intent.putExtra("quantity",(productList.get(position).getQuantity())); //create these methods in product class
                intent.putExtra("price",(productList.get(position).getPrice()));
                intent.putExtra("discount",(productList.get(position).getDiscount()));
                intent.putExtra("finalPrice", (productList.get(position).getFinalPrice()));
                intent.putExtra("code",(productList.get(position).getCode()));
                intent.putExtra("name",(productList.get(position).getName()));
                intent.putExtra("family",(productList.get(position).getFamily()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        productList.clear();
        if (charText.length() == 0) {
            productList.addAll(arraylist);
        }
        else
        {
            for (Product wp : arraylist)
            {
                //search both by name and by family
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)||wp.getFamily().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    productList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}