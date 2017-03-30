package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.cart_data;

public class Laundry_service extends AppCompatActivity {

    private TextViewNormal service_heading , cart_item_num ;

    public static Laundry_service inst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_service);

        inst = this ;

        service_heading = (TextViewNormal) findViewById(R.id.service_name);
        service_heading.setText(getIntent().getStringExtra("service"));
        cart_item_num = (TextViewNormal) findViewById(R.id.cart_item_no);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ((ScrollView) findViewById(R.id.scroll_service)).smoothScrollTo(0,0);
    }

    public void show_rate(View view) {

        Intent i = new Intent(Laundry_service.this , RateCard.class);
        i.putExtra("id" , getIntent().getStringExtra("id"));
        i.putExtra("service" , getIntent().getStringExtra("service"));
        startActivity(i);
    }



    public void continue_to_cart(View view)
    {


            Intent i = new Intent(Laundry_service.this , Add_item_cart.class);
             i.putExtra("id" , getIntent().getStringExtra("id"));
             i.putExtra("service" , getIntent().getStringExtra("service"));
            startActivity(i);
            overridePendingTransition(R.anim.right_in , R.anim.left_out);

    }

    public void close(View view) {

        finish();
    }
}
