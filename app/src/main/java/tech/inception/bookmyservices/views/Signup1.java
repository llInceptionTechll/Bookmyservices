package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class Signup1 extends AppCompatActivity {

     private  EditText name_et , mobile_et , pass_et;

     public static Signup1 inst1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);
        name_et = (EditText) findViewById(R.id.name_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        inst1 = this;

        mobile_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 10)
                {
                    mobile_et.clearFocus();
                    pass_et.requestFocus();
                    pass_et.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void verifymobile(View view)
    {
        String name , mobile , pass ;

        name = name_et.getText().toString();
        mobile = mobile_et.getText().toString();
        pass = pass_et.getText().toString();

        if(name.equals(""))
        {
            Toast.makeText(Signup1.this , "please enter your name" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(mobile.length() < 10)
        {
            Toast.makeText(Signup1.this , "please check mobile number" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length() < 8)
        {
            Toast.makeText(Signup1.this , "password must be of atleast 8 characters" , Toast.LENGTH_SHORT).show();
            return;
        }

         check_mobile(name , mobile , pass);

    }

    public void close(View view)

    {
        finish();
    }

    private void check_mobile(final String name , final String mobile , final String pass)

    {
        JSONObject jobj = new JSONObject();

        try {
            jobj.put("mobile" , mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest(Global.getInstance().getIP()+"/bookmyservices/check.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("not found"))
                    {
                        int randomPIN = (int)(Math.random()*9000)+1000;
                        Intent i = new Intent(Signup1.this , Signup2.class);

                        i.putExtra("name" , name);
                        i.putExtra("mobile" , String.valueOf(mobile));
                        i.putExtra("pass" , pass);
                        i.putExtra("code",String.valueOf(randomPIN));

                        startActivity(i);
                        overridePendingTransition(R.anim.right_in , R.anim.left_out);
                    }

                    else
                    {
                       Toast.makeText(Signup1.this , "mobile number already exist", Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signup1.this , "error try again", Toast.LENGTH_SHORT).show();

                System.out.println(error);

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = AppController.getInstance();

        app.addToRequestQueue(jobjreq);


    }
}
