package com.example.schooldesk.student.data;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*
This class is to ensure that there is only one object is present in entire Application.
for more information https://developer.android.com/training/volley/requestqueue#singleton
It is recommended to use VolleySingleton when your application uses connection through out the application.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;

    // Below constructor will create newRequestQueue.
    // As it is private we need another method to create an instance of Volley.
    private VolleySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // Here it will create new instance only if there is not instance is present.
    //Here synchronized keyword will make sure that only one thread is attached.
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    //As mInstance is a private member we need getter method to access it.
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
