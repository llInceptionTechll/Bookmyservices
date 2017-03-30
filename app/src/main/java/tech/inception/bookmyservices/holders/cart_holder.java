package tech.inception.bookmyservices.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;

/**
 * Created by ghumman on 3/13/2017.
 */

public class cart_holder extends RecyclerView.ViewHolder {

    public TextViewNormal itemName;
    public ImageView delete;

    public cart_holder(View itemView) {
        super(itemView);

        itemName = (TextViewNormal) itemView.findViewById(R.id.cart_item_name);
        delete = (ImageView) itemView.findViewById(R.id.delete_image);

    }
}
