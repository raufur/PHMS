package com.epsilon.coders.phms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.databases.DoctorProfileDataSource;
import com.epsilon.coders.phms.model.DoctorProfile;

import java.util.ArrayList;

public class DoctorListAdapter extends BaseAdapter {

    private ArrayList<DoctorProfile> doctorArrayList;
    private Context context;
    private LayoutInflater inflater;
    private DoctorProfileDataSource dataSource;

    public DoctorListAdapter(Context context, ArrayList<DoctorProfile> doctorArrayList) {
        this.context = context;
        this.doctorArrayList = doctorArrayList;
        inflater = LayoutInflater.from(context);
        dataSource = new DoctorProfileDataSource(context);
    }

    @Override
    public int getCount() {
        return doctorArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.doctor_list_item, parent, false);
            TextView tvName = (TextView) view.findViewById(R.id.nameView);
            TextView tvNumber=(TextView )view.findViewById(R.id.numberView);
            tvName.setText(doctorArrayList.get(position).getDname());
            tvName.setTag(doctorArrayList.get(position));
            tvNumber.setText(" "+doctorArrayList.get(position).getPhone());
            tvNumber.setTag(doctorArrayList.get(position));
//            tvName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    long id = ((DoctorProfile) v.getTag()).getId();
//                    Intent gotoDetailsActivity = new Intent(context, DisplayFamilyDetails.class);
//                    gotoDetailsActivity.putExtra("f_id", id);
//                    context.startActivity(gotoDetailsActivity);
//
//                }
//            });
        } else {
            view = convertView;
        }

        return view;
    }


}
