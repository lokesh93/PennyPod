package com.example.testpenny;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class savepage extends Activity {
	
	//layout elements
	EditText target, periodLength;
	Spinner periodType;
	TextView daily, weekly, monthly;
	ImageButton calculate,back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savepage);
		
		//element declarations
		target = (EditText) findViewById(R.id.editText1);
		periodLength = (EditText) findViewById(R.id.editText2);
		periodType = (Spinner) findViewById(R.id.spinnergoal);
		daily = (TextView) findViewById(R.id.textView1);
		weekly = (TextView) findViewById(R.id.textView2);
		monthly = (TextView) findViewById(R.id.textView3);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		back = (ImageButton) findViewById(R.id.imageButton2);
		
		periodType = (Spinner) findViewById(R.id.spinnergoal);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.unit, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		periodType.setAdapter(adapter);
		
		//spinner and button listeners
		addListenerOnSpinnerItemSelection();
		addListenerOnButton();
		addListenerOnBackButton();
		
	}
	
	private void addListenerOnBackButton() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener(){
			
			//back button to options page
			public void onClick(View arg0){
				Intent optionsIntent = new Intent(savepage.this,optionspage.class);
				startActivity(optionsIntent);
				finish();
			}
		});
		
	}
	
	//calcul
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		periodType = (Spinner) findViewById(R.id.spinnergoal);
		periodType.setOnItemSelectedListener(new SpinnerActivity());
	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		
		target = (EditText) findViewById(R.id.editText1);
		periodLength = (EditText) findViewById(R.id.editText2);
		periodType = (Spinner) findViewById(R.id.spinnergoal);
		daily = (TextView) findViewById(R.id.textView1);
		weekly = (TextView) findViewById(R.id.textView2);
		monthly = (TextView) findViewById(R.id.textView3);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		
		
		 calculate.setOnClickListener(new OnClickListener() {
			 
			 @Override
            public void onClick(View arg0) {
            	double dailyVal = 0, weeklyVal = 0,monthlyVal = 0;
            	
  
        		target = (EditText) findViewById(R.id.editText1);
        		periodLength = (EditText) findViewById(R.id.editText2);
        		periodType = (Spinner) findViewById(R.id.spinnergoal);
        		daily = (TextView) findViewById(R.id.textView1);
        		weekly = (TextView) findViewById(R.id.textView2);
        		monthly = (TextView) findViewById(R.id.textView3);
        		
            	//convert to string
            	String period = periodType.getSelectedItem().toString();
            	String periodInput = periodLength.getText().toString();
            	String goalInput = target.getText().toString();
            	
            	//empty string check
            	if (periodInput.matches("")||goalInput.matches("")){
            		Context context = getApplicationContext();
            		Toast toast = Toast.makeText(context, "Enter a numerical value", Toast.LENGTH_SHORT);
            		toast.show();
            	}
            	
            	else{
            		
            		//covert to double
	            	double length = Double.parseDouble(periodInput);
	            	double goal = Double.parseDouble(goalInput);
	            	
	            	//check spinner selection
	            		
	            	if (period.equals("Days")) {
	            		
	            		//calculate values
	            		dailyVal = goal/length;
	            		weeklyVal = dailyVal*7;
	            		monthlyVal = dailyVal*30;	
	            	}
	            	if (period.equals("Weeks")) {
	            		weeklyVal = goal/length;
	            		dailyVal = weeklyVal/7;
	            		monthlyVal = dailyVal*30;
	            	}
	            	if (period.equals("Months")) {
	            		monthlyVal = goal/length;
	            		dailyVal = monthlyVal/30;
	            		weeklyVal = dailyVal*7;
	            	}
            	}
            	
            	//convert to string
            	
            	String dailyRate = String.valueOf(dailyVal);
            	String weeklyRate = String.valueOf(weeklyVal);
            	String monthlyRate = String.valueOf(monthlyVal);
            	
            	//display values
        		 daily.setText("$"+dailyRate);
        		 weekly.setText("$"+weeklyRate);
        		 monthly.setText("$"+monthlyRate);
			 }

		 });
		
	}
}
