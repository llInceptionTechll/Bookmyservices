package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.adapters.item_cart_adapter;
import tech.inception.bookmyservices.adapters.rate_adapter;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.rate_data;
import tech.inception.bookmyservices.holders.cart_item_holder;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class Add_item_cart extends AppCompatActivity {
    private ArrayList<rate_data> rate_list , filter_rate_list;
    private item_cart_adapter adapter;

    private EditText search_et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_cart);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        search_et = (EditText) findViewById(R.id.search_et);

        filter_rate_list = new ArrayList<>();


        rate_list = new ArrayList<>();
        adapter = new item_cart_adapter(Add_item_cart.this , filter_rate_list , getIntent().getStringExtra("service"));

        RecyclerView rv = (RecyclerView) findViewById(R.id.rate_recycler);
        rv.setLayoutManager(new LinearLayoutManager(Add_item_cart.this , LinearLayoutManager.VERTICAL , false));

        rv.setAdapter(adapter);

        get_rate();

        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private void get_rate()
    {
        JSONObject jobj = new JSONObject();

        try {
            jobj.put("id" , getIntent().getStringExtra("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest(Global.getInstance().getIP() + "/bookmyservices/get_rate.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)

            {
                try {
                    JSONArray jarr = response.getJSONArray("rates");
                    for(int i = 0 ; i < jarr.length() ; i ++)
                    {
                        JSONObject jobj = (JSONObject) jarr.get(i);
                        rate_data data = new rate_data();
                        data.setId(jobj.getString("id"));
                        data.setItem(jobj.getString("item"));
                        data.setPrice(jobj.getString("price"));

                        rate_list.add(data);
                    }

                    filter("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)

            {

                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 , 2));

        AppController.getInstance().addToRequestQueue(jobjreq);

    }

    public void filter(String text) {

            filter_rate_list.clear();
            if (text.isEmpty()) {
                filter_rate_list.addAll(rate_list);
            } else {
                text = text.toLowerCase();
                for (rate_data item : rate_list) {
                    if (item.getItem().toLowerCase().contains(text) ) {
                        filter_rate_list.add(item);
                    }
                }
            }
            adapter.notifyDataSetChanged();

    }

    public void continue_to_cart(View view) {

        if(MainActivity.cart_list.size() > 0) {
            Intent i = new Intent(Add_item_cart.this, Cart.class);
            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            Laundry_service.inst.finish();
            finish();
        }
        else {

            Toast.makeText(Add_item_cart.this , "please add some items to cart" , Toast.LENGTH_SHORT).show();

        }
    }

    public void close(View view) {

        finish();
    }
}