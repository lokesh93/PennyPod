package com.example.testpenny;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class optionspage extends Activity{
	
	//buttons
	ImageButton goalButton;
	ImageButton saveButton;
	ImageButton timeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optionspage);
		
		addListenerOnButton();
	}
	
	//activity change for each button
	
	public void addListenerOnButton(){
		goalButton = (ImageButton) findViewById(R.id.imageButton1);
		saveButton = (ImageButton) findViewById(R.id.imageButton2);
		timeButton = (ImageButton) findViewById(R.id.imageButton3);
		
		goalButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent goalIntent = new Intent(optionspage.this,goalpage.class);
				startActivity(goalIntent);
				finish();
			}
		});
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent goalIntent = new Intent(optionspage.this,savepage.class);
				startActivity(goalIntent);
				finish();
			}
		});
		
		timeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent goalIntent = new Intent(optionspage.this,timepage.class);
				startActivity(goalIntent);
				finish();
			}
		});
		
	}
	
		
		
}