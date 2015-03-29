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
public class ClientExpandableListViewAdapter extends BaseAdapter{
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Client> clientList = null;
    private ArrayList<Client> c_arraylist;

    public ClientExpandableListViewAdapter(Context context, List<Client> clientList) {
        mContext = context;
        this.clientList = clientList;
        inflater = LayoutInflater.from(mContext);
        this.c_arraylist = new ArrayList<Client>();
        this.c_arraylist.addAll(clientList);
    }

    public class ViewHolder { //??????????????????????????????????
        TextView id;
        TextView name;
        TextView address;
        TextView zip;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }

    @Override
    public Client getItem(int position) {
        return clientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent){//when the fuck is this method called?
        final ViewHolder holder;
        if (view == null) {
          /*  holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);  //create listview_item xml <- this shouldn't be needed: i do not want a new activity
            // Locate the TextViews in listview_item.xml
            holder.rank = (TextView) view.findViewById(R.id.rank);  //decide what info to hold <-> display
            holder.country = (TextView) view.findViewById(R.id.country);
            holder.population = (TextView) view.findViewById(R.id.population);
            view.setTag(holder);
        */
        }/* else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.rank.setText(clientList.get(position).getRank());
        holder.country.setText(clientList.get(position).getCountry());
        holder.population.setText(clientList.get(position).getPopulation());
        */

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            //SHOULD RETURN THE SEL ECTED CLIENT TO THE NuovoOrdine CLASS AND SET IT IN order.setClient(string g);
                Intent intent = new Intent();
                intent.putExtra("clientName",clientList.get(position).getName());
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        clientList.clear();
        if (charText.length() == 0) {
            clientList.addAll(c_arraylist);
        }
        else
        {
            for (Client wp : c_arraylist)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    clientList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
