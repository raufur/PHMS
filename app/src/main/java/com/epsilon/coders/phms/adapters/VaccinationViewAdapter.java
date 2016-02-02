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
import com.epsilon.coders.phms.model.VaccinationChart;

import java.util.ArrayList;
import java.util.List;


public class VaccinationViewAdapter extends ArrayAdapter<VaccinationChart> {

	private static LayoutInflater inflater = null;

	List<VaccinationChart> allDailyVaccinationChart = new ArrayList<VaccinationChart>();

	public VaccinationViewAdapter(Activity context,
                                  List<VaccinationChart> allDailyVaccinationChart)
	{
		super(context, R.layout.diet_activity_list_view, allDailyVaccinationChart);
		
		this.allDailyVaccinationChart = allDailyVaccinationChart;
		
		/*********** Layout inflator to call external xml layout () ***********/
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {
		public TextView id;
		public TextView event;
		public TextView time;
		public TextView vaccinationDetails;
		public ImageView image;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate(R.layout.diet_activity_list_view, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.id = (TextView) vi.findViewById(R.id.viewId);
			
			holder.event = (TextView) vi.findViewById(R.id.viewEvent);
			
			holder.time = (TextView) vi.findViewById(R.id.viewTime);
			
			holder.vaccinationDetails = (TextView) vi.findViewById(R.id.viewManu);
			
			holder.image = (ImageView) vi.findViewById(R.id.imageAlarm);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} 
		else
			holder = (ViewHolder) vi.getTag();

		VaccinationChart mVaccinationChart = allDailyVaccinationChart.get(position);

		holder.id.setText(mVaccinationChart.getId().toString());
		
		holder.event.setText(mVaccinationChart.getVaccinationName().toString());

		holder.time.setText(mVaccinationChart.getTime().toString());

		holder.vaccinationDetails.setText(mVaccinationChart.getVaccinationDetails().toString());

		String mAlarm = mVaccinationChart.getAlarm();
		
		if (mAlarm == "0") {
			holder.image.setVisibility(View.GONE);
		} else {
			holder.image.setVisibility(View.VISIBLE);
		}
		return vi;
	}
}
