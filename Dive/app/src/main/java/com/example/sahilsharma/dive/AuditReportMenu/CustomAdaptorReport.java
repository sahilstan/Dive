package com.example.sahilsharma.dive.AuditReportMenu;

/**
 * Created by sahilsharma on 3/5/19.
 */
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.AssetListMenu.AssetManagement;
import com.example.sahilsharma.dive.AuditAssetMenu.AssetManagementAudit;
import com.example.sahilsharma.dive.R;

import java.util.ArrayList;

/**
 * Created by sahilsharma on 3/3/19.
 */

public class CustomAdaptorReport extends BaseAdapter {

    Context c;
    ArrayList<AssetListDataFull> assetListDataFull;
    LayoutInflater inflater;

    public CustomAdaptorReport(Context c, ArrayList<AssetListDataFull> assetListDataFull) {
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
            convertView = inflater.inflate(R.layout.custom_list_view_report, parent, false);
        }

        TextView nameText = convertView.findViewById(R.id.nameAssetListAudit);
        TextView nameOwner = convertView.findViewById(R.id.ownerAssetListAudit);
        TextView nameBarcode = convertView.findViewById(R.id.barcodeAssetListAudit);
        TextView nameAudit = convertView.findViewById(R.id.stateAssetListAudit);

        nameText.setText(assetListDataFull.get(position).getRoom());
        nameOwner.setText(assetListDataFull.get(position).getComment());
        nameBarcode.setText(assetListDataFull.get(position).getTag());
        nameAudit.setText(assetListDataFull.get(position).getAudit());

        final AssetListDataFull m = (AssetListDataFull) this.getItem(position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, assetListDataFull.get(position).getName(), Toast.LENGTH_SHORT).show();


                openDetails(m.getId(), m.getName(), m.getProduct(), m.getComponent(), m.getRoom(), m.getFloor(), m.getState() ,m.getOwner(), m.getBarcode(),
                        m.getSerial(), m.getSite(), m.getLocation());

            }
        });

        return convertView;
    }

    private void openDetails(Integer id,String name, String product, String component, String room, String floor, String state ,String owner
            ,String barcode, String serial, String site, String location){

        Intent i = new Intent(c, AssetManagementAudit.class);

        //DATA

        i.putExtra("ID_KEY", id);
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

//        Log.i(TAG, "openDetails: ");

        c.startActivity(i);
    }
}