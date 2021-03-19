package com.am.mywebserve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String API = "https://api.androidhive.info/contacts/",n1,n2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
    }

    public void getdat(View view) {
        MyServiceclas myServiceclas = new MyServiceclas();
        myServiceclas.execute();
    }

    private class MyServiceclas extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            GetNames getNames = new GetNames();

            String resut = getNames.makeServiceCall(API);

            //jsonParsing

            try {
                JSONObject jsonObject = new JSONObject(resut);

                JSONArray  jsonArray = jsonObject.getJSONArray("contacts");

                JSONObject jsonObject1 = jsonArray.getJSONObject(7);

                n1= jsonObject1.getString("name");

                JSONObject jsonObject2 = jsonArray.getJSONObject(8);

                n2= jsonObject2.getString("name");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            textView.setText(n1+"\n"+n2);
        }
    }
}