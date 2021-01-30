package com.delivery;

import androidx.appcompat.app.AppCompatActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.controller.AppConstant;
import com.delivery.controller.AppUtils;
import com.delivery.controller.PrefUtil;
import com.delivery.utils.RestClient;
import com.google.gson.JsonObject;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {

    ImageView iv_back;
    TextView tv_login, tv_forgot_password;
    EditText et_username, et_password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_login = (TextView) findViewById(R.id.tv_login);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_username.getText().toString();
                String password = et_password.getText().toString();
                if(AppUtils.isNullOrEmpty(email)){
                    progressBar.setVisibility(GONE);
                    et_username.setError("Please Enter User Name");
                    et_username.requestFocus();
                }else
                /*if(AppUtils.isNullOrEmpty(password)){
                    progressBar.setVisibility(GONE);
                    et_password.setError("Please Enter Password");
                    et_password.requestFocus();
                }else*/{
                    progressBar.setVisibility(View.VISIBLE);
                    getLogin();
                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getLogin(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("emailMobile", et_username.getText().toString());

        new RestClient().getApiService().getLogin(jsonObject, new Callback<ModLogin>() {
            @Override
            public void success(ModLogin modLogin, Response response) {
                progressBar.setVisibility(GONE);
                if(modLogin.getSuccess().equals("1")){
                    modLogin.setDeliveryBoyEmail(et_username.getText().toString());
                    PrefUtil.getInstance(LoginActivity.this).putData(AppConstant.PREF_USER_ID,modLogin.getDeliveryBoyId());
                    AppUtils.setUserDetails(modLogin);
                    Intent intent=new Intent(LoginActivity.this, OtpActivity.class);
                    startActivity(intent);
                    //finish();
                }else{
                    Toast.makeText(LoginActivity.this, modLogin.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(GONE);
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
