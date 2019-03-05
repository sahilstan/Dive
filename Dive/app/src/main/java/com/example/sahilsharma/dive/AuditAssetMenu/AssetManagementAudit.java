package com.example.sahilsharma.dive.AuditAssetMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sahilsharma.dive.Constants;
import com.example.sahilsharma.dive.R;
import com.example.sahilsharma.dive.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class AssetManagementAudit extends AppCompatActivity {

    public String editTextValue;
    public EditText editTextCommentAssetListFull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_management_audit);

        final Button buttonCommentAssetListFull = findViewById(R.id.buttonCommentAssetListFull);
        editTextCommentAssetListFull = findViewById(R.id.editTextCommentAssetListFull);

        editTextValue = editTextCommentAssetListFull.getText().toString();



        TextView nameTextFull = findViewById(R.id.nameAssetListFull);
        TextView productTextFull = findViewById(R.id.productAssetListFull);
        TextView componentTextFull = findViewById(R.id.componentAssetListFull);
        TextView roomTextFull = findViewById(R.id.roomAssetListFull);
        TextView floorTextFull = findViewById(R.id.floorAssetListFull);
        TextView stateTextFull = findViewById(R.id.stateAssetListFull);
        TextView ownerTextFull = findViewById(R.id.ownerAssetListFull);
        TextView barcodeTextFull = findViewById(R.id.barcodeAssetListFull);
        TextView serialTextFull = findViewById(R.id.serialAssetListFull);
        TextView siteTextFull = findViewById(R.id.siteAssetListFull);
        TextView locationTextFull = findViewById(R.id.locationAssetListFull);


        Intent i = this.getIntent();

        int id = i.getIntExtra("ID_KEY", 0);
//        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        String name = i.getExtras().getString("NAME_KEY");
        String product = i.getExtras().getString("PRODUCT_KEY");
        String component = i.getExtras().getString("COMPONENT_KEY");
        String room = i.getExtras().getString("ROOM_KEY");
        String floor = i.getExtras().getString("FLOOR_KEY");
        String state = i.getExtras().getString("STATE_KEY");
        String owner = i.getExtras().getString("OWNER_KEY");
        String barcode = i.getExtras().getString("BARCODE_KEY");
        String serial = i.getExtras().getString("SERIAL_KEY");
        String site = i.getExtras().getString("SITE_KEY");
        String location = i.getExtras().getString("LOCATION_KEY");


//        Log.i("DEMO", id.);

        Log.d("MYINT", "value: " + id);
        nameTextFull.setText(name);
        productTextFull.setText(product);
        componentTextFull.setText(component);
        roomTextFull.setText(room);
        floorTextFull.setText(floor);
        stateTextFull.setText(state);
        ownerTextFull.setText(owner);
        barcodeTextFull.setText(barcode);
        serialTextFull.setText(serial);
        siteTextFull.setText(site);
        locationTextFull.setText(location);

        buttonCommentAssetListFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Comment Added!", Toast.LENGTH_LONG).show();
                addComment();
            }
        });


    }

    private void addComment() {

        final int id = getIntent().getIntExtra("ID_KEY", 0);
        final String idFinal = Integer.toString(id);

        final String comment = editTextCommentAssetListFull.getText().toString();;

        Log.d("The id in String---", idFinal);

        Log.d("The id", "In Integer---" + id);
        Log.d("The comment---", "::::-" + comment);



        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ADD_COMMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        try {
////                            JSONArray jsonArray = new JSONArray(response);
////                            Log.d("listDEMO", jsonArray.toString());
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
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
                params.put("comment", comment);
                params.put("id", idFinal);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
