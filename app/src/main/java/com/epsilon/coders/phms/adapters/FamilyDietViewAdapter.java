package com.epsilon.coders.phms.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epsilon.coders.phms.R;
import com.epsilon.coders.phms.model.FamilyDietChart;

import java.util.ArrayList;
import java.util.List;


public class FamilyDietViewAdapter extends ArrayAdapter<FamilyDietChart> {

	private static LayoutInflater inflater = null;

	List<FamilyDietChart> allDailyFamilyDietChart = new ArrayList<FamilyDietChart>();

	public FamilyDietViewAdapter(Activity context,
                                 List<FamilyDietChart> allDailyFamilyDietChart)
	{
		super(context, R.layout.family_diet_activity_list_view, allDailyFamilyDietChart);
		
		this.allDailyFamilyDietChart = allDailyFamilyDietChart;
		
		/*********** Layout inflator to call external xml layout () ***********/
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {
		public TextView id;
		public TextView event;
		public TextView time;
		public TextView familyDietDetails;
		public ImageView image;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate(R.layout.family_diet_activity_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.id = (TextView) vi.findViewById(R.id.viewId);
			
			holder.event = (TextView) vi.findViewById(R.id.viewEvent);
			
			holder.time = (TextView) vi.findViewById(R.id.viewTime);
			
			holder.familyDietDetails = (TextView) vi.findViewById(R.id.viewManu);
			
			holder.image = (ImageView) vi.findViewById(R.id.imageAlarm);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} 
		else
			holder = (ViewHolder) vi.getTag();

		FamilyDietChart mFamilyDietChart = allDailyFamilyDietChart.get(position);

		holder.id.setText(mFamilyDietChart.getId().toString());
		
		holder.event.setText(mFamilyDietChart.getFamilyDietName().toString());

		holder.time.setText(mFamilyDietChart.getTime().toString());

		holder.familyDietDetails.setText(mFamilyDietChart.getFamilyDietDetails().toString());

		String mAlarm = mFamilyDietChart.getAlarm();
		
		if (mAlarm == "0") {
			holder.image.setVisibility(View.GONE);
		} else {
			holder.image.setVisibility(View.VISIBLE);
		}
		return vi;
	}
}
