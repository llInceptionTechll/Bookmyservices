package tech.inception.bookmyservices.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.adapters.rate_adapter;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.rate_data;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class RateCard extends AppCompatActivity {

    private ArrayList<rate_data> rate_list ;
    private rate_adapter adapter;
    private TextViewNormal heading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_card);
        heading = (TextViewNormal) findViewById(R.id.service_name);
        heading.setText(getIntent().getStringExtra("service"));
        rate_list = new ArrayList<>();
        adapter = new rate_adapter(RateCard.this , rate_list);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rate_recycler);
        rv.setLayoutManager(new LinearLayoutManager(RateCard.this , LinearLayoutManager.VERTICAL , false));

        rv.setAdapter(adapter);

        get_rate();

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

                    adapter.notifyDataSetChanged();
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
}
