package cs.dal.krush.tutorFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs.dal.krush.R;
import cs.dal.krush.TutorCursorAdapters.SessionCursorAdapter;
import cs.dal.krush.models.DBHelper;

/**
 * TutorLocationFragment allows a user to set their preferred based on a location.
 *
 */
public class TutorLocationFragment extends Fragment implements OnMapReadyCallback {

    static int USER_ID;
    private GoogleMap mMap;
    private SupportMapFragment mapFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_location, container, false);
        USER_ID = getArguments().getInt("USER_ID");

        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.tutorMap);
        mapFrag.getMapAsync(this);



        return view;
    }

    private void setUpMap() {
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }
}
