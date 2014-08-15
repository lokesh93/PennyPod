package com.example.testpenny;



import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class goalpage extends Activity {
	//declare layout elements
	Spinner spinner;
	ImageButton calculate,back;
	EditText goalText;
	EditText threeM,sixM,oneY;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goalpage);
		
		goalText = (EditText) findViewById(R.id.editText1);
		threeM = (EditText) findViewById(R.id.textView1);
		sixM = (EditText) findViewById(R.id.textView2);
		oneY = (EditText) findViewById(R.id.textView3);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		back = (ImageButton) findViewById(R.id.imageButton2);
		
		
		spinner = (Spinner) findViewById(R.id.spinnergoal);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.period, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		//action listeners
		addListenerOnSpinnerItemSelection();
		addListenerOnButton();
		addListenerOnBackButton();
		
	    
	}
	
	private void addListenerOnBackButton() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0){
				Intent optionsIntent = new Intent(goalpage.this,optionspage.class);
				startActivity(optionsIntent);
				finish();
			}
		});
		
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById(R.id.spinnergoal);
		spinner.setOnItemSelectedListener(new SpinnerActivity());
	  }
	
	public void addListenerOnButton() {
		//find layout elements
		goalText = (EditText) findViewById(R.id.editText1);
		threeM = (EditText) findViewById(R.id.textView1);
		sixM = (EditText) findViewById(R.id.textView2);
		oneY = (EditText) findViewById(R.id.textView3);
		calculate = (ImageButton) findViewById(R.id.imageButton1);
		
		
		 calculate.setOnClickListener(new OnClickListener() {
		 
			 @Override
            public void onClick(View arg0) {
            	double threeMonth = 0, sixMonth = 0,oneYear = 0;
            	goalText = (EditText) findViewById(R.id.editText1);
            	threeM = (EditText) findViewById(R.id.textView1);
        		sixM = (EditText) findViewById(R.id.textView2);
        		oneY = (EditText) findViewById(R.id.textView3);
        		spinner = (Spinner) findViewById(R.id.spinnergoal);
            	
        		//convert inputs to string
            	String periodInput = spinner.getSelectedItem().toString();
            	String userInput = goalText.getText().toString();
            	
            	//check for empty input
            	if (userInput.matches("")){
            		//display error message
            		Context context = getApplicationContext();
            		Toast toast = Toast.makeText(context, "Enter a numerical value", Toast.LENGTH_SHORT);
            		toast.show();
            	}
            	
            	else{
            		//convert to double
            		double savingRate = Double.parseDouble(userInput);
            		
            		//check spinner selections
	            	if (periodInput.equals("Daily")) {
	            		//calculate three month, six month and one year value
	            		threeMonth = savingRate*91;
	            		sixMonth = savingRate*182;
	            		oneYear = savingRate*365;
	            	}
	            	if (periodInput.equals("Weekly")) {
	            		//calculate three month, six month and one year value
	            		threeMonth = savingRate*13;
	            		sixMonth = savingRate*26;
	            		oneYear = savingRate*52;
	            	}
	            	if (periodInput.equals("Monthly")) {
	            		//calculate three month, six month and one year value
	            		threeMonth = savingRate*3;
	            		sixMonth = savingRate*6;
	            		oneYear = savingRate*12;
	            	}
            	}
            	
            	//convert to string
            	String threeVal = String.valueOf(threeMonth);
            	String sixVal = String.valueOf(sixMonth);
            	String oneVal = String.valueOf(oneYear);
            	
            	//display to layout
        		 threeM.setText("$"+ threeVal);
        		 sixM.setText("$"+ sixVal);
        		 oneY.setText("$"+ oneVal);
			 }

		 });
	}
}
            		
            			
            	
         



