package tech.inception.bookmyservices.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;

/**
 * Created by ghumman on 3/24/2017.
 */

public class cart_item_holder extends RecyclerView.ViewHolder {

    public TextViewNormal item_name , price , qty_text;
    public ImageView plus_img , minus_img;

    public cart_item_holder(View itemView) {
        super(itemView);

        item_name = (TextViewNormal) itemView.findViewById(R.id.item_name_id);
        price = (TextViewNormal) itemView.findViewById(R.id.price_id);
        plus_img = (ImageView) itemView.findViewById(R.id.plus_img);
        minus_img = (ImageView) itemView.findViewById(R.id.minus_img);
        qty_text = (TextViewNormal) itemView.findViewById(R.id.qty_text);

    }
}
