package cs.dal.krush.tutorFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cs.dal.krush.R;
import cs.dal.krush.TutorCursorAdapters.SessionCursorAdapter;
import cs.dal.krush.models.DBHelper;

import static cs.dal.krush.R.id.map;

/**
 * TutorLocationFragment allows a user to set their preferred based on a location.
 *
 */
public class TutorLocationFragment extends Fragment implements OnMapReadyCallback {

    static int USER_ID;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_location, container, false);
        USER_ID = getArguments().getInt("USER_ID");


        //fetch UI elements:
        TextView pageTitle = (TextView)view.findViewById(R.id.tutorLocationHeader);
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.tutorMap);
        mapFrag.getMapAsync(this);


        //fetch custom app font:
        final Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FredokaOne-Regular.ttf");

        //set font style:
        pageTitle.setTypeface(typeFace);




        return view;
    }

    private void setUpMap() {
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(44.651070, -63.582687);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Halifax"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera( CameraUpdateFactory.zoomTo( 15.0f ) );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), null);

        setAddres("6050 University Avenue B3H");
    }

    private void setAddres(String address) {
//        String address = "6050 University Avenue B3H";
        // get address in string for used location for the map

        /* get latitude and longitude from the adderress */

        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address, 5);
            if (addresses.size() > 0)
            {
                Double lat = (double) (addresses.get(0).getLatitude());
                Double lon = (double) (addresses.get(0).getLongitude());

                Log.d("lat-long", "" + lat + "......." + lon);
                final LatLng user = new LatLng(lat, lon);
                /*used marker for show the location */
                Marker hamburg = mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(address)
                        // TODO: 2017-03-25 Create custom marker (if we have time) 
                        // .icon(BitmapDescriptorFactory.fromResource(R.drawable.star))
                );
                // Move the camera instantly to address with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }
}
