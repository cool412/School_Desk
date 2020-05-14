package com.example.schooldesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    private EditText mEditUserName, mEditMobileNumber, mEditOTP;
    Button mBtnSubmit,mBtnGetOTP;
    TextView mResendOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        mEditUserName = findViewById(R.id.forgot_activity_username);
        mEditMobileNumber = findViewById(R.id.forgot_activity_mobile);
        mEditOTP = findViewById(R.id.forgot_otp);

        mBtnGetOTP = findViewById(R.id.btn_forgot_get_otp);

        mBtnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To Do for sending details for OTP.
                submitDetails(v);
            }
        });

        mBtnSubmit = findViewById(R.id.submit_otp);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO for submitting the OTP to server.
                submitOTP(v);
            }
        });

        mResendOTP = findViewById(R.id.resend_otp);
        mResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO for resend OTP.
                Toast.makeText(getApplicationContext(), "OTP request sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitDetails(View v) {
        String userName = mEditUserName.getText().toString().trim();
        String mobileNumber = mEditMobileNumber.getText().toString().trim();

        // Validating inputs.
        if (TextUtils.isEmpty(userName)) {
            mEditUserName.setError("Please enter your username");
            mEditUserName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobileNumber) || mobileNumber.length() != 10) {
            mEditMobileNumber.setError("Please enter valid Mobile Number");
            mEditMobileNumber.requestFocus();
            return;
        }

        System.out.println(mobileNumber);

        // To Do for connecting with server.

        // If the details are correct then enable the OTP EditText and TextView and Submit button.
        mEditOTP.setEnabled(true);
        mEditOTP.setFocusable(true);
        mResendOTP.setEnabled(true);
        mBtnSubmit.setEnabled(true);
    }

    private void submitOTP(View v) {
        String stringOTP = mEditOTP.getText().toString().trim();
        // Validate input.
        if (TextUtils.isEmpty(stringOTP)) {
            mEditOTP.setError("Please enter OTP");
            mEditOTP.requestFocus();
            return;
        }
        System.out.println(stringOTP);
        // TO Do for sending OTP to server for validation.
        // If the details are correct then we will move to the next activity i.e. change password.
        Intent intent = new Intent();
        intent.setClass(this, ChangePasswordActivity.class);
        startActivity(intent);
    }
}
