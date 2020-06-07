package com.example.schooldesk.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.schooldesk.ChangePasswordActivity;
import com.example.schooldesk.student.DashboardFragment;
import com.example.schooldesk.R;
import com.example.schooldesk.user.SharedPrefClass;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class TeacherDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity mActivity;
    private Context mContext;

    // Declare the DrawerLayout object as global.
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        mContext = getApplicationContext();
        mActivity = TeacherDashboardActivity.this;

        // We have already removed toolbar from the activity.
        // so, we have to all extra toolbar for activity.
        Toolbar toolBar = findViewById(R.id.toolbar_teacher);
        setSupportActionBar(toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // We will populate the navigation view.
        NavigationView navigationView = findViewById(R.id.nav_teach_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialize the drawer layout.
        mDrawerLayout = findViewById(R.id.nav_teach_drawer);
        // Here in below R.string argument is for blind people.
        // Those strings are not going to display anywhere but will help blind people for navigation.
        mActionBarDrawerToggle = new ActionBarDrawerToggle(TeacherDashboardActivity.this,
                mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        // Here if statement is required because the code inside if statement will load dashboard fragment in all the cases
        // whenever the fragment is destroyed. But it used to destroy in case of memory leak, screen rotate.
        // So, avoid UI level bug we have put if statement.
        if (savedInstanceState == null) {
            //Below code is to display dashboard fragment by default.
            getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                    new TeachDashFrag()).commit();
            navigationView.setCheckedItem(R.id.nav_teach_dashboard);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_teach_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                        new TeachDashFrag()).addToBackStack(null).commit();
                break;
            case R.id.nav_teach_timetable:
                Toast.makeText(this,"timetable clicked !",Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                //        new TimetableFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_teach_attendance:
                getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                        new TakeAttendanceFrag()).addToBackStack(null).commit();
                break;
            case R.id.nav_teach_exam:
                Toast.makeText(this,"exam clicked !",Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                //        new ExamFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_teach_result:
                Toast.makeText(this,"result clicked !",Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.teacher_dash_fragment,
                //        new ResultFragment()).addToBackStack(null).commit();
                break;
            case R.id.menu_teach_change_password:
                Toast.makeText(this,"change_pass clicked !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ChangePasswordActivity.class);
                intent.putExtra("class", "change_password");
                finish();
                startActivity(intent);
                break;
            case R.id.teach_logout:
                userLogOut();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void userLogOut() {
        // When you logout from the application, it will reset all the sharedpreferences to default.
        // Here I have considered "User" as default user.
        // Session Id, I would like keep it blank whenever it is not used.
        /*sharedPrefClass.writeUserName("User");
        sharedPrefClass.writeLoginStatus(false);
        sharedPrefClass.writeSessionId("");*/
        SharedPrefClass.getInstance(getApplicationContext()).userLogout();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
