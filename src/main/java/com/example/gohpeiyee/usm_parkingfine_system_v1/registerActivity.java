package com.example.gohpeiyee.usm_parkingfine_system_v1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    EditText username, email, phoneNum, password, rePassword, userRole;
    Spinner role;
    Button submit;
    String URL= "http://192.168.0.154/android-usm/user/register.php";

    JSONParser jsonParser=new JSONParser();
    final Context context=this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);



    username=(EditText)findViewById(R.id.reg_name);
    email=(EditText)findViewById(R.id.reg_email);
    phoneNum=(EditText)findViewById(R.id.reg_phone);
    password=(EditText)findViewById(R.id.reg_password);
    rePassword=(EditText)findViewById(R.id.re_password);
    userRole=(EditText)findViewById(R.id.user_role);
    //role=(Spinner)findViewById(R.id.user_role);
    submit=(Button)findViewById(R.id.submit_btn);

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //if((username != null) && (email != null) && (phoneNum!=null) && (password!=null) && (rePassword!=null) && (role != null)){
//                if(rePassword == password)
//                {
                Log.i("info","re");
                    AttemptRegister attemptRegister= new AttemptRegister();
                    attemptRegister.execute(username.getText().toString(), email.getText().toString(),phoneNum.getText().toString(), userRole.getText().toString(), password.getText().toString(),rePassword.getText().toString(),"");
                //}

            //}
        }
    });
}

    private class AttemptRegister extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String email = args[1];
            String password = args[4];
            String username = args[0];
            String phoneNum  =args[2];
            String role = args[3];
            String retypePassword = args[5];

            ArrayList params = new ArrayList();
            if(username.length()>0)
                params.add(new BasicNameValuePair("username", username));

            if (email.length() > 0) {
                params.add(new BasicNameValuePair("email", email));

            }

            if(phoneNum.length() >0)
                params.add(new BasicNameValuePair("phone", phoneNum));

            if(role.length() >0)
                params.add(new BasicNameValuePair("role", role));

            if(password.length()>= 8)
                params.add(new BasicNameValuePair("password", password));



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

    public void registerUser(){

    }
}
