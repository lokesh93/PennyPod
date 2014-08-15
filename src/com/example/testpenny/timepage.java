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

public class timepage extends Activity {
	
	// user inputs and outputs of layouts
	EditText goalInput, rateInput;
	ImageButton calculate,back;
	TextView days, weeks, months;
	Spinner periodInput;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timepage);	
		
		//find the layout view the specified inputs
		goalInput = (EditText) findViewById(R.id.editText1);
		rateInput = (EditText) findViewById(R.id.editText2);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		back = (ImageButton) findViewById(R.id.imageButton2);
		
		periodInput = (Spinner) findViewById(R.id.spinnergoal);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.period, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		periodInput.setAdapter(adapter);
		
		//listener for spinner (drop-down menu)
		addListenerOnSpinnerItemSelection();
		
		//listener for calculate button
		addListenerOnButton();
	    addListenerOnBackButton();
	}
	
	private void addListenerOnBackButton() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0){
				Intent optionsIntent = new Intent(timepage.this,optionspage.class);
				startActivity(optionsIntent);
				finish();
			}
		});
		
	}


	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		goalInput = (EditText) findViewById(R.id.editText1);
		rateInput = (EditText) findViewById(R.id.editText2);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		days = (TextView) findViewById(R.id.textView1);
		weeks = (TextView) findViewById(R.id.textView2);
		months = (TextView) findViewById(R.id.textView3);
		periodInput = (Spinner) findViewById(R.id.spinnergoal);
		
		calculate.setOnClickListener(new OnClickListener() {
			 
			 @Override
           public void onClick(View arg0) {
			
			int daysVal = 0,weeksVal = 0,monthsVal = 0;
           	
			periodInput = (Spinner) findViewById(R.id.spinnergoal);
           	goalInput = (EditText) findViewById(R.id.editText1);
    		rateInput = (EditText) findViewById(R.id.editText2);
    		days = (TextView) findViewById(R.id.textView1);
    		weeks = (TextView) findViewById(R.id.textView2);
    		months = (TextView) findViewById(R.id.textView3);
           	
           	//convert to string
           	String period = periodInput.getSelectedItem().toString();
           	String goalIn = goalInput.getText().toString();
           	String rateIn = rateInput.getText().toString();
           	
           	//check empty string
           	if (goalIn.matches("")||rateIn.matches("")){
           		//goalInput.setText("Invalid Entry");
           		//rateInput.setText("Invalid Entry");
           		Context context = getApplicationContext();
        		Toast toast = Toast.makeText(context, "Enter a numerical value", Toast.LENGTH_SHORT);
        		toast.show();
           	}
           	
           	else{
           		//convert to double
	           	double goal = Double.parseDouble(goalIn);
	           	double rate = Double.parseDouble(rateIn);
	           	
	           	//check spinner selection and calculate daily rate
	           	if (period.equals("Daily")) {
	           		rate = rate*1;
	           	}
	           	if (period.equals("Weekly")) {
	           		rate = rate/7;
	           	}
	           	if (period.equals("Monthly")) {
	           		rate = rate/30;
	           	}
	           	
	           	//calculate number of days 
	           	double numberofDays = goal/rate;
	           	
	         //check domain to display values in terms of weeks, months and years
	           	//check to see if its over a week and under a month
	           	if (6<numberofDays && numberofDays<30){
	           		weeksVal = (int)(numberofDays/7); //calculate number of whole weeks
	           		daysVal = (int)(numberofDays%7);//calculate number of whole days
	           	}
	           	//check to see if over a month
	        	if (30<numberofDays){
	           		monthsVal = (int)(numberofDays/30);//calculate number of whole months
	           		numberofDays = numberofDays%30;//calculate number of whole days
	           		// check if number of whole days can amount to a week or more
	           		if (6<numberofDays && numberofDays<30){
	           			//convert to weeks and days
	               		weeksVal = (int)(numberofDays/7);
	               		daysVal = (int)(numberofDays%7);
	               	}
	           	}
	        	//check to see if under a week
	        	else{
	        		daysVal = (int)(numberofDays%7);
	        	}
           	}
           	
           	//convert to string
           	String dailyRate = String.valueOf(daysVal);
           	String weeklyRate = String.valueOf(weeksVal);
           	String monthlyRate = String.valueOf(monthsVal);
           	
           	//display to layout
       		 days.setText(dailyRate);
       		 weeks.setText(weeklyRate);
       		 months.setText(monthlyRate);
			 }

		 });
		
	}


	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		periodInput = (Spinner) findViewById(R.id.spinnergoal);
		periodInput.setOnItemSelectedListener(new SpinnerActivity());
	}

}
