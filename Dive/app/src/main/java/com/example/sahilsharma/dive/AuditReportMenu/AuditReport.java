package com.example.sahilsharma.dive.AuditReportMenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.AssetListMenu.Downloader;
import com.example.sahilsharma.dive.AuditAssetMenu.AuditAsset;
import com.example.sahilsharma.dive.AuditAssetMenu.AuditAssetsList;
import com.example.sahilsharma.dive.Constants;
import com.example.sahilsharma.dive.R;

import java.util.ArrayList;

/**
 * Created by sahilsharma on 2/28/19.
 */

public class AuditReport extends Fragment {
    String url = Constants.URL_SHOW_ASSETS;
    String demo;
    String strtext;

    ArrayList<AssetListDataFull> assetListDataFull;

    String roomName = "";
    String tagName = "";


    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.audit_report, container, false);

        ListView lvReport =  v.findViewById(R.id.lvReport);
        DownloaderReport d = new DownloaderReport(getActivity(), url, lvReport);
        d.execute();


//        Bundle bundle = getActivity().getIntent().getExtras();
//        if (bundle != null)
//        {
//            strtext = getArguments().getString("TAG");
//        }

//        AuditReportList auditReportList = (AuditReportList) getActivity();



//        demo = getActivity().getIntent().getExtras().getString("ROOM_KEY");
//        Log.d("THIS IS THE ROOM", demo);

//        assetListDataFull.get


        return v;
    }
}
