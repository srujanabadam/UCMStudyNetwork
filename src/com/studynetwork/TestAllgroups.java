package com.studynetwork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.ucmstudynetwork.R;
import com.studynetwork.util.DatabaseHelper;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class TestAllgroups extends ListActivity 
{
	public static final String ROW_ID = "row_id";
	private static final String[] StringArray = null;
	private ListView contactListView;
	private SimpleCursorAdapter contactAdapter;
	ListView listViewgroupList ;
	
	 @SuppressWarnings("deprecation")
	@Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState); 
	      contactListView = getListView();
	      
	     ListView listViewgroupList = (ListView)findViewById(R.id.listViewgroupList); 
	      ArrayAdapter adapter = new ArrayAdapter<String>(this,  
	              R.id.listViewgroupList, 
	              StringArray);
	      listViewgroupList.setAdapter(adapter);
	      
//	      String[] from = new String[] { "name" };
//	      int[] to = new int[] { R.id.groupTextView };
//	      contactAdapter = new SimpleCursorAdapter(TestAllgroups.this, R.layout.allgroups, null, from, to);
//	      setListAdapter(contactAdapter); 
	   }
	 
	/*   @Override
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
*/	 
	 public class getGroupList extends AsyncTask<Object, Object, Cursor> {				 

		@Override
		protected Cursor doInBackground(Object... params){
			Statement st = null;
			String sql = "select username from auth_user";
			ResultSet rs= null;

			return  (Cursor) rs;
		}
			
		@Override
		 protected void onPostExecute(Cursor result){			
			/*while (((ResultSet) result).next()) 
			{
			    String em = result.getString("username");
			    StringArray = em.split("\n");
			    for (int i =0; i < StringArray.length; i++){
			        System.out.println(StringArray[i]);
			    }
			}*/
			
			// set the adapter's Cursor
	         //db.CloseConnection(cn);
	      } 
	 }
	 
	 public OnClickListener newgroupButtonListener = new OnClickListener() 
	   {
		 public void onClick(View v) 
	      {
			 try
			 {
				// Intent intent = new Intent(TestAllgroups.this, CreateNewGroup.class); 
				// startActivity(intent);
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
	      }

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	   };
	    
}