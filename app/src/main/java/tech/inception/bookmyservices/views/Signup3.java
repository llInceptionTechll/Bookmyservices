package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.adapters.city_auto_list;
import tech.inception.bookmyservices.datamodel.city_list;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class Signup3 extends AppCompatActivity {

    private AutoCompleteTextView city_et;
    private LinearLayout next1btn;
    private List<city_list> cities_list;
    private LinearLayout block_all;
    private city_auto_list city_adapter;
    private EditText locality_et , address_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        locality_et = (EditText) findViewById(R.id.locality_et);
        address_et = (EditText ) findViewById(R.id.house_et);

        city_et = (AutoCompleteTextView) findViewById(R.id.city_name_et);

        cities_list = new ArrayList<>();

        city_list c = new city_list();

        c.setName("Kolkata");

        cities_list.add(c);

        city_list c2 = new city_list();

        c2.setName("Bangalore");

        cities_list.add(c2);

        city_adapter = new city_auto_list(Signup3.this ,R.layout.autocomplete_list , cities_list);

        city_et.setAdapter(city_adapter);


    }

    private void signup(String city , String locality , String address , final Button b)
    {
        JSONObject jobj = new JSONObject();

        try {
            jobj.put("name" , getIntent().getStringExtra("name"));
            jobj.put("mobile" , getIntent().getStringExtra("mobile"));
            jobj.put("pass" , getIntent().getStringExtra("pass"));
            jobj.put("city" , city);
            jobj.put("locality" , locality);
            jobj.put("address" , address);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest(Global.getInstance().getIP()+"/bookmyservices/signup.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    if(response.getString("result").equals("done"))
                    {

                        SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();
                        sp.putString("user_id",response.getString("id"));
                        sp.commit();


                        Toast.makeText(Signup3.this , "account created successfully" , Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Signup3.this , MainActivity.class));
                        overridePendingTransition(R.anim.right_in , R.anim.left_out);
                        Signup1.inst1.finish();
                        Signup2.inst2.finish();
                        Login.inst0.finish();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Signup3.this , "error try again" , Toast.LENGTH_SHORT).show();

                        b.setText("try again");
                        b.setEnabled(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Signup3.this , "error try again" , Toast.LENGTH_SHORT).show();

                b.setText("try again");
                b.setEnabled(true);
                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 ,2));

        AppController app = AppController.getInstance();
        app.addToRequestQueue(jobjreq);
    }

    public void signupclick (View view) {

        String city , locality , address;

        city = city_et.getText().toString();
        locality = locality_et.getText().toString();
        address = address_et.getText().toString();
        if(city.equals(""))
        {
            Toast.makeText(Signup3.this , "select your city" , Toast.LENGTH_SHORT).show();

            return;
        }
        if(locality.equals(""))
        {
            Toast.makeText(Signup3.this , "enter your locality" , Toast.LENGTH_SHORT).show();

            return;
        }
        if(address.equals(""))
        {
            Toast.makeText(Signup3.this , "enter your address" , Toast.LENGTH_SHORT).show();

            return;
        }
        Button b = (Button) view;
        b.setText("please wait ..");
        b.setEnabled(false);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        signup(city ,locality , address , b );
    }
}
