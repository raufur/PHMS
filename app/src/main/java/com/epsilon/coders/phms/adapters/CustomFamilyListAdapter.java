package com.epsilon.coders.phms.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.activities.DisplayFamilyDetails;
import com.epsilon.coders.phms.databases.FamilyProfileDataSource;
import com.epsilon.coders.phms.model.FamilyProfileClass;

import java.util.ArrayList;

/**
 * Created by nilima on 10/13/2015.
 */
public class CustomFamilyListAdapter extends BaseAdapter {

    private ArrayList<FamilyProfileClass> familyArrayList;
    private Context context;
    private LayoutInflater inflater;
    private FamilyProfileDataSource dataSource;

    public CustomFamilyListAdapter(Context context, ArrayList<FamilyProfileClass> familyArrayList) {
        this.context = context;
        this.familyArrayList = familyArrayList;
        inflater = LayoutInflater.from(context);
        dataSource = new FamilyProfileDataSource(context);
    }

    @Override
    public int getCount() {
        return familyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return familyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.all_family_list_item, parent, false);
            TextView tvName = (TextView) view.findViewById(R.id.nameView);
            tvName.setText(familyArrayList.get(position).getFname());
            tvName.setTag(familyArrayList.get(position));
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = ((FamilyProfileClass) v.getTag()).getFid();
                    Intent gotoDetailsActivity = new Intent(context, DisplayFamilyDetails.class);
                    gotoDetailsActivity.putExtra("f_id", id);
                    context.startActivity(gotoDetailsActivity);

                }
            });
        } else {
            view = convertView;
        }

        return view;
    }


}