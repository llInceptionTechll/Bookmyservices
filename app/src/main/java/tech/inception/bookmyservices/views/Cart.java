package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.adapters.cart_adapter;
import tech.inception.bookmyservices.custom.TextViewBold;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.cart_data;

public class Cart extends AppCompatActivity {


    private LinearLayout cart_scroll;
    private ArrayList<String> main_categories;
    //private static TextViewBold cart_heading;
    private static TextViewNormal proceed_from_cart , sub_total_price , service_charges , total_amount;
    private RelativeLayout service_charges_lay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        proceed_from_cart = (TextViewNormal) findViewById(R.id.proceed_from_cart);

        sub_total_price = (TextViewNormal) findViewById(R.id.subtotal_price);

        service_charges = (TextViewNormal) findViewById(R.id.service_charges);

        total_amount = (TextViewNormal) findViewById(R.id.total_amount);

        service_charges_lay = (RelativeLayout) findViewById(R.id.service_tax_lay);

        if(MainActivity.cart_list.size() == 0)
        {
            proceed_from_cart.setText("Add some services to cart");
        }

        main_categories = new ArrayList<>();

        Map<String , LinearLayout> lays = new HashMap<String , LinearLayout>();

        cart_scroll = (LinearLayout) findViewById(R.id.cart_scroll);



        LinearLayout.LayoutParams tvpara0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tvpara0.setMargins(5, 10, 5, 5); // llp.setMargins(left, top, right, bottom);

        TextViewNormal tvreview = new TextViewNormal(Cart.this);
        tvreview.setTextColor(getResources().getColor(R.color.startColor));
        tvreview.setLayoutParams(tvpara0);
        tvreview.setGravity(Gravity.CENTER);
        tvreview.setTextSize(16);
        tvreview.setText("Review Order");


        cart_scroll.addView(tvreview);

        for (cart_data items : MainActivity.cart_list)
        {
            Boolean found  = false ;
            for (String service : main_categories)
            {
                if(service.equals(items.getMain_service()))
                {
                    found = true;
                    break;
                }
            }

            if(! found)
            {
                main_categories.add(items.getMain_service());
            }

        }

        for(String name : main_categories)
        {

            lays.put(name , new LinearLayout(Cart.this));
        }

        for (Map.Entry<String, LinearLayout> entry : lays.entrySet())
        {


            LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p1.setMargins(5,5,5,5);
            entry.getValue().setLayoutParams(p1);
            entry.getValue().setOrientation(LinearLayout.VERTICAL);
            TextViewNormal tv1 = new TextViewNormal(Cart.this);
            tv1.setText(entry.getKey());
            tv1.setTextColor(getResources().getColor(R.color.startColor));
            tv1.setTextSize(20);

            View v = new View(Cart.this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
            v.setLayoutParams(p);
            v.setBackgroundColor(getResources().getColor(R.color.startColor));
            entry.getValue().addView(v);
            entry.getValue().addView(tv1);


            cart_scroll.addView(entry.getValue());

        }

        int amount = 0 , dry_amount = 0;

        for(cart_data items : MainActivity.cart_list)

        {
            LinearLayout parent = new LinearLayout(Cart.this);

            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            parent.setOrientation(LinearLayout.HORIZONTAL);

            TextViewNormal tv1 = new TextViewNormal(Cart.this);
            tv1.setTextColor(getResources().getColor(R.color.black));

            LinearLayout.LayoutParams tvpara = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvpara.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);
            tv1.setLayoutParams(tvpara);
            tv1.setTextSize(16);

            TextViewNormal tv2 = new TextViewNormal(Cart.this);
            tv2.setTextColor(getResources().getColor(R.color.black));
            tv2.setLayoutParams(tvpara);
            tv2.setTextSize(16);

            TextViewNormal tv3 = new TextViewNormal(Cart.this);
            tv3.setTextColor(getResources().getColor(R.color.black));
            tv3.setLayoutParams(tvpara);
            tv3.setTextSize(16);

            TextViewNormal tv4 = new TextViewNormal(Cart.this);
            tv4.setTextColor(getResources().getColor(R.color.black));
            tv4.setLayoutParams(tvpara);
            tv4.setTextSize(16);

            TextViewNormal tv5 = new TextViewNormal(Cart.this);
            tv5.setTextColor(getResources().getColor(R.color.black));

            LinearLayout.LayoutParams tvpara2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvpara.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);
            tv5.setLayoutParams(tvpara2);
            tv5.setGravity(Gravity.RIGHT);
            tv5.setTextSize(16);


            tv1.setText(String.valueOf(items.getQty()));
            tv2.setText("x");
            tv3.setText("");
            tv4.setText(items.getName());

            tv5.setText("Rs "+String.valueOf(Integer.parseInt(items.getPrice())*Integer.parseInt(items.getQty())));

            amount += Integer.parseInt(items.getPrice())* Integer.parseInt(items.getQty());

            if(items.getMain_service().equals("Dry Cleaning"))
            {
                dry_amount += Integer.parseInt(items.getPrice())* Integer.parseInt(items.getQty());
            }
            parent.addView(tv1);
            parent.addView(tv2);
            parent.addView(tv3);
            parent.addView(tv4);
            parent.addView(tv5);

            lays.get(items.getMain_service()).addView(parent);

        }

      service_charges.setText(String.valueOf(dry_amount * 0.1));

        sub_total_price.setText(String.valueOf(amount));

        total_amount.setText(String.valueOf(amount+dry_amount));


    }

    public static void refresh_cart()
    {

    }

    private static void empty_cart()
    {

        if(MainActivity.cart_list.size() == 0)
        {
            //cart_heading.setText("");
            proceed_from_cart.setText("Add some services to cart");
        }
    }

    public void proceed_from_cart(View view)

    {

        if(MainActivity.cart_list.size() > 0) {
            Intent i = new Intent(Cart.this, Checkout.class);
            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
        else {
            finish();
        }


    }

    public void close(View view) {

        finish();
    }
}
