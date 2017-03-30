package tech.inception.bookmyservices.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewBold;
import tech.inception.bookmyservices.custom.TextViewNormal;

/**
 * Created by ghumman on 3/5/2017.
 */

public class Default_offer extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.offer_default,container,false);

        ImageView img = (ImageView) v.findViewById(R.id.bgimage);
        img.setImageBitmap(StringToBitMap(getArguments().getString("image")));

        TextViewBold text = (TextViewBold) v.findViewById(R.id.offer_text);
        text.setText(getArguments().getString("offer"));


        return v;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}
