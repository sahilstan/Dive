package com.example.sahilsharma.dive.AssetListMenu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sahilsharma.dive.R;

import java.util.ArrayList;

public class AssetManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_management);


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




    }


}
