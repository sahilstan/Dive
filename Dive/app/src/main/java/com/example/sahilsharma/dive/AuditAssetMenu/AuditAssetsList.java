package com.example.sahilsharma.dive.AuditAssetMenu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.AssetListMenu.AssetList;
import com.example.sahilsharma.dive.AssetListMenu.AssetManagement;
import com.example.sahilsharma.dive.AssetListMenu.Downloader;
import com.example.sahilsharma.dive.AuditReportMenu.AuditReport;
import com.example.sahilsharma.dive.Constants;
import com.example.sahilsharma.dive.R;
import com.example.sahilsharma.dive.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AuditAssetsList extends AppCompatActivity {

    String url_rm021 = Constants.URL_SHOW_ASSETS_RM021;
    String url_rm022 = Constants.URL_SHOW_ASSETS_RM022;
    String url_rm023 = Constants.URL_SHOW_ASSETS_RM023;

    ArrayList<AssetListDataFull> assetListDataFullAudit = new ArrayList<>();

    private String tagName;
    private String roomName;

    public EditText editTextTag;

//    String room = null;

    Button buttonTag;




    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_list_audit);

//        editTextRoom = findViewById(R.id.auditAssetEditText);
//        room = editTextRoom.getText().toString().trim();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }







        editTextTag = findViewById(R.id.editTextTag);
        editTextTag.getText().toString();

        buttonTag = findViewById(R.id.buttonTag);
//        buttonTag.setOnClickListener((View.OnClickListener) this);

        buttonTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Assets Audited", Toast.LENGTH_LONG).show();

                if(TextUtils.isEmpty(editTextTag.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter Tag", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(), editTextTag.toString(), Toast.LENGTH_LONG).show();
                    auditAssets();
                    saveTag();
                    String roomNo = getMyRoom();
                    String tagNo = getMyTag();

                    Log.i("FINAL ROOM IS", "----" + roomNo);
                    Log.i("FINAL TAG IS", "----" + tagNo);
                    String content = "Room number - " + roomNo + " is audited with Tag - " + tagNo ;

                    saveTextAsFile(content);

                    finish();
                }

            }
        });

        String room = getIntent().getExtras().getString("ROOM_KEY","defaultKey").trim().toLowerCase();

        Log.i("DEMO", room);

//        Intent i = this.getIntent();
//        final String room = i.getExtras().getString("ROOM_KEY", "none");
//
//        String demo =

        if (room.equals("rm021")) {
            ListView lvAudit = findViewById(R.id.lvAudit);
            DownloaderAudit d = new DownloaderAudit(this, url_rm021, lvAudit);
            d.execute();
        }else if(room.equals("rm022")){
            ListView lvAudit = findViewById(R.id.lvAudit);
            DownloaderAudit d = new DownloaderAudit(this, url_rm022, lvAudit);
            d.execute();
        }else if(room.equals("rm023")){
            ListView lvAudit = findViewById(R.id.lvAudit);
            DownloaderAudit d = new DownloaderAudit(this, url_rm023, lvAudit);
            d.execute();
        }else{
            Toast.makeText(this, "Please enter a valid Room Number", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void saveTextAsFile(String content){

        String filename = "Report.txt";

        //Create File

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);

        //write to file


        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File Not Found!", Toast.LENGTH_SHORT).show();

        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error Saving!", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private int saveTag(){

        final String roomNo = getIntent().getExtras().getString("ROOM_KEY","defaultKey").trim().toLowerCase();
        final String tag = editTextTag.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SAVE_TAG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("listDEMO", jsonArray.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("roomNo", roomNo);
                params.put("tag", tag);

                return params;
            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        tagName = tag;
        roomName = roomNo;

        Log.d("THIS IS THE ROOM", "---:" + roomName);
        Log.d("THIS IS THE TAG", "---:" + tagName);



//        Bundle bundle = new Bundle();
//        bundle.putString("TAG:", tag);
//        // set Fragmentclass Arguments
//        AuditReport fragobj = new AuditReport();
//        fragobj.setArguments(bundle);

//        Log.i(TAG,  tag);

//        Intent i = new Intent(AuditAssetsList.this, AuditReport.class);
//        i.putExtra("ROOM_KEY", roomNo);
//        i.putExtra("TAG_KEY", tag);

        return 1;

    }


    private void auditAssets() {

        final String roomNo = getIntent().getExtras().getString("ROOM_KEY","defaultKey").trim().toLowerCase();
//        Log.i(TAG, "auditAssets: Hello");
//        final String room = editTextRoom.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_AUDIT_ASSETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("listDEMO", jsonArray.toString());
                            AssetListDataFull m = null;

                            JSONObject jo = null;

//                            // LOOP THROUGH ARRAY
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                jo = jsonArray.getJSONObject(i);
//
//                                // GET NAME
//                                String audit = jo.getString("Audit");
//                                //For Full Details
//                                m = new AssetListDataFull();
//                                m.setAudit(audit);
//                                assetListDataFullAudit.add(m);
//                                finish();
//                                Log.i("DEMO-Name", audit);
//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("roomNo", roomNo);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }


    public String getMyRoom() {
        return roomName;
    }
    public String getMyTag() {
        return tagName;
    }
}
