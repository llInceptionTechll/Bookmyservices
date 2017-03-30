package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class Login extends AppCompatActivity {

    public static Login inst0;
    private EditText mobile_et , pass_et;
    private CheckBox remember_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inst0 = this;

        mobile_et = (EditText) findViewById(R.id.mobile_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        remember_check = (CheckBox) findViewById(R.id.remember_checkbox);

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

    public void gotoSignup(View view) {

        startActivity(new Intent(Login.this , Signup1.class));

        overridePendingTransition(R.anim.fade_in , R.anim.fade_out);
    }

    public void signinclick(View view) {

        String mobile , pass;

        mobile = mobile_et.getText().toString();
        pass = pass_et.getText().toString();

        if(mobile.length() < 10)
        {
            Toast.makeText(Login.this , "please check mobile no." , Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.length() < 8)
        {
            Toast.makeText(Login.this , "password must be atleast 8 character long" , Toast.LENGTH_SHORT).show();
            return;
        }

        Button b  = (Button) view;
        b.setText("please wait ..");
        b.setEnabled(false);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


        signin(mobile , pass , b);

    }

    private void signin(String mobile , String pass , final Button b)
    {
        JSONObject jobj = new JSONObject();

        try {
            jobj.put("mobile" , mobile);
            jobj.put("pass" , pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest(Global.getInstance().getIP()+ "/bookmyservices/login.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("done"))
                    {

                        SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();
                        sp.putString("user_id",response.getString("id"));
                        if(remember_check.isChecked())
                        {
                            sp.putString("remember" , "yes");
                        }
                        sp.commit();

                        startActivity(new Intent(Login.this , MainActivity.class));
                        overridePendingTransition(R.anim.right_in , R.anim.left_out);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this , "error try again" , Toast.LENGTH_SHORT).show();
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
                b.setText("try again");
                b.setEnabled(true);
                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2 ,2));

        AppController.getInstance().addToRequestQueue(jobjreq);

    }
}
