package com.example.firebasenotificationpushnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myhexaville.smartimagepicker.ImagePicker;
import com.myhexaville.smartimagepicker.OnImagePickedListener;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name,email,password;
    private TextView login;
    private ImageView back;
    private Button registration;
    private CircleImageView imageView;
    private ProgressBar progressBar;

    //for permission
    final int PERMISSION_ALL = 100;
    //for permission


     ImagePicker imagePicker;

     Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registration = findViewById(R.id.register);
        imageView = findViewById(R.id.image);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        back = findViewById(R.id.back);

        progressBar = findViewById(R.id.progress_bar);

        //for permission(multiple permission)
        final String[] PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermissions(RegistrationActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(RegistrationActivity.this, PERMISSIONS, PERMISSION_ALL);
                }
                else {
                    imagePicker.choosePicture(true /*show camera intents*/);
                }
            }
        });

        //image pick
        imagePicker = new ImagePicker(this, /* activity non null*/
                null, /* fragment nullable*/
                new OnImagePickedListener() {
                    @Override
                    public void onImagePicked(Uri imageUri) {
                        UCrop.of(imageUri, getTempUri())
                                .withAspectRatio(1, 1)
                                .start(RegistrationActivity.this);
                    }
                });
        //image pick



    }

    //image pick
    private Uri getTempUri(){
        String dri = Environment.getExternalStorageDirectory()+ File.separator+"Temp";
        File dirFile = new File(dri);
        dirFile.mkdir();

        String file = dri+File.separator+"temp.png";
        File tempFile = new File(file);
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(tempFile);
    }

    //for permission
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //if user deny the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL:{
                if (grantResults.length > 0) {
                    boolean b = true;
                    for (String per : permissionsList) {
                        if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                            b=false;

                        }

                    }
                    if(b){
                        imagePicker.choosePicture(true /*show camera intents*/);
                    }

                }
                return;
            }
        }
    }
    //for permission


    //image pick
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode,requestCode, data);

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            uri = UCrop.getOutput(data);
            imageView.setImageURI(null);
            imageView.setImageURI(uri);

        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.i("dsjknjsdkn", "onActivityResult: "+cropError.getMessage());
        }
    }

}