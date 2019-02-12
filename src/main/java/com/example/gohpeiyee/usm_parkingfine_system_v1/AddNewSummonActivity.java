package com.example.gohpeiyee.usm_parkingfine_system_v1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class AddNewSummonActivity extends AppCompatActivity {

    EditText ownerEmail;
    EditText summonDate;
    EditText summonTime;
    EditText clampedVenue;
    EditText clapmpedAmount;
    EditText plateNum;
    Button captureCarPlate;
    @SuppressLint("WrongViewCast")
    Button addSummon;

    String URL="http://192.168.0.154/android-usm/summons/newSummon.php";

    JSONParser jsonParser=new JSONParser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_summon);
        final Context context = this;

        ownerEmail = (EditText)findViewById(R.id.ownerEmail);
        summonDate = (EditText)findViewById(R.id.summonDate);
        summonTime = (EditText)findViewById(R.id.summonTime);
        clampedVenue = (EditText)findViewById(R.id.clampedVenue);
        clapmpedAmount = (EditText)findViewById(R.id.clampedAmount);
        captureCarPlate = (Button) findViewById(R.id.capturePlate);
        plateNum = (EditText)findViewById(R.id.carPlateNum);
        addSummon = (Button) findViewById(R.id.addSummon);

        captureCarPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, captureCarPlateActivity.class);
                startActivity(intent);
            }
        });

        addSummon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class AttemptAddSummon extends AsyncTask<String, String, JSONObject>{
        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String ownerEmail = args[0];
            String summonDate = args[1];
            String summonTime = args[2];
            String clampedVenue = args[3];
            String clampedAmount = args[4];
            String plateNumber = args[5];

            ArrayList params = new ArrayList();

            if (ownerEmail.length() > 0) {
                params.add(new BasicNameValuePair("email", ownerEmail));

            }
            if( summonDate.length() >0) {
                params.add(new BasicNameValuePair("summon Date", summonDate));
            }

            if( summonTime.length() >0 ) {
                params.add(new BasicNameValuePair("summon time", summonTime));
            }
            if( clampedVenue.length() >0 ){
                params.add(new BasicNameValuePair("clamping venue", clampedVenue));
            }

            if( clapmpedAmount.length() >0 ){
                params.add(new BasicNameValuePair("clamp amount", clampedAmount));
            }

            if(plateNumber.length()>0)
            {
                params.add(new BasicNameValuePair("car plate number", plateNumber));
            }

            Log.i("summon time", summonTime);
            Log.i("summon Date", summonDate);
            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Log.i("tagconvertstr", "["+result+"]");
            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(), result.getString("message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
