package com.delivery;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.delivery.controller.AppConstant;
import com.delivery.controller.AppUtils;
import com.delivery.controller.PrefUtil;
import com.delivery.utils.RestClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.gson.JsonObject;

import static android.view.View.GONE;

public class OtpActivity extends AppCompatActivity {
    ImageView ivBack;
    PinEntryEditText etOtp;
    TextView tvVerify;
    TextView tvResendOtp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
    }

    private void init(){
        ivBack = (ImageView)findViewById(R.id.iv_back);
        etOtp = (PinEntryEditText) findViewById(R.id.et_otp);
        tvVerify = (TextView) findViewById(R.id.tvVerify);
        tvResendOtp = (TextView) findViewById(R.id.tvResendOtp);
        progressBar = (ProgressBar) findViewById(R.id.progress);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(AppUtils.isNullOrEmpty(etOtp.getText().toString())){
                    progressBar.setVisibility(GONE);
                    etOtp.setError("Please Enter Otp");
                    etOtp.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    callApiForVerifyOtp();
                }
            }
        });

        tvResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                callApiForResendOtp();
            }
        });
    }

    private void callApiForResendOtp() {
    }

    private void callApiForVerifyOtp() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("emailMobile", AppUtils.getUserDetails().getDeliveryBoyEmail());
        jsonObject.addProperty("otp", etOtp.getText().toString());

        new RestClient().getApiService().getVerifyOtp(jsonObject, new Callback<ModLogin>() {
            @Override
            public void success(ModLogin modLogin, Response response) {
                progressBar.setVisibility(View.GONE);
                if(modLogin.getSuccess().equals("1")){
                    PrefUtil.getInstance(OtpActivity.this).putData(AppConstant.PREF_USER_ID,modLogin.getDeliveryBoyId());
                    AppUtils.setUserDetails(modLogin);
                    Intent intent=new Intent(OtpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();

                }else{
                    Toast.makeText(OtpActivity.this, modLogin.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(OtpActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

