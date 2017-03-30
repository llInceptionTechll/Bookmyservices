package tech.inception.bookmyservices.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.SemiCircleDrawable;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout profile_semi = (LinearLayout) findViewById(R.id.profile_semi);

       // profile_semi.setBackgroundDrawable(new SemiCircleDrawable());
    }
}
