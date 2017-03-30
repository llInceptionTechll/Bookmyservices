package tech.inception.bookmyservices.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.volley.AppController;
import tech.inception.bookmyservices.volley.Global;


public class Splash extends AppCompatActivity {

   private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new AppController(Splash.this);
        new Global().setIP("http://www.inceptiontechnologies.co.in/phpfiles");


       logo = (ImageView) findViewById(R.id.logo);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fadein_out);
        anim.setInterpolator((new AccelerateDecelerateInterpolator()));
        anim.setFillAfter(true);
        logo.startAnimation(anim);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);

                if(sp.getString("remember" , "").equals("yes") && ! sp.getString("user_id","").equals("")) {


                    startActivity(new Intent(Splash.this, MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                    finish();
                }else {
                    startActivity(new Intent(Splash.this, Login.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                    finish();
                }
            }
        }, 3000);
    }
}
