package tech.inception.bookmyservices.views;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.EditTextAvira;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.offers_data;
import tech.inception.bookmyservices.datamodel.service_data;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;

public class Checkout extends AppCompatActivity {

    private EditTextAvira house_et , locality_et , city_et ;
    private TextViewNormal name_txt , house_txt , locality_txt , city_txt , date_txt ;
    private RadioButton same_delivery_radio , diff_delivery_radio ;
    private LinearLayout delivery_address , block_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        name_txt = (TextViewNormal) findViewById(R.id.name_txt);
        house_txt = (TextViewNormal) findViewById(R.id.address_txt);
        locality_et =(EditTextAvira) findViewById(R.id.locality_et);
        locality_txt = (TextViewNormal) findViewById(R.id.locality_txt);
        city_txt = (TextViewNormal) findViewById(R.id.city_txt);
        house_et = (EditTextAvira) findViewById(R.id.house_et);
        city_et = (EditTextAvira) findViewById(R.id.city_et);
        same_delivery_radio = (RadioButton) findViewById(R.id.same_delivery_radio);
        diff_delivery_radio = (RadioButton) findViewById(R.id.diff_delivery_radio);
        delivery_address = (LinearLayout) findViewById(R.id.delivery_add_lay);

        date_txt = (TextViewNormal) findViewById(R.id.curr_date);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        date_txt.setText(cal.get(Calendar.DAY_OF_MONTH)+" / "+cal.get(Calendar.MONTH)+" / "+cal.get(Calendar.YEAR));

        block_all = (LinearLayout) findViewById(R.id.block_all);
        same_delivery_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {

                    delivery_address.setVisibility(View.GONE);
                }
            }
        });

        diff_delivery_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {

                    delivery_address.setVisibility(View.VISIBLE);
                }
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        try {
            getaddress();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getaddress() throws JSONException {

        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);
        JSONObject jobj = new JSONObject();
        jobj.put("id", sp.getString("user_id" , ""));

        JsonObjectRequest jsonreq = new JsonObjectRequest(Global.getInstance().getIP() + "/bookmyservices/get_address.php", jobj , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    name_txt.setText(response.getString("name"));
                    house_txt.setText(response.getString("address"));
                    locality_txt.setText(response.getString("locality"));
                    city_txt.setText(response.getString("city"));

                    city_et.setText(response.getString("city"));
                    city_et.setEnabled(false);

                    block_all.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 ,2));

        AppController.getInstance().addToRequestQueue(jsonreq);
    }

    public void openCalender(View v)
    {

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog datePicker = new DatePickerDialog(this,
                R.style.Theme_Dialog,
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));

        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");

        datePicker.show();





    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);

            date_txt.setText(day1 + "/" + month1 + "/" + year1);
        }
    };

    public void close(View view) {

        finish();
    }
}
