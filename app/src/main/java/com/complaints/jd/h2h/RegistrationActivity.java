package com.complaints.jd.h2h;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    private ImageView identityImageView;
    private EditText tinNo;
    private TextView myImageViewText;
    private static final String TAG="custom_tag";
    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int CAMERA_IMAGE_REQUEST = 1;
    public static final String FILE_NAME = "temp.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");
        identityImageView = (ImageView) findViewById(R.id.identityImageView);
        tinNo = (EditText) findViewById(R.id.tinNo);
        myImageViewText =(TextView) findViewById(R.id.myImageViewText);
        identityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                builder
                        .setMessage(R.string.dialog_select_prompt)
                        .setPositiveButton(R.string.dialog_select_gallery, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startGalleryChooser();
                            }
                        })
                        .setNegativeButton(R.string.dialog_select_camera, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startCamera();
                            }
                        });
                builder.create().show();
            }
        });

    }

    public void startGalleryChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);;
        startActivityForResult(Intent.createChooser(intent, "Select a photo"), GALLERY_IMAGE_REQUEST);
    }

    public void startCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
        else{
            Log.d(TAG, "Image picking failed");
        }
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
    }
    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            myImageViewText.setVisibility(View.GONE);
            Uri uri = data.getData();
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                1200);
                identityImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
             }
//            TODO upload image
//            uploadImage(data.getData());
//            Toast.makeText(MainActivity.this,data.getDataString(),Toast.LENGTH_SHORT).show();
        }

        else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            myImageViewText.setVisibility(View.GONE);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            identityImageView.setImageBitmap(imageBitmap);
//            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
//            TODO upload image
//            uploadImage(photoUri);
//            Toast.makeText(MainActivity.this,photoUri.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    public void onProceed(View view) {
        //TODO validate user input
        String TinNo = tinNo.getText().toString();
        if(TinNo.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tin No is a must!",Toast.LENGTH_SHORT).show();
        }
        else{
            //TODO upload TIN to database
        }
    }
}
