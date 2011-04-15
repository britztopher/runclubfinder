/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.runclubfinder.controller.R;
import com.runclubfinder.utils.WebPageUtils;

/**
 *
 * @author britzdawg
 */
public class RunClubInfo extends Activity {

     String INFO_URL = "http://www.dcroadrunners.org/about-us/membership-information.html";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runclubinfo);

        String dcrrc = "DC Road Runners Info";
        String info = "";
    
        TextView myTextView = (TextView) findViewById(R.id.club_name);
        myTextView.setText(dcrrc);

        TextView club_info = (TextView) findViewById(R.id.club_info);

        
        try{
            
            info = WebPageUtils.getElementsFromName("/p", INFO_URL);
            club_info.setText(info);

        }catch(Exception e){
            e.printStackTrace();
        }
        
//        TextView myHtmlSrc = (TextView) findViewById(R.id.html_src);
//        myHtmlSrc.setText(src);
        
        // ToDo add your GUI initialization code here        
    }

}
