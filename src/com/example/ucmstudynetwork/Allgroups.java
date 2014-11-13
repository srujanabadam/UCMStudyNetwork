package com.example.ucmstudynetwork;

import java.util.Arrays;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;

public class Allgroups extends Activity
{
	 public SharedPreferences savedSearches; 
	 private TableLayout groupTableLayout;
	 
	 @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState); // call the superclass version
	      setContentView(R.layout.allgroups);
	      
	      savedSearches = getSharedPreferences("searches", MODE_WORLD_WRITEABLE);
	      
	      groupTableLayout = 
	    	         (TableLayout) findViewById(R.id.groupTableLayout);
	      
	      Button createGroup = (Button) findViewById(R.id.newgroupButton);
	      createGroup.setOnClickListener(newgroupButtonListener);	      
	      
	      /**************
	       * 
	       * get group names from table and display them
	       * 
	       */
	      
	      
	   }
	 
	 public OnClickListener newgroupButtonListener = new OnClickListener() 
	   {
		 public void onClick(View v) 
	      {
			 try
			 {
				 Intent intent = new Intent(Allgroups.this, CreateNewGroup.class); 
				 startActivity(intent);
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
	      }
	   };
	    
}