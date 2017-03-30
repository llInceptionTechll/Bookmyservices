package tech.inception.bookmyservices.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.datamodel.service_data;
import tech.inception.bookmyservices.holders.services_holder;
import tech.inception.bookmyservices.views.Laundry_service;
import tech.inception.bookmyservices.views.Repair_service_category;

/**
 * Created by ghumman on 3/5/2017.
 */

public class service_adapter extends RecyclerView.Adapter<services_holder> {

    private ArrayList<service_data> sd ;

    private Activity a;

    public service_adapter(Activity a , ArrayList<service_data> sd)
    {
        this.a = a;
        this.sd = sd;
    }

    @Override
    public services_holder onCreateViewHolder(ViewGroup parent, int viewType) {

         return  new services_holder(LayoutInflater.from(a).inflate(R.layout.services_cell , parent ,false));

    }

    @Override
    public void onBindViewHolder(services_holder holder, int position) {

        final service_data sdd = sd.get(position);

        holder.service_image.setImageBitmap(StringToBitMap(sdd.getImage()));
        holder.service_name.setText(sdd.getName());
        holder.service_description.setText(sdd.getDescription());
        holder.service_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdd.getCategory().equals("laundry")) {
                    Intent i = new Intent(a, Laundry_service.class);
                    i.putExtra("service", sdd.getName());
                    i.putExtra("id", sdd.getId());
                    a.startActivity(i);

                    a.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                else {
                    Intent i = new Intent(a, Repair_service_category.class);
                    i.putExtra("service", sdd.getName());
                    i.putExtra("id", sdd.getId());
                    a.startActivity(i);

                    a.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return sd.size();
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
