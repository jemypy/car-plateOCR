package com.example.gohpeiyee.usm_parkingfine_system_v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

public class SignInActivity extends ActivityCompat {
    private TextView statusField,roleField;
    private Context context;
    private int byGetOrPost = 0;


}
