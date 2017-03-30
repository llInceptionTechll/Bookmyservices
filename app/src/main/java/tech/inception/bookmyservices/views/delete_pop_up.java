package tech.inception.bookmyservices.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewBold;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.cart_data;

/**
 * Created by ghumman on 3/14/2017.
 */

public class delete_pop_up extends Dialog implements View.OnClickListener{


    private String name , id ;
    private TextViewBold service_heading;
    private TextViewNormal cancel , delete;
    private Context c;
    public delete_pop_up(Context context, int theme_Dialog, String dataName, String name) {
        super(context , theme_Dialog);
        this.name = dataName;
        this.id = name;
        this.c= context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up);
        service_heading = (TextViewBold) findViewById(R.id.delete_service_heading);
        cancel = (TextViewNormal) findViewById(R.id.cancel_action);
        delete = (TextViewNormal) findViewById(R.id.delete_action);

        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
        service_heading.setText(name);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.cancel_action)
        {
            delete_pop_up.this.dismiss();
        }
        if(v.getId() == R.id.delete_action)
        {

            for(int i = 0 ; i <MainActivity.cart_list.size() ; i++)
            {
                if(MainActivity.cart_list.get(i).getId().equals(id))
                {
                    MainActivity.cart_list.remove(i);
                    Toast.makeText(c , "item deleted from cart" , Toast.LENGTH_SHORT).show();

                    delete_pop_up.this.dismiss();
                    Cart.refresh_cart();
                    break;
                }
            }
        }
    }


}
