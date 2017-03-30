package tech.inception.bookmyservices.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.datamodel.rate_data;
import tech.inception.bookmyservices.holders.rate_holder;

/**
 * Created by ghumman on 3/12/2017.
 */

public class rate_adapter extends RecyclerView.Adapter<rate_holder> {

    private Activity a ;
    private ArrayList<rate_data> rate_list;

    public rate_adapter(Activity a , ArrayList<rate_data> rate_list)
    {
        this.a = a;
        this.rate_list = rate_list;
    }


    @Override
    public rate_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new rate_holder(LayoutInflater.from(a).inflate(R.layout.rate_card_cell , parent , false));

    }

    @Override
    public void onBindViewHolder(rate_holder holder, int position) {

        rate_data data = rate_list.get(position);

        holder.item_name.setText(data.getItem());
        holder.price.setText(data.getPrice());

    }

    @Override
    public int getItemCount() {
        return rate_list.size();
    }
}
