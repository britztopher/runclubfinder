/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.runclubfinder.controller.R;

/**
 *
 * @author britzdawg
 */
public class RunClubInfo extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runclubinfo);

        String dcrrc = "DCRRC";
        TextView myTextView = (TextView) findViewById(R.id.club_name); 
        myTextView.setText("My double value is " +dcrrc);

        // ToDo add your GUI initialization code here        
    }

}
