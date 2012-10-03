package com.example.ass2note.location;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.ass2note.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GoogleMapsActivity extends MapActivity implements LocationListener{
	private static final int MAPSINTENT_ID = 1;
	private double latitude=0, longitude=0;
	private LocationManager mLocationManager;
	private MapController mapController;
//	private Geocoder geocoder;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        
      //  getGpsCoordinates();
   
        // Fetch the mapView used to display the map, and enable zooming
        final MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        
        // Create the controller and zoom in the desired height
        mapController = mapView.getController(); //<4>
        mapController.setZoom(16);
        
        mLocationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
       // geocoder = new Geocoder(this);
        
        
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); //<5>
        if (location != null) {
          Log.d("TAG", location.toString());
          this.onLocationChanged(location); //<6>
        }
        
        /*
        mapView.setOnTouchListener(new View.OnTouchListener() {

        	public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                GeoPoint p = null;
System.out.println("hmm");
                if (event.getAction() == MotionEvent.ACTION_UP) {
                	System.out.println("action up");
                    p = mapView.getProjection().fromPixels((int) event.getX(),
                            (int) event.getY());
                   /* locationText.setText(p.getLatitudeE6() / 1E6 + ","
                            + p.getLongitudeE6() / 1E6 + "Action is : "
                            + event.getAction());
                    return true;*/
                 /*  Toast.makeText(
                            getBaseContext(),
                            p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6()
                                    / 1E6 + "Action is : " + event.getAction(),
                            Toast.LENGTH_SHORT).show();
                return true;
                }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                	return false;
                }
                return true;
            }
        });*/
        
       /* Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
        
        MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableMyLocation();
        
        GeoPoint point = new GeoPoint((int)(1E6*latitude), (int)(1E6*longitude));
        OverlayItem item = new OverlayItem(point, "", "");
        itemizedOverlay.clear();
        itemizedOverlay.addOVerlay(item);
        mapView.invalidate();
        */
       /* List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
        ItemizedOverlayClass itemizedoverlay = new ItemizedOverlayClass(drawable, this);
        
        GeoPoint point = new GeoPoint((int)latitude,(int)longitude);
        OverlayItem overlayitem = new OverlayItem(point, "Testheader", "Testinformation");
        
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
    */}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_google_maps, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void cancelMaps(View view){
		Intent i = new Intent();
		setResult(Activity.RESULT_CANCELED, i);
		finish();
	}
	
	public void confirmMaps(View view){
		Intent i = new Intent();
		i.putExtra("test", "Confirmed");
		setResult(Activity.RESULT_OK, i);
		finish();
	}
	
	/*public void getGpsCoordinates(){
		mLocationManager = (LocationManager) 
        		this.getSystemService(Context.LOCATION_SERVICE);
        
     // Retrieve a list of location providers that have fine accuracy, no monetary cost, etc
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);
        
        String providerName = mLocationManager.getBestProvider(criteria, true);

        // If no suitable provider is found, null is returned.
        if (providerName != null) {
           System.out.println("no location providor found");
        }
        
       /* LocationProvider provider =
                locationManager.getProvider(LocationManager.GPS_PROVIDER);*/
        
   /*     final LocationListener listener = new LocationListener() {

            public void onLocationChanged(Location location) {
                // A new location update is received.  Do something useful with it.  In this case,
                // we're sending the update to a handler which then updates the UI with the new
                // location.
               
                System.out.println("latitude: "+location.getLatitude());
                System.out.println("longitude: "+location.getLongitude());
                	latitude = location.getLatitude();
                	longitude = location.getLongitude();
                }

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
            
        };

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000,          // 10-second interval.
                10,             // 10 meters.
                listener);
        
	}*/

	public void onLocationChanged(Location location) {
		 Log.d("TAG", "onLocationChanged with location " + location.toString());
		    String text = String.format("Lat:\t %f\nLong:\t %f\nAlt:\t %f\nBearing:\t %f", location.getLatitude(), 
		                  location.getLongitude(), location.getAltitude(), location.getBearing());
		 //   this.locationText.setText(text);
		    
		    try {
		//      List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10); //<10>
		   /*   for (Address address : addresses) {
		        this.locationText.append("\n" + address.getAddressLine(0));
		      }
		     */ 
		      int latitude = (int)(location.getLatitude() * 1000000);
		      int longitude = (int)(location.getLongitude() * 1000000);

		      GeoPoint point = new GeoPoint(latitude,longitude);
		      mapController.animateTo(point); //<11>
		      

			  mLocationManager.removeUpdates(this); 
		      
		    } catch (Exception e) {
		      Log.e("LocateMe", "Could not get Geocoder data", e);
		    }
		  
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	 protected void onResume() {
		    super.onResume();
		    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this); //<7>
		  }
	 @Override
	  protected void onPause() {
	    super.onPause();
	    mLocationManager.removeUpdates(this); //<8>
	  }
}
