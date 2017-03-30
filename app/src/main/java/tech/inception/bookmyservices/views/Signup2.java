package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.EditTextAvira;

public class Signup2 extends AppCompatActivity {

    private EditTextAvira otp_pin1 , otp_pin2 , otp_pin3 , otp_pin4;
    public static Signup2 inst2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);



        inst2 = this;
        otp_pin1 = (EditTextAvira) findViewById(R.id.otp_pin1);
        otp_pin2 = (EditTextAvira) findViewById(R.id.otp_pin2);
        otp_pin3 = (EditTextAvira) findViewById(R.id.otp_pin3);
        otp_pin4 = (EditTextAvira) findViewById(R.id.otp_pin4);

        otp_pin1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if(s.length()==1)
                {

                    otp_pin1.clearFocus();
                    otp_pin2.requestFocus();
                    otp_pin2.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {



            }

            public void afterTextChanged(Editable s) {


            }
        });

        otp_pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==1)
                {

                    otp_pin2.clearFocus();
                    otp_pin3.requestFocus();
                    otp_pin3.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()==1)
                {

                    otp_pin3.clearFocus();
                    otp_pin4.requestFocus();
                    otp_pin4.setCursorVisible(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 1)
                {
                    if((otp_pin1.getText().toString()+otp_pin2.getText().toString()+otp_pin3.getText().toString()+otp_pin4.getText().toString()).equals(getIntent().getStringExtra("code"))) {
                        Intent i = new Intent(Signup2.this, Signup3.class);

                        i.putExtra("name", getIntent().getStringExtra("name"));
                        i.putExtra("mobile" , getIntent().getStringExtra("mobile"));
                        i.putExtra("pass" , getIntent().getStringExtra("pass"));
                        startActivity(i);
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                    else {
                        Toast.makeText(Signup2.this , "code do not matched" , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Toast.makeText(Signup2.this , getIntent().getStringExtra("code"), Toast.LENGTH_SHORT).show();

    }


    public void gotocity(View view) {

        if((otp_pin1.getText().toString()+otp_pin2.getText().toString()+otp_pin3.getText().toString()+otp_pin4.getText().toString()).equals(getIntent().getStringExtra("code"))) {

            Intent i = new Intent(Signup2.this, Signup3.class);

            i.putExtra("name", getIntent().getStringExtra("name"));
            i.putExtra("mobile" , getIntent().getStringExtra("mobile"));
            i.putExtra("pass" , getIntent().getStringExtra("pass"));
            startActivity(i);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else {
            Toast.makeText(Signup2.this , "code do not matched" , Toast.LENGTH_SHORT).show();

        }
    }
}
