package com.runclubfinder.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static final int ERROR_GEOCODER = -2;
    private static final int ERROR_SUCCESS = 0;
    private static final int ERROR_LOCATION_TIMEOUT = -1;
    
    private static final int DIALOG_NO_INFO = 1;
    private static final int DIALOG_ERROR_GPS = 2;
    private static final int USE_CURRENT_LOCATION = 0;
    private static final int USE_LOCATION_NAME = 1;

    private Geocoder coder = null;
    private List<Address> addresses = null;
    private EditText entry;
    private Button mButton;
    private Intent myIntent;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        entry = (EditText) findViewById(R.id.entry);     
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        
    }
   public void onClick(View view) {                
        myIntent = new Intent(getApplicationContext(), MainMenu.class);
           
        coder = new Geocoder(this);
        
        
        String locationName = entry.getText().toString();
        
        boolean isCurrentLocation = false;

        if(isCurrentLocation){
//                gpsManager.setGPSCallback(GeocoderSample.this);
//                gpsManager.startListening(GeocoderSample.this);
        }
        else if(locationName.length() <= 0){
            showDialog(DIALOG_NO_INFO);
            return;
        }

        new GeocodeTask().execute(isCurrentLocation ? USE_CURRENT_LOCATION : USE_LOCATION_NAME);
        startActivity(myIntent);
        
    }
   
    private int getLocationInfo(final String locationName){
        int result = ERROR_SUCCESS;
        
        try{
            addresses = coder.getFromLocationName(locationName, 1);
            myIntent.putExtra("ADDRESS", addresses.get(0).toString());
        }catch (Exception e){
            e.printStackTrace();
            result = ERROR_GEOCODER;
        }
        
        return result;
    }
    
    private String getGeocoderInfo(List<Address> addresses){
        StringBuilder builder = new StringBuilder();
        Address address = null;
        
        for(int index=0; index<addresses.size(); ++index){
            address = addresses.get(index);

            builder.append("Name: ").append(address.getLocality()).append("\n");
            builder.append("Sub-Admin Ares: ").append(address.getSubAdminArea()).append("\n");
            builder.append("Admin Area: ").append(address.getAdminArea()).append("\n");
            builder.append("Country: ").append(address.getCountryName()).append("\n");
            builder.append("Country Code: ").append(address.getCountryCode()).append("\n");
            builder.append("Latitude: ").append(address.getLatitude()).append("\n");
            builder.append("Longitude: ").append(address.getLongitude()).append("\n\n");
        }
        
        return builder.toString();
    }
    
    public ProgressDialog createProgressDialog(final Context context, final String message){
        final ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(message);
        progressDialog.setProgressDrawable(getWallpaper());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
                
        return progressDialog;
    }
        
    private int getErrorDialogIdFromCode(int code){
        int dialogid = 0;
        
        switch(code){
            case ERROR_LOCATION_TIMEOUT:    
                dialogid = DIALOG_ERROR_GPS;    
                break;
            case ERROR_GEOCODER:                    
                dialogid = DIALOG_ERROR_GPS;    
                break;
        }
        
        return dialogid; 
    }
   
    private class GeocodeTask extends AsyncTask<Integer, Integer, Integer>{
        private String locationName = "";
        private ProgressDialog progress = null;

        @Override
        protected Integer doInBackground(Integer... task){
                Integer result = 0;

                switch(task[0]){
                    case USE_CURRENT_LOCATION:{
//                                result = getcurrentLocationInfo();
//
//                                break;
                    }
                    case USE_LOCATION_NAME:{
                            result = getLocationInfo(locationName);
                            break;
                    }
                }

                return result;
        }

        @Override
        protected void onCancelled(){
                super.onCancelled();
        }

        @Override
        protected void onPostExecute(Integer result){
            progress.dismiss();

            if(null!= addresses && addresses.size() > 0){
                    ((TextView)findViewById(R.id.location_info)).setText(getGeocoderInfo(addresses));
            }
            else{
                    showDialog(getErrorDialogIdFromCode(result));
            }

            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute(){       

            locationName = ((EditText)findViewById(R.id.entry)).getText().toString();

            progress = createProgressDialog(MainActivity.this, getString(R.string.finding_loc));

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values){
                super.onProgressUpdate(values);
        }
    };    
//        myIntent.putExtra("ZIP_CODE", entry.getText().toString());
//        startActivity(myIntent);

   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = intent.getExtras();
        entry.setText(extras != null ? extras.getString("returnKey"):"nothing returned");
    }
}