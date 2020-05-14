package com.example.schooldesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncAdapterType;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private EditText mEditOldPass, mEditNewPass, mEditRePass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mEditOldPass = findViewById(R.id.edit_old_password);
        mEditNewPass = findViewById(R.id.edit_new_password1);
        mEditRePass = findViewById(R.id.edit_new_password2);
        Button btnChangePassword = findViewById(R.id.btn_change_pass);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs(v);
            }
        });
    }

    private void validateInputs(View v) {
        String oldPassWord = mEditOldPass.getText().toString().trim();
        String newPassWord = mEditNewPass.getText().toString().trim();
        String rePassWord = mEditRePass.getText().toString().trim();
        mEditNewPass.addTextChangedListener((TextWatcher) this);

        // Validating inputs.
        if (!validateOldPass(oldPassWord) | !validateNewPass(newPassWord) | !validateRePass(newPassWord,rePassWord)){
            return;
        }

        // If all the conditions are satisfied then connect with server.
        // After confirming from server move tp login activity.
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    private boolean validateOldPass(String pass) {
        if (TextUtils.isEmpty(pass)) {
            mEditOldPass.setError("The Field can't be empty!");
            return false;
        } else {
            mEditOldPass.setError(null);
            return true;
        }
    }

    private boolean validateNewPass(String pass) {
        if (TextUtils.isEmpty(pass)) {
            mEditNewPass.setError("The Field can't be empty!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
            mEditNewPass.setError("Password is too week");
            return false;
        } else {
            mEditNewPass.setError(null);
            return true;
        }
    }

    private boolean validateRePass(String pass, String repass) {
        if (TextUtils.isEmpty(repass)) {
            mEditNewPass.setError("The Field can't be empty!");
            return false;
        } else if (!pass.matches(repass)) {
            mEditRePass.setError("Password does not match !");
            return false;
        } else {
            mEditRePass.setError(null);
            return true;
        }
    }
}
