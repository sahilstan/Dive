package com.example.sahilsharma.dive.AssetListMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sahilsharma.dive.AssetListDataFull;
import com.example.sahilsharma.dive.AssetListMenu.CustomAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by sahilsharma on 3/2/19.
 */

public class Parser extends AsyncTask<Void, Integer, Integer> {

    Context c;
    ListView lv;
    String data;

    ArrayList<String> AssetsNames = new ArrayList<>();
    ArrayList<String> AssetsOwner = new ArrayList<>();
    ArrayList<String> AssetsBarcode = new ArrayList<>();
    ArrayList<String> AssetsState = new ArrayList<>();
    ArrayList<AssetListDataFull> assetListDataFull = new ArrayList<>();


    ProgressDialog pd;

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("ParserAuditAssets");
        pd.setMessage("Parsing the data. Please wait!");
        pd.show();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1){

            //Call CustomAdaptorAuditAssets for List View

            CustomAdaptor customAdaptor = new CustomAdaptor(c, assetListDataFull);
            lv.setAdapter(customAdaptor);

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, AssetsBarcode);
//            lv.setAdapter(adapter);

//            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(c, android.R.layout.list_content, AssetsOwner);
//            lv.setAdapter(adapter2);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view, AssetsNames.get(position), Snackbar.LENGTH_SHORT).show();
                }
            });


        }else{
            Toast.makeText(c, "Unable to parse the data", Toast.LENGTH_LONG).show();
        }
        pd.dismiss();
    }

    private int parse(){

        try{
            // new JSONObject(jsonData).getJSONArray("result"));
            JSONObject jo = null;

            JSONArray ja = new JSONArray(data);

            AssetsNames.clear();
            AssetsOwner.clear();

            AssetListDataFull m = null;

            // LOOP THROUGH ARRAY
            for (int  i = 0; i < ja.length(); i++){
                jo = ja.getJSONObject(i);

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

                assetListDataFull.add(m);

//                Log.d("tag2", jo.toString(4));
                Log.d(TAG, name);
            }
            return 1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
