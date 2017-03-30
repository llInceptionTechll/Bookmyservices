package tech.inception.bookmyservices.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;

/**
 * Created by ghumman on 3/12/2017.
 */

public class rate_holder extends RecyclerView.ViewHolder

{

    public TextViewNormal item_name , price ;

    public rate_holder(View itemView) {
        super(itemView);

        item_name = (TextViewNormal) itemView.findViewById(R.id.item_name_id);
        price = (TextViewNormal) itemView.findViewById(R.id.price_id);

    }
}
