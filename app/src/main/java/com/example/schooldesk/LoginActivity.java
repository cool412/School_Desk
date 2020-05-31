package com.example.schooldesk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.schooldesk.data.SchoolContract;
import com.example.schooldesk.data.VolleySingleton;
import com.example.schooldesk.user.SharedPrefClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static SharedPrefClass sharedPrefClass;

    private EditText EditUserName, EditPassword;
    private Button btnLogIn;
    private ImageView imagePass;
    private RequestQueue mStringRequest;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefClass = new SharedPrefClass(this);

        mStringRequest = VolleySingleton.getInstance(getApplication()).getRequestQueue();


        EditUserName = findViewById(R.id.login_user);
        EditPassword = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.login_progressbar);
        //imagePass = findViewById(R.id.pass_visibility);
        btnLogIn = findViewById(R.id.btn_login);
        progressBar.setVisibility(View.GONE);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogIn.setEnabled(false);
                // listener.loginUser(UserName, PassWord);
                progressBar.setVisibility(View.VISIBLE);
                System.out.println("button clicked");
                loginUser();
            }
        });

        TextView forgotPassText = findViewById(R.id.forgot_pass);
        forgotPassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.forgotPassWord();
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });
    }

    //Below method is for moving to next activity if user is logged in.
    private void moveToNextActivityIfLogIn() {

        // Use is still logged in then we will continue with DashboardActivity.
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        finish();
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    private void loginUser() {
        // getting final values.
        final String UserName = EditUserName.getText().toString().trim();
        final String PassWord = EditPassword.getText().toString().trim();
        //validating inputs
        if (TextUtils.isEmpty(UserName)) {
            EditUserName.setError("Please enter your username");
            EditUserName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(PassWord)) {
            EditPassword.setError("Please enter your password");
            EditPassword.requestFocus();
            return;
        }

        /*
        here is example for making JSONObject for testing, if we do not have server connection available.
        String str ="{\"error\":false,\"message\":\"Login successful\"}";
        System.out.println(str);
        */

        //String url = "http://10.0.2.2/registrationapi.php?apicall=login";
        //String url = "http://10.0.2.2/registrationapi.php";

        // Below method is for sending UserName and PassWord to database and get it confirmed.
        // POST method is used for sending data though HTTP request.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SchoolContract.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println(response);
                        try {
                            // We are reading response in JSONObject because we have configured in server like that.
                            JSONObject jsonObject = new JSONObject(response);
                            String strResponse = jsonObject.getString(SchoolContract.AUTH_RESPONSE_KEY);
                            if (strResponse.equals(SchoolContract.RESPONSE_SUCCESS)) {
                                // Saving desired data in sharedpreferances.
                                sharedPrefClass.writeUserName(UserName);
                                sharedPrefClass.writeLoginStatus(true);
                                sharedPrefClass.writeSessionId(jsonObject.getString(SchoolContract.SESSION_KEY));
                                sharedPrefClass.writeAccountType(jsonObject.getString(SchoolContract.USER_ROLL_KEY));

                                moveToNextActivityIfLogIn();
                            } else if (strResponse.equals(SchoolContract.AUTH_RESPONSE_INACTIVE)) {
                                btnLogIn.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Sorry, User is not active! Please contact Administration.",
                                        Toast.LENGTH_SHORT).show();
                            } else if (strResponse.equals(SchoolContract.AUTH_RESPONSE_INVALID)) {
                                btnLogIn.setEnabled(true);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),
                                        "Sorry, User is not Valid! Please enter valid username and password.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btnLogIn.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Below method is for sending as data to server over HTTP request.
            // We have to use the same key which we have used in server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(SchoolContract.USERNAME_KEY, UserName);
                params.put(SchoolContract.PASSWORD_KEY, PassWord);
                return params;
            }
        };
        mStringRequest.add(stringRequest);


        // Below method is sample for JSONObject.
        /*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String res = response.getString("message");
                            System.out.println(res);
                            Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", UserName);
                params.put("password", PassWord);
                return params;
            }
        };*/
        //mStringRequest.add(jsonObjectRequest);

    }
}
