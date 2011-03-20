/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.location.Address;
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
public class MainMenu extends ListActivity implements ListView.OnItemClickListener{

    private Address mIntentString;
    private Intent myIntent;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] mainMenu = getResources().getStringArray(R.array.main_menu_array);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.start_list, mainMenu));
        
        ListView lv = getListView();
        lv.setOnItemClickListener(this);
        
    }
    public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
      // When clicked, show a toast with the TextView text

        Bundle extras = getIntent().getExtras();
        mIntentString =(Address)extras.get("ADDRESS");
        
        
        switch(position){
            case 4:{
                myIntent = new Intent(this, DCRRC.class);
                startActivity(myIntent);
                break;
            }
                
        }
        Toast.makeText(getApplicationContext(),mIntentString.getLocality(), Toast.LENGTH_SHORT).show();


   
    }
 
}
