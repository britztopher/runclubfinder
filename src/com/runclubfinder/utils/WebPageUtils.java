/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.runclubfinder.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.xml.sax.SAXException;

/**
 *
 * @author britzdawg
 */
public class WebPageUtils {

        // EXAMPLE XPATH QUERIES IN THE FORM OF STRINGS - WILL BE USED LATER

    private static final String DCRR_INFO = "//p";

    // TAGNODE OBJECT, ITS USE WILL COME IN LATER
    private static TagNode node;

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
    // A METHOD THAT HELPS ME RETRIEVE THE STOCK OPTION'S DATA BASED OFF THE NAME
    //(I.E. GOUAA IS ONE OF GOOGLE'S STOCK OPTIONS)
    public static String getElementsFromName(String xpathExp, String webpage) throws XPatherException,
            ParserConfigurationException,SAXException, IOException, XPatherException {

        // THE URL WHOSE HTML I WANT TO RETRIEVE AND PARS
        String date = "";
        URLConnection conn = null;
        
        try{
            // THIS IS WHERE THE HTMLCLEANER COMES IN, I INITIALIZE IT HERE
            HtmlCleaner cleaner = new HtmlCleaner();
            CleanerProperties props = cleaner.getProperties();
            props.setAllowHtmlInsideAttributes(true);
            props.setAllowMultiWordAttributes(true);
            props.setRecognizeUnicodeChars(true);
            props.setOmitComments(true);

            // OPEN A CONNECTION TO THE DESIRED URL
            URL url = new URL(webpage);
            conn = url.openConnection();

           //USE THE CLEANER TO "CLEAN" THE HTML AND RETURN IT AS A TAGNODE OBJECT
            node = cleaner.clean(new InputStreamReader(conn.getInputStream()));

        }catch(Exception e){
            e.printStackTrace();
        }
        
        // ONCE THE HTML IS CLEANED, THEN YOU CAN RUN YOUR XPATH EXPRESSIONS ON THE NODE,
        //WHICH WILL THEN RETURN AN ARRAY OF TAGNODE OBJECTS (THESE ARE RETURNED
        //AS OBJECTS BUT GET CASTED BELOW)
        Object[] time_nodes = node.evaluateXPath(DCRR_INFO);

        // HERE I JUST DO A SIMPLE CHECK TO MAKE SURE THAT MY XPATH WAS CORRECT
        //AND THAT AN ACTUAL NODE(S) WAS RETURNED

            try{
                TagNode time_node = (TagNode) time_nodes[0];
                date = time_node.getText().toString();

            // DATE RETURNED IN 15-JAN-10 FORMAT, SO THIS IS SOME METHOD I
            //WROTE TO JUST PARSE THAT STRING INTO THE FORMAT THAT I USE

                System.out.println(date);
            }catch(Exception e){
                e.printStackTrace();
            }

    return date;

      }

}


