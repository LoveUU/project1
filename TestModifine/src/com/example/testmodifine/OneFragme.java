package com.example.testmodifine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OneFragme extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.one, null);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText("this is one!!");
		view.setBackgroundColor(getResources().getColor(R.color.white));
		return view;
	}
}
