package com.example.schooldesk;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.schooldesk.student.DashboardActivity;
import com.example.schooldesk.student.data.VolleySingleton;
import com.example.schooldesk.teacher.TeacherDashboardActivity;
import com.example.schooldesk.user.SharedPrefClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.example.schooldesk.user.SchoolContract;


public class MainActivity extends AppCompatActivity {
    // First we will define Object of SharedPrefClass object.
    //@SuppressLint("StaticFieldLeak")
    //private SharedPrefClass sharedPrefClass;

    private RequestQueue mStringRequest;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sharedPrefClass = new SharedPrefClass(this);

        mStringRequest = VolleySingleton.getInstance(getApplication()).getRequestQueue();
        progressBar = new ProgressBar(this);
        progressBar = findViewById(R.id.initial_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        if (SharedPrefClass.getInstance(getApplicationContext()).readLoginStatus()) {
            checkLoginStatus();
        } else {
            movetoLoginActivity();
        }
    }

    // If user is not logged in, we will load LoginActivity.
    private void movetoLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //intent.setClass(this,LoginActivity.class);
        progressBar.setVisibility(View.GONE);
        finish();
        startActivity(intent);
    }

    //Below method is for moving to next activity if user is logged in.
    private void moveToNextActivityIfLogIn() {
        Intent intent;
        // Use is still logged in then we will continue with respective activity.
        if (SharedPrefClass.getInstance(getApplicationContext()).readAccountType().equals(SchoolContract.STUDENT_ROLL)) {
            intent = new Intent(MainActivity.this, DashboardActivity.class);
        } else if (SharedPrefClass.getInstance(getApplicationContext()).readAccountType().equals(SchoolContract.TEACHER_ROLL)) {
            intent = new Intent(MainActivity.this, TeacherDashboardActivity.class);
        } else {
            Toast.makeText(this, "Invalid user, please login again !", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, LoginActivity.class);
        }
        // The below finish will destroy the MainActivity as soon as the new activity is invoked.
        finish();
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    private void checkLoginStatus() {
        // Check, if user has logged out of the application or not.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SchoolContract.SESSION_CHECK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // We are reading response in JSONObject because we have configured in server like that.
                            JSONObject jsonObject = new JSONObject(response);
                            String strResponse = jsonObject.getString(SchoolContract.RESPONSE_KEY);
                            if (strResponse.equals(SchoolContract.RESPONSE_CORRECT)) {
                                moveToNextActivityIfLogIn();
                            } else if (strResponse.equals(SchoolContract.RESPONSE_INCORRECT)) {
                                Toast.makeText(getApplicationContext(), "Sorry, Your session has expired! Please login again.",
                                        Toast.LENGTH_SHORT).show();
                                movetoLoginActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Below method is for sending as data to server over HTTP request.
            // We have to use the same key which we have used in server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(SchoolContract.USERNAME_KEY, SharedPrefClass.getInstance(getApplicationContext()).readUserName());
                params.put(SchoolContract.SESSION_SEND_KEY, SharedPrefClass.getInstance(getApplicationContext()).readSessionId());
                params.put(SchoolContract.USER_ROLL_KEY, SharedPrefClass.getInstance(getApplicationContext()).readAccountType());
                return params;
            }
        };
        mStringRequest.add(stringRequest);
    }



/*
    private void ShowHidePass(View view) {
        if(view.getId()==R.id.pass_visibility){

            if(EditPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                imagePass.setImageResource(R.drawable.invisible);

                //Show Password
                EditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                imagePass.setImageResource(R.drawable.visible);

                //Hide Password
                EditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
*/

// Below methods are for sample for other method for implementation.
    /*
    //Below Method is for initiating background methods.
    private void signIn(String userName, String passWord){
        Login lg = new Login(this);
        lg.execute(userName, passWord);
    }
    // This is method of fragment interface, so here we have to implement this method.
    @Override
    public void loginUser(CharSequence user, CharSequence pass) {
        // We have to call another method which is part of main activity because
        // AsyncTask can not be run on any interface or @Override methods.
        signIn((String) user,(String) pass);
    }

    @Override
    public void forgotPassWord() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                new ForgotPassFragment()).addToBackStack(null).commit();
    }*/

    // Below class is for background login implementation

    private static class Login extends AsyncTask<String, Void, String> {
        private WeakReference<MainActivity> activityWeakReference;
        //private final Context context;

        // Constructor
        Login(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }
        /*
        * MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) return;
          The above sample code will create strong reference inside process.
          If any time while this background task is running MainActivity is destroyed
          then it will also terminate the background process.
          The strong reference is for accessing the fre methods and variables of main activity.
        * */

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) return;
            /*
            if (sharedPrefClass.readLoginStatus()) {
                return;
            }*/
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) return;
        }

        @Override
        protected String doInBackground(String... voids) {
            String result = "";
            //int i = voids.length;
            String user = voids[0];
            String pass = voids[1];

            String connstr = "http://manage.hydrocarbonz.com/index.php/interfaceclass/authorize";

            if (user.equals("kuldip") && pass.equals("vicky")) {
                result = "login successful";
            } else {
                result = "login failed";
            }
/*
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
*/

            return result;

        }

        /* All the return value from doInBackground will go into this below method.
        So, after completing the background task we can perform our next execution.
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) return;
            else if (s.contentEquals("login successful")) {
                Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
                // Let us write the content in sharedpreference.
                /*
                sharedPrefClass.writeUserName("kuldip");
                sharedPrefClass.writeLoginStatus(true);
                sharedPrefClass.writeSessionId("session active");*/

                // activity.moveToNextActivityIfLogIn();

            } else Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
