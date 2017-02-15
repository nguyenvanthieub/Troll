package com.haynhanh.troll;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.haynhanh.troll.utils.Funs;
import com.haynhanh.troll.utils.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        if (Funs.isNetworkAvailable(this)) {
            try {
                setTitle("Test JSON");
                try {
                    String url = "";
                    new AsynLoad().execute(url);
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }
        } else {
            Toast.makeText(this, "Please check Wifi/3G", Toast.LENGTH_SHORT).show();
        }
    }

    private class AsynLoad extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url = params[0];
            JSONParser jParser = new JSONParser();
            List<NameValuePair> p = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url, "GET", p);
            try {
//                JSONObject pageInfo = json.getJSONObject("pageInfo");
//                int totalResults = pageInfo.getInt("totalResults");
//                JSONArray items = json.getJSONArray("items");
//                for (int i = 0; i < items.length(); i++) {
//                    JSONObject c = items.getJSONObject(i);
//                    JSONObject snippet = c.getJSONObject("snippet");
//                    String title = snippet.getString("title");
//                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
//                    JSONObject high = thumbnails.getJSONObject("default");
//                    String urlthumbnails = high.getString("url");
//                    JSONObject resourceId = snippet.getJSONObject("resourceId");
//                    String videoId = resourceId.getString("videoId");
//                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }

}
