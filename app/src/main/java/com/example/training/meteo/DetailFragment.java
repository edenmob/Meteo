package com.example.training.meteo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    public static final String TAG = DetailFragment.class.getSimpleName();

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle arguments = getArguments();

        if (arguments != null) {
            String forecastData = arguments.getString(Intent.EXTRA_TEXT);
            Log.d(TAG,arguments.toString());
            ((TextView) rootView.findViewById(R.id.detail_text))
                    .setText(forecastData);
        }

        return rootView;
    }
}
