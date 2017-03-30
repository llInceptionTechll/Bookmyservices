package tech.inception.bookmyservices.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewBold;
import tech.inception.bookmyservices.custom.TextViewNormal;

/**
 * Created by ghumman on 3/5/2017.
 */

public class services_holder extends RecyclerView.ViewHolder {

    public TextViewBold service_name;
    public TextViewNormal service_description;
    public ImageView service_image;
    public RelativeLayout service_frame;

    public services_holder(View itemView) {
        super(itemView);

        service_name = (TextViewBold) itemView.findViewById(R.id.service_name);
        service_description = (TextViewNormal) itemView.findViewById(R.id.service_description);
        service_image = (ImageView) itemView.findViewById(R.id.service_image);
        service_frame = (RelativeLayout) itemView.findViewById(R.id.service_frame);
    }
}
