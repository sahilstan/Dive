package com.example.sahilsharma.dive.AssetListMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sahilsharma.dive.Constants;
import com.example.sahilsharma.dive.R;

/**
 * Created by sahilsharma on 2/28/19.
 */

public class AssetList extends Fragment{

    View v;
    String url = Constants.URL_SHOW_ASSETS;


//    ArrayList<String> AssetsOwnerDemo = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.asset_list, container, false);

        ListView lv =  v.findViewById(R.id.lv);
        Downloader d = new Downloader(getActivity(), url, lv);
        d.execute();


        return v;

    }



}
