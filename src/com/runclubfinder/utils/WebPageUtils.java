/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author britzdawg
 */
public class WebPageUtils {
    
    public WebPageUtils(){
        
    }
    public String getWebPageSource(){
    
        String url = "http://www.dcroadrunners.org/activities/slr.html";
        String html = "";
        
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
    
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null){
                str.append(line);
            }
            
            in.close();
            html = str.toString();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return html;
        
    }
}
