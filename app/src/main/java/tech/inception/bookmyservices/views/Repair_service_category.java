package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;

public class Repair_service_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_service_category);

        TextViewNormal service_heading = (TextViewNormal) findViewById(R.id.service_name);
        service_heading.setText(getIntent().getStringExtra("service"));
    }

    public void uninstallation(View view) {

        Intent i = new Intent(Repair_service_category.this , Repair_service.class);

        i.putExtra("type" , "Uninstallation");
        i.putExtra("service" , getIntent().getStringExtra("service"));
        i.putExtra("id" , getIntent().getStringExtra("id"));
        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }

    public void installation(View view) {
        Intent i = new Intent(Repair_service_category.this , Repair_service.class);

        i.putExtra("type" , "Installation");
        i.putExtra("service" , getIntent().getStringExtra("service"));
        i.putExtra("id" , getIntent().getStringExtra("id"));
        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }

    public void gas_refill(View view) {
        Intent i = new Intent(Repair_service_category.this , Repair_service.class);

        i.putExtra("type" , "Gas Refill");
        i.putExtra("service" , getIntent().getStringExtra("service"));
        i.putExtra("id" , getIntent().getStringExtra("id"));
        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }

    public void wet_service(View view) {
        Intent i = new Intent(Repair_service_category.this , Repair_service.class);

        i.putExtra("type" , "Wet Service");
        i.putExtra("service" , getIntent().getStringExtra("service"));
        i.putExtra("id" , getIntent().getStringExtra("id"));
        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }

    public void dry_service(View view) {
        Intent i = new Intent(Repair_service_category.this , Repair_service.class);

        i.putExtra("type" , "Dry Service");
        i.putExtra("service" , getIntent().getStringExtra("service"));
        i.putExtra("id" , getIntent().getStringExtra("id"));
        startActivity(i);
        overridePendingTransition(R.anim.right_in , R.anim.left_out);
    }
}
