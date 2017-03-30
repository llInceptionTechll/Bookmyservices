package tech.inception.bookmyservices.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import tech.inception.bookmyservices.datamodel.offers_data;
import tech.inception.bookmyservices.fragments.Default_offer;

/**
 * Created by ghumman on 3/5/2017.
 */

public class offers_pager extends FragmentPagerAdapter {

    ArrayList<offers_data> al;
    public offers_pager(FragmentManager fm , ArrayList<offers_data> al)
    {

        super(fm);
        this.al = al;
    }
    @Override
    public Fragment getItem(int position) {

        offers_data data = al.get(position);

        Fragment defau = new Default_offer();
        Bundle b = new Bundle();
        b.putString("image",data.getOffer_image());
        b.putString("offer" , data.getOffer());

        defau.setArguments(b);
        return  defau;
    }

    @Override
    public int getCount() {
        return al.size();
    }
}
