package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;

public class Repair_service extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_service);

        TextViewNormal service_heading = (TextViewNormal) findViewById(R.id.service_name);
        service_heading.setText(getIntent().getStringExtra("type"));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void show_rate(View view) {

        Intent i = new Intent(Repair_service.this , RateCard.class);
        i.putExtra("id" , getIntent().getStringExtra("id"));
        i.putExtra("service" , getIntent().getStringExtra("service"));
        startActivity(i);
    }
}
