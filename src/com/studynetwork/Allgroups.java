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
	      setContentView(R.layout.allgroups);
	      
	      	     
	   }
	 
	 	    
}