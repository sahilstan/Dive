package com.example.sahilsharma.dive.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sahilsharma.dive.R;

/**
 * Created by sahilsharma on 2/28/19.
 */

public class Signout extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.asset_list, container, false);
        return v;
    }


}
