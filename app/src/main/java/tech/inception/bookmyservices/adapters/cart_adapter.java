package tech.inception.bookmyservices.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.datamodel.cart_data;
import tech.inception.bookmyservices.holders.cart_holder;
import tech.inception.bookmyservices.views.delete_pop_up;

/**
 * Created by ghumman on 3/13/2017.
 */

public class cart_adapter extends RecyclerView.Adapter<cart_holder> {

    private Activity a ;
    private ArrayList<cart_data> cart_list;

    public cart_adapter(Activity a, ArrayList<cart_data> cart_list)
    {

        this.a = a ;
        this.cart_list = cart_list;
    }

    @Override
    public cart_holder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        return new cart_holder(LayoutInflater.from(a).inflate(R.layout.cart_cell,parent,false));

    }

    @Override
    public void onBindViewHolder(cart_holder holder, int position) {

        final cart_data data = cart_list.get(position);
        holder.itemName.setText(data.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new delete_pop_up(a , R.style.Theme_Dialog , data.getName() , data.getId()).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cart_list.size();
    }
}
