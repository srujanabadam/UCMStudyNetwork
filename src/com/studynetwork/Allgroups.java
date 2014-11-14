package com.studynetwork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import com.example.ucmstudynetwork.R;
import com.studynetwork.util.DatabaseHelper;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;

public class Allgroups extends ListActivity 
{
	 public SharedPreferences savedSearches; 
	 private TableLayout groupTableLayout;
	 private ListView contactListView; // the ListActivity's ListView
	 private CursorAdapter contactAdapter;
	 
	 @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState); // call the superclass version
	      //setContentView(R.layout.allgroups);
	      
	      //savedSearches = getSharedPreferences("searches", MODE_WORLD_WRITEABLE);
	      contactListView = getListView();
	      //contactListView.setOnItemClickListener(viewContactListener); 
	      
	      String[] from = new String[] { "name" };
	      int[] to = new int[10];// { R.id.groupTextView };
	      contactAdapter = new SimpleCursorAdapter(Allgroups.this, R.layout.allgroups, null, from, to);
	      setListAdapter(contactAdapter); // set contactView's adapter
	      	      
	      Button createGroup = (Button) findViewById(R.id.newgroupButton);
	      createGroup.setOnClickListener(newgroupButtonListener);	      
	      
	      /**************
	       * 
	       * get group names from table and display them
	       * 
	       */
	     // new getGroupList().execute("");
	   }
	 
	   @Override
	   protected void onResume() 
	   {
	      super.onResume(); // call super's onResume method
	      
	       // create new GetContactsTask and execute it 
	       new getGroupList().execute((Object[]) null);
	    } // end method onResume
	   
	   @Override
	   protected void onStop() 
	   {
	      Cursor cursor = contactAdapter.getCursor(); // get current Cursor
	      
	      if (cursor != null) 
	         cursor.deactivate(); // deactivate it
	      
	      contactAdapter.changeCursor(null); // adapted now has no Cursor
	      super.onStop();
	   } // end method onStop
	 
	 public class getGroupList extends AsyncTask<Object, Object, Cursor> 
	 {
		 DatabaseHelper db = new DatabaseHelper();		 

		@Override
		protected Cursor doInBackground(Object... params) {
			Statement st = null;
			String sql = "select username from auth_user";
			ResultSet rs= null;

			return (Cursor) rs;
		}
			
		@Override
		 protected void onPostExecute(Cursor result)
	      {
	         contactAdapter.changeCursor(result); // set the adapter's Cursor
	        // db.CloseConnection(cn);
	      } 
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