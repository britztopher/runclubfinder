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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runclubinfo);

        String dcrrc = "DC Road Runners Info";
//        TextView myTextView = (TextView) findViewById(R.id.club_name);
//        myTextView.setText(dcrrc);
        
        WebPageUtils wpu = new WebPageUtils();
        
        String src = wpu.getWebPageSource();   
        System.out.println(src);
        
//        TextView myHtmlSrc = (TextView) findViewById(R.id.html_src);
//        myHtmlSrc.setText(src);
        
        // ToDo add your GUI initialization code here        
    }

}
