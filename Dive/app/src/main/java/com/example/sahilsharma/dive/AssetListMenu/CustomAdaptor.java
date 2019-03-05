package com.example.sahilsharma.dive.AssetListMenu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.AssetListMenu.AssetManagement;
import com.example.sahilsharma.dive.R;

import java.util.ArrayList;

/**
 * Created by sahilsharma on 3/3/19.
 */

public class CustomAdaptor extends BaseAdapter {

    Context c;
    ArrayList<AssetListDataFull> assetListDataFull;
    LayoutInflater inflater;

    public CustomAdaptor(Context c, ArrayList<AssetListDataFull> assetListDataFull) {
        this.c = c;
        this.assetListDataFull = assetListDataFull;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return assetListDataFull.size();
    }

    @Override
    public Object getItem(int position) {
        return assetListDataFull.get(position);
    }

    @Override
    public long getItemId(int position) {
        return assetListDataFull.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_list_view, parent, false);
        }

        TextView nameText = convertView.findViewById(R.id.nameAssetList);
        TextView nameOwner = convertView.findViewById(R.id.ownerAssetList);
        TextView nameBarcode = convertView.findViewById(R.id.barcodeAssetList);
        TextView nameState = convertView.findViewById(R.id.stateAssetList);

        nameText.setText(assetListDataFull.get(position).getName());
        nameOwner.setText(assetListDataFull.get(position).getComponent());
        nameBarcode.setText(assetListDataFull.get(position).getBarcode());
        nameState.setText(assetListDataFull.get(position).getState());

        final AssetListDataFull m = (AssetListDataFull) this.getItem(position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, assetListDataFull.get(position).getName(), Toast.LENGTH_SHORT).show();

                openDetails(m.getName(), m.getProduct(), m.getComponent(), m.getRoom(), m.getFloor(), m.getState() ,m.getOwner(), m.getBarcode(),
                        m.getSerial(), m.getSite(), m.getLocation());
            }
        });

        return convertView;
    }

    private void openDetails(String name, String product, String component, String room, String floor, String state ,String owner
                                ,String barcode, String serial, String site, String location){

        Intent i = new Intent(c, AssetManagement.class);

        //DATA

        i.putExtra("NAME_KEY", name);
        i.putExtra("PRODUCT_KEY", product);
        i.putExtra("COMPONENT_KEY", component);
        i.putExtra("ROOM_KEY", room);
        i.putExtra("FLOOR_KEY", floor);
        i.putExtra("STATE_KEY", state);
        i.putExtra("OWNER_KEY", owner);
        i.putExtra("BARCODE_KEY", barcode);
        i.putExtra("SERIAL_KEY", serial);
        i.putExtra("SITE_KEY", site);
        i.putExtra("LOCATION_KEY", location);

        c.startActivity(i);
    }
}
