package tech.inception.bookmyservices.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import tech.inception.bookmyservices.R;
import tech.inception.bookmyservices.custom.TextViewNormal;
import tech.inception.bookmyservices.datamodel.city_list;

/**
 * Created by ghumman on 2/28/2017.
 */

public class city_auto_list extends ArrayAdapter<city_list> {


    private final Context mContext;
    private final List<city_list> mcities;

    private final List<city_list> mcities_All;
    private final List<city_list> mcities_Suggestion;
    private final int mLayoutResourceId;

    public city_auto_list(Context context, int resource, List<city_list> mdistricts) {
        super(context, resource, mdistricts);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mcities = new ArrayList<>(mdistricts);
        this.mcities_All = new ArrayList<>(mdistricts);
        this.mcities_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mcities.size();
    }

    public city_list getItem(int position) {
        return mcities.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            city_list city = getItem(position);
            TextViewNormal name = (TextViewNormal) convertView.findViewById(R.id.list_text);
            name.setText(city.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((city_list) resultValue).getName();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mcities_Suggestion.clear();
                    for (city_list city : mcities_All) {
                        if (city.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            mcities_Suggestion.add(city);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mcities_Suggestion;
                    filterResults.count = mcities_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mcities.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof city_list) {
                            mcities.add((city_list) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mcities.addAll(mcities);
                }
                notifyDataSetChanged();
            }
        };
    }
}
