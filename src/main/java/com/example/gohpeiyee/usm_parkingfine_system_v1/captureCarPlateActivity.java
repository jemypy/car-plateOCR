package com.example.gohpeiyee.usm_parkingfine_system_v1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;


public class captureCarPlateActivity extends AppCompatActivity {

    //Button captureBtn = (Button)findViewById(R.id.btnCaptureImage);
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Button snapImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_car_plate);

        imageView = (ImageView)this.findViewById(R.id.displayImage);
        snapImage = (Button)this.findViewById(R.id.btnCaptureImage);

        snapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeImageFromCamera(v);
            }
        });

    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }
}
