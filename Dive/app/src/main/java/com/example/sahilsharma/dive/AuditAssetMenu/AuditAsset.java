package com.example.sahilsharma.dive.AuditAssetMenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.Constants;
import com.example.sahilsharma.dive.R;
import com.example.sahilsharma.dive.RequestHandler;
import com.example.sahilsharma.dive.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import com.example.sahilsharma.dive.AssetListDataFull;

/**
 * Created by sahilsharma on 2/28/19.
 */

public class AuditAsset extends Fragment implements View.OnClickListener {

    View v;
    private TextView textViewUserName, textViewUserEmail;
    public EditText editTextRoom;
    private Button button;
    ListView lv;

    ProgressDialog progressDialog;

    String url = Constants.URL_AUDIT_ASSETS_DATA;

    ArrayList<AssetListDataFull> assetListDataFullAudit = new ArrayList<>();



    Context c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.audit_asset, container, false);


        textViewUserName = v.findViewById(R.id.auditAssetUsernameTextView);
        textViewUserEmail = v.findViewById(R.id.auditAssetEmailTextView);

        textViewUserName.setText(SharedPrefManager.getInstance(c).getUsername());
        textViewUserEmail.setText(SharedPrefManager.getInstance(c).getUserEmail());

        editTextRoom = v.findViewById(R.id.auditAssetEditText);
        button = v.findViewById(R.id.auditAssetButton);
        button.setOnClickListener(this);

        String room2 = editTextRoom.getText().toString().trim();

//        Activity act = getActivity();
//        String roomName = ((AuditAssetsList)act).getMyRoom();
//        String tagName = ((AuditAssetsList)act).getMyTag();
//        Log.d("THIS IS THE ROOM", "---:" + roomName);
//        Log.d("THIS IS THE ROOM", "---:" + tagName);


//        progressDialog = new ProgressDialog(ProfileActivity.class);

        return v;
    }

    private int auditAssetList() {

        final String room = editTextRoom.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_AUDIT_ASSETS_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("list", jsonArray.toString());
                            AssetListDataFull m = null;

                            JSONObject jo = null;

                            // LOOP THROUGH ARRAY
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jo = jsonArray.getJSONObject(i);

                                int id = jo.getInt("id");

                                // GET NAME
                                String name = jo.getString("Name");
                                String product = jo.getString("Product");
                                String component = jo.getString("Component");
                                String room = jo.getString("Room");
                                String floor = jo.getString("Floor");
                                String state = jo.getString("State");
                                String owner = jo.getString("Owner");
                                String barcode = jo.getString("Barcode");
                                String site = jo.getString("Site");
                                String location = jo.getString("Location");


                                //For Full Details

                                m = new AssetListDataFull();
                                m.setId(id);
                                m.setName(name);
                                m.setProduct(product);
                                m.setComponent(component);
                                m.setRoom(room);
                                m.setFloor(floor);
                                m.setState(state);
                                m.setOwner(owner);
                                m.setBarcode(barcode);
                                m.setSite(site);
                                m.setLocation(location);

                                assetListDataFullAudit.add(m);
                                Log.d(TAG, name);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(c, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("room", room);
                return params;
            }
        };

        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);

        return 1;

    }

    @Override
    public void onClick(View v) {
        if ( v == button){
//            auditAssetList();

            Intent intent = new Intent(getActivity(), AuditAssetsList.class);
            intent.putExtra("ROOM_KEY", editTextRoom.getText().toString().trim());
            startActivity(intent);
            Log.i(TAG, editTextRoom.getText().toString().trim());
        }

    }


}
