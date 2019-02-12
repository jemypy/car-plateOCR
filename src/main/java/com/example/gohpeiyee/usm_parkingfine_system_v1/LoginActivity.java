package com.example.gohpeiyee.usm_parkingfine_system_v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText emailField, passwordField;
    Button register, signin, forget;

    String URL= "http://192.168.0.154/android-usm/user/login.php";

    JSONParser jsonParser=new JSONParser();
    int i=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context context = this;

        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password);

        register = (Button) findViewById(R.id.register_btn);
        signin = (Button)findViewById(R.id.signIn_btn);
        forget = (Button)findViewById(R.id.forget_btn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(emailField.getText().toString(),passwordField.getText().toString(),"");
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, registerActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        }));

    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String email = args[0];
            String password = args[1];
            //String name = args[0];

            ArrayList params = new ArrayList();
            //params.add(new BasicNameValuePair("username", name));

            if (email.length() > 0) {
                params.add(new BasicNameValuePair("email", email));

            }
            params.add(new BasicNameValuePair("password", password));

            Log.i("email",email);
            Log.i("password",password);
            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Log.i("tagconvertstr", "["+result+"]");
            try {
                if ((result != null) && (result.getString("message").equals("Successfully logged in")) ){
                    Toast.makeText(getApplicationContext(), result.getString("message"), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, AddNewSummonActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
