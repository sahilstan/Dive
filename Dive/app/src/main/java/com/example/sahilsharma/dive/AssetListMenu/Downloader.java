package com.example.sahilsharma.dive.AssetListMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sahilsharma.dive.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sahilsharma on 3/2/19.
 */

public class Downloader extends AsyncTask<Void, Integer, String> {

    Context c;
    String address;
    ListView lv;
    ProgressDialog pd;

    public Downloader(Context c, String address, ListView lv) {
        this.c = c;
        this.address = address;
        this.lv = lv;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String data = downloadData();
        return data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data. Please wait!");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s != null){
            Parser p = new Parser(c, s, lv);
            p.execute();


        }else{
            Toast.makeText(c, "Unable to download Data", Toast.LENGTH_LONG).show();
        }

    }

    private String downloadData(){
        //connect and get stream

        InputStream is = null;
        String line = null;

        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuffer sb = new StringBuffer();

            if( br != null){
                while ((line = br.readLine()) != null){
                    sb.append(line + "\n");
                }
            }else{
                return null;
            }

            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
