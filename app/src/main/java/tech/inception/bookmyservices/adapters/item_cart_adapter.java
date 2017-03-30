package tech.inception.bookmyservices.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.inception.bookmyservices.R;

import tech.inception.bookmyservices.datamodel.cart_data;
import tech.inception.bookmyservices.datamodel.rate_data;

import tech.inception.bookmyservices.holders.cart_item_holder;
import tech.inception.bookmyservices.views.MainActivity;


/**
 * Created by ghumman on 3/24/2017.
 */

public class item_cart_adapter extends RecyclerView.Adapter<cart_item_holder> {

    private Activity a ;
    private ArrayList<rate_data> rate_list;
    private String service_name;

    public item_cart_adapter(Activity a ,  ArrayList<rate_data> rate_list , String service_name )
    {
        this.a = a;
        this.rate_list = rate_list;
        this.service_name = service_name;
    }

    @Override
    public cart_item_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new cart_item_holder(LayoutInflater.from(a).inflate(R.layout.item_cart_cell,parent,false));
    }

    @Override
    public void onBindViewHolder(final cart_item_holder holder, int position) {

        final rate_data data = rate_list.get(position);

        holder.item_name.setText(data.getItem());
        holder.price.setText(data.getPrice());

        holder.qty_text.setText("0");

        for (cart_data cdata : MainActivity.cart_list)
        {
            if(cdata.getId().equals(data.getId()))
            {
                holder.qty_text.setText(cdata.getQty());
            }

        }

        holder.minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.qty_text.getText().toString()) == 0)
                {
                    return;
                }
                else {
                    for(int i = 0 ; i < MainActivity.cart_list.size() ; i++ )
                    {
                        cart_data cdata = MainActivity.cart_list.get(i);
                        if(cdata.getId().equals(data.getId()))
                        {
                           int quant = Integer.parseInt(cdata.getQty()) - 1;
                            if(quant == 0)
                            {
                                MainActivity.cart_list.remove(i);
                                holder.qty_text.setText("0");
                                break;

                            }
                            else
                            {
                                cdata.setQty(String.valueOf(quant));
                                holder.qty_text.setText(String.valueOf(quant));
                                break;
                            }
                        }
                    }
                }
            }
        });

        holder.plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean found = false;

                for (cart_data cdata : MainActivity.cart_list)
                {
                    if(cdata.getId().equals(data.getId()))
                    {
                        found = true;
                        cdata.setQty(String.valueOf(Integer.parseInt(holder.qty_text.getText().toString()) + 1));
                        holder.qty_text.setText(String.valueOf(Integer.parseInt(holder.qty_text.getText().toString())+1));
                        break;
                    }
                }

                if(!found)
                {
                    cart_data newdata = new cart_data();
                    newdata.setId(data.getId());
                    newdata.setName(data.getItem());
                    newdata.setQty("1");
                    newdata.setPrice(data.getPrice());
                    newdata.setMain_service(service_name);


                    MainActivity.cart_list.add(newdata);
                    holder.qty_text.setText("1");
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return rate_list.size();
    }
}
