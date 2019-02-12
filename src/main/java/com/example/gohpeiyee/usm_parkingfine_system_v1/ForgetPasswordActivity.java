package com.example.gohpeiyee.usm_parkingfine_system_v1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText registeredEmailField, newPasswordField;
    Button updateBtn;

    String URL= "http://192.168.0.154/android-usm/user/forgetPassword.php";

    JSONParser jsonParser=new JSONParser();
    int i=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        final Context context = this;

        registeredEmailField = (EditText)findViewById(R.id.forget_email);
        newPasswordField = (EditText)findViewById(R.id.new_password);
        updateBtn = (Button)findViewById(R.id.updatePasswordBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttemptUpdate attemptUpdate = new AttemptUpdate();
                attemptUpdate.execute(registeredEmailField.getText().toString(), newPasswordField.getText().toString(), "");
            }
        });
    }

    private class AttemptUpdate extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String email = args[0];
            String newPassword = args[1];


            ArrayList params = new ArrayList();

            if (email.length() > 0) {
                params.add(new BasicNameValuePair("email", email));

            }

            if(newPassword.length() > 0){
                params.add(new BasicNameValuePair("newPassword", newPassword));
            }


            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

            Log.i("info",email+" "+newPassword);
            return json;

        }
    }
}
