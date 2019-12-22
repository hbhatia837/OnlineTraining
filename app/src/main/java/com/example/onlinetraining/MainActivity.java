package com.example.onlinetraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.contentcapture.ContentCaptureCondition;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements CallBackInterface {

    GridView gridView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DatabaseReference mRef;
FirebaseDatabase firebaseDatabase;
    int flag;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout=findViewById(R.id.mylayout);
//        getSupportActionBar().hide();
        fragmentManager = getSupportFragmentManager();
//firebaseDatabase=FirebaseDatabase.getInstance();
mRef=FirebaseDatabase.getInstance().getReference("users");
        addCourseFragemet();

    }

    private void addCourseFragemet() {

        fragmentTransaction = fragmentManager.beginTransaction();
        CourseFragment courseFragment = new CourseFragment();
        courseFragment.setCallBackInterface((CallBackInterface) this);
        fragmentTransaction.replace(R.id.frame, courseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void callBackMethod() {

        register();

//login();

    }

    @Override
    public void callbackmethod1() {

        login();
    }

    @Override
    public void callbackMethod2(String phoneNumber) {

        connection(phoneNumber);




    }

    @Override
    public void callbackMethod3(String phoneNumber) {
        connection1(phoneNumber);

    }

    private void connection(String number)
    {
        ConnectivityManager conMan= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=conMan.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            verification(number);
        }
        else
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(), 0);
Snackbar.make(coordinatorLayout,"Please connect to network and try again later",Snackbar.LENGTH_LONG).show();
        }
        }

private void connection1(String number)
{
    ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
    if(networkInfo!=null && networkInfo.isConnected())
    {
        verificationLogin(number);
    }
    else
    {
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(),0);
        Snackbar.make(coordinatorLayout,"Please connect to network and try again later",Snackbar.LENGTH_LONG).show();

    }




}

    private void verificationLogin(final String phoneNumber) {
        flag=0;

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("tag1", "no response1" + dataSnapshot.getChildren());

                for(DataSnapshot mobileNumber : dataSnapshot.getChildren()) {
                    Log.e("tag1", "no response" + mobileNumber);
                    Log.e("tag1", "no response" + phoneNumber);

                    if (mobileNumber.getValue().equals(phoneNumber)) {
                        Log.e("tag1", "no response" + mobileNumber.getValue());
                        //Toast.makeText(MainActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                        flag=1;
                        break;

                    }
                }
                if(flag==1)
                {
                    verificationLoginOtp(phoneNumber);
                  //  Toast.makeText(MainActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "This phone number is not registerd.Please register to continue", Toast.LENGTH_SHORT).show();
                }


                Log.e("tag1", "no response1" + dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });



    }



    private void verification(final String  phoneNumber) {









 flag=0;

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("tag1", "no response1" + dataSnapshot.getChildren());

                for(DataSnapshot mobileNumber : dataSnapshot.getChildren()) {
                    if (mobileNumber.getValue().equals(phoneNumber)) {
                        Log.e("tag1", "no response" + mobileNumber.getValue());
                        //Toast.makeText(MainActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                        flag=1;
                        break;

                    }
                }
                if(flag==1)
                {
                    Toast.makeText(MainActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String id=mRef.push().getKey();

                    mRef.child(id).setValue(phoneNumber);
                    verification1(phoneNumber);
                }


                Log.e("tag1", "no response1" + dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

    private void verificationLoginOtp(String phoneNumber)
    {
     fragmentTransaction=fragmentManager.beginTransaction();
     OtpLoginFragment otpLoginFragment=new OtpLoginFragment();
     Bundle bundle=new Bundle();
     bundle.putString(CallBackInterface.KEY_NUMBER,phoneNumber);
     fragmentManager.popBackStack(0,FragmentManager.POP_BACK_STACK_INCLUSIVE);
     otpLoginFragment.setArguments(bundle);
     fragmentTransaction.replace(R.id.frame,otpLoginFragment);
     fragmentTransaction.commit();




    }


    private void verification1(String phoneNumber) {

//String id=mRef.push().getKey();
//
//mRef.child(id).setValue(phoneNumber);


        fragmentTransaction = fragmentManager.beginTransaction();
        OtpVerifyFragment otpVerifyFragment = new OtpVerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CallBackInterface.KEY_NUMBER, phoneNumber);
        fragmentTransaction.replace(R.id.frame, otpVerifyFragment);
        otpVerifyFragment.setArguments(bundle);
        fragmentTransaction.commit();


    }

    private void register() {



        fragmentTransaction = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.setCallBackInterface(this);
        //  fragmentManager.popBackStack(0,0);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, registerFragment);


        fragmentTransaction.commit();

    }

    private void login() {

        fragmentTransaction = fragmentManager.beginTransaction();
        Login login = new Login();
        login.setCallBackInterface(this);
        fragmentTransaction.replace(R.id.frame, login);
        fragmentManager.popBackStack(0, 0);// it will remove all the fragment  show the fragment in 0th position
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {

        Fragment fragment;
      //  int fragments = getSupportFragmentManager().getBackStackEntryCount();
fragment=fragmentManager.findFragmentById(R.id.frame);
        if (fragment instanceof CourseFragment) {
            finish();
        }
        else if(fragment instanceof OtpLoginFragment)
        {
fragment=new CourseFragment();
fragmentTransaction =fragmentManager.beginTransaction();
fragmentTransaction.replace(R.id.frame,fragment,"demo");
fragmentTransaction.commit();
        }

        else {
            super.onBackPressed();
        }

    }
}