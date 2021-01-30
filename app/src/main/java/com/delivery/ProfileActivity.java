package com.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.controller.AppUtils;
import com.delivery.utils.RestClient;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_submit;
    EditText et_name, et_email, et_mobile;
    ProgressBar progressBar;
    ImageView ivEditProfile;
    ImageView imgProfile;
    String captureImg = "", imgString = "", imagedelivered="", imageName="", filePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        et_name = (EditText)findViewById(R.id.et_name);
        et_email = (EditText)findViewById(R.id.et_email);
        et_mobile = (EditText)findViewById(R.id.et_mobile);
        tv_submit = (TextView)findViewById(R.id.tv_submit);
        imgProfile = (ImageView)findViewById(R.id.img_profile);
        ivEditProfile = (ImageView)findViewById(R.id.iv_edit_profile);

        et_name.setText(AppUtils.getUserDetails().getDeliveryBoyName());
        et_email.setText(AppUtils.getUserDetails().getDeliveryBoyEmail());
        et_mobile.setText(AppUtils.getUserDetails().getDeliveryBoyMobile());

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updateProfile();
            }
        });

        ivEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 1213);
            }
        });

        getProfile();
    }

    private void setProfileDetail(ModLogin modLogin){
        et_name.setText(modLogin.getDeliveryBoyName());
        et_email.setText(modLogin.getDeliveryBoyEmail());
        et_mobile.setText(modLogin.getDeliveryBoyMobile());
        Picasso.with(ProfileActivity.this).load(modLogin.getDeliveryBoyImage()).error(R.drawable.profile).into(imgProfile);
    }

    private void setUpdatedDetail(ModLogin modLogin){
        et_name.setText(modLogin.getDeliveryBoyName());
        et_email.setText(modLogin.getDeliveryBoyEmail());
        et_mobile.setText(modLogin.getDeliveryBoyMobile());
        Picasso.with(ProfileActivity.this).load(modLogin.getDeliveryBoyImage()).into(imgProfile);
    }

    private void getProfile(){
        progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deliveryBoyId", AppUtils.getUserDetails().deliveryBoyId);

        new RestClient().getApiService().getProfile(jsonObject, new Callback<ModLogin>() {
            @Override
            public void success(ModLogin modLogin, Response response) {
                progressBar.setVisibility(View.GONE);
                if(modLogin.getSuccess().equals("1")){
                    setProfileDetail(modLogin);
                }else {
                    Toast.makeText(ProfileActivity.this, modLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateProfile(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deliveryBoyId", AppUtils.getUserDetails().deliveryBoyId);
        jsonObject.addProperty("deliveryBoyName", et_name.getText().toString());
        jsonObject.addProperty("deliveryBoyEmail", et_email.getText().toString());
        jsonObject.addProperty("deliveryBoyMobile", et_mobile.getText().toString());
        jsonObject.addProperty("deliveryBoyImage", imgString);

        new RestClient().getApiService().updateProfile(jsonObject, new Callback<ModLogin>() {
            @Override
            public void success(ModLogin modLogin, Response response) {
                progressBar.setVisibility(View.GONE);
                if(modLogin.getSuccess().equals("1")){
                    setUpdatedDetail(modLogin);
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }else {
                    Toast.makeText(ProfileActivity.this, modLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            imgProfile.setImageBitmap(selectedImage);

            Bitmap bitmap=((BitmapDrawable)imgProfile.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            imgString=Base64.encodeToString(byteArray,Base64.NO_WRAP);
        }

    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                imgProfile.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                byte[] imagenamedelivered = byteArrayOutputStream.toByteArray();
                imagedelivered = Base64.encodeToString(imagenamedelivered, Base64.NO_WRAP);
                imageName= ""+System.currentTimeMillis()+".png";
                //updateProfile();
                //calling the method uploadBitmap to upload image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
