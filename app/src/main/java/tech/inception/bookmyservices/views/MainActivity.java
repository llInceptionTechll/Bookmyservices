package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.adapters.cart_adapter;
import tech.inception.bookmyservices.adapters.offers_pager;
import tech.inception.bookmyservices.adapters.service_adapter;
import tech.inception.bookmyservices.datamodel.cart_data;
import tech.inception.bookmyservices.datamodel.offers_data;
import tech.inception.bookmyservices.datamodel.service_data;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class MainActivity extends FragmentActivity implements ViewPager.PageTransformer , View.OnClickListener {

    private offers_pager  pagerAdapter;
    private ViewPager pager;
    private ArrayList<offers_data> al;
    private ArrayList<service_data> sd;
    private List<ImageView> dots;
    private RecyclerView service_recycle;
    private service_adapter serviceAdapter ;
    private LinearLayout block_all , profile_drawer , drawer_logout;
    public static ArrayList<cart_data> cart_list;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_drawer = (LinearLayout) findViewById(R.id.profile_drawer);
        drawer_logout = (LinearLayout) findViewById(R.id.drawer_logout);

        drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        cart_list = new ArrayList<>();

        al = new ArrayList<>();
        sd = new ArrayList<>();
        pager = (ViewPager) findViewById(R.id.pager);
        block_all = (LinearLayout) findViewById(R.id.block_all);
        service_recycle = (RecyclerView) findViewById(R.id.services_recycle);
        service_recycle.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL ,false));
        serviceAdapter = new service_adapter(MainActivity.this , sd);
        service_recycle.setAdapter(serviceAdapter);

        pagerAdapter = new offers_pager(getSupportFragmentManager(),al);
        pager.setPageTransformer(true , this);
        pager.setAdapter(pagerAdapter);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                selectDot(position , al.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        profile_drawer.setOnClickListener(this);
        drawer_logout.setOnClickListener(this);
        getinitials();
    }

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1);

        } else if (position <= 1) { // [-1,1]


            ImageView dummyImageView = (ImageView) view.findViewById(R.id.bgimage);

            dummyImageView.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1);
        }
    }

    public void addDots(int size) {
        dots = new ArrayList<>();
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.dots);

        for (int i = 0; i < size; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.unselectedcircle));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            dot.setPadding(5, 5, 5, 5);
            dotsLayout.addView(dot, params);

            dots.add(dot);
        }

    }

    public void selectDot(int idx , int size) {
        Resources res = getResources();
        for (int i = 0; i < size; i++) {
            int drawableId = (i == idx) ? (R.drawable.selectedcircle) : (R.drawable.unselectedcircle);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

    private void getinitials()
    {
        JsonObjectRequest jsonreq = new JsonObjectRequest(Global.getInstance().getIP() + "/bookmyservices/getinitials.php",new JSONObject() , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray offerarr = response.getJSONArray("offer");

                    for(int i = 0 ;  i < offerarr.length() ; i++)
                    {
                        offers_data data = new offers_data();
                        JSONObject jobj = (JSONObject) offerarr.get(i);

                        data.setOffer(jobj.getString("offer"));
                        data.setOffer_image(jobj.getString("image"));

                        al.add(data);
                    }
                    pagerAdapter.notifyDataSetChanged();
                    addDots(al.size());
                    selectDot(0 , al.size());

                    JSONArray servicesarr = response.getJSONArray("services");

                    for(int i = 0 ;  i < servicesarr.length() ; i++)
                    {
                        service_data data = new service_data();
                        JSONObject jobj = (JSONObject) servicesarr.get(i);

                        data.setId(jobj.getString("id"));
                        data.setImage(jobj.getString("image"));
                        data.setName(jobj.getString("name"));
                        data.setDescription(jobj.getString("description"));
                        data.setCategory(jobj.getString("category"));
                        sd.add(data);
                    }
                    serviceAdapter.notifyDataSetChanged();
                    block_all.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);
            }
        });

        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 ,2));

        AppController.getInstance().addToRequestQueue(jsonreq);
    }

    public void opendrawer(View view) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawer.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.profile_drawer:
                Intent i = new Intent(MainActivity.this , Profile.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in , R.anim.left_out);
                drawer.closeDrawer(Gravity.LEFT);
                break;

            case R.id.drawer_logout:

                SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();
                sp.clear();
                sp.commit();
                finish();

        }
    }
}
