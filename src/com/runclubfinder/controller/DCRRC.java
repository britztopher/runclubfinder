/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.controller;

import com.runclubfinder.common.RunClubInfo;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 *
 * @author britzdawg
 */
public class DCRRC extends ListActivity implements ListView.OnItemClickListener{

    private Intent myIntent;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        String[] runClubMenu = getResources().getStringArray(R.array.run_club_menu);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.runclub, runClubMenu));
        
        ListView lv = getListView();
        lv.setOnItemClickListener(this);
        
        // ToDo add your GUI initialization code here        
    }
    
    public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
      // When clicked, show a toast with the TextView text

        Bundle extras = getIntent().getExtras();
        System.out.println(position);
        switch(position){
            case 0: {
               myIntent = new Intent(this, RunClubInfo.class);
               startActivity(myIntent);
               break;
            }
        }

        Toast.makeText(getApplicationContext(),"DCRRC", Toast.LENGTH_SHORT).show();
    }

}
