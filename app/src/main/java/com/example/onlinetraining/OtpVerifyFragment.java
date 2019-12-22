package com.example.onlinetraining;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.TaskExecutor;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerifyFragment extends Fragment {

  View rootView;
Button sigin;
FirebaseAuth mAuth;
ProgressBar progressBar;
EditText otp1,otp2,otp3,otp4,otp5,otp6;
String verificationId;
TextView number1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
rootView=inflater.inflate(R.layout.fragment_otpverify,container,false);
initUi();
return rootView;

    }

    private void initUi() {
//sigin=rootView.findViewById(R.id.signin)
otp1=rootView.findViewById(R.id.otp1);
        otp2=rootView.findViewById(R.id.otp2);
        otp3=rootView.findViewById(R.id.otp3);
        otp4=rootView.findViewById(R.id.otp4);
        otp5=rootView.findViewById(R.id.otp5);
        otp6=rootView.findViewById(R.id.otp6);
progressBar=rootView.findViewById(R.id.progressbar);
number1=rootView.findViewById(R.id.number);
mAuth=FirebaseAuth.getInstance();



    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String number = bundle.getString(CallBackInterface.KEY_NUMBER, "hello");
     //   number = getString(getNumber(number));

       number1.setText(number);
        sendVerificationCode(number);

    }

    private void sendVerificationCode(String number) {

progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD
        ,mCallback);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback=
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
verificationId=s;

                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                {

               String     code=phoneAuthCredential.getSmsCode();
                   if(code!=null)
                   {


                       String a[]=code.split("");


                       otp1.setText((a[1]));
                       otp2.setText((a[2]));
                       otp3.setText(a[3]);
                       otp4.setText(a[4]);
                       otp5.setText((a[5]));
                       otp6.setText((a[6]));



progressBar.setVisibility(View.VISIBLE);
                   verifyCode(code);
                   }

                }

                @Override
                public void onVerificationFailed(FirebaseException e)
                {

                }
            };


private  void verifyCode(String code)
{
PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful())
{
    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
}
else
{
    Toast.makeText(getContext(), ""+task.getException(), Toast.LENGTH_LONG).show();
}

    }
});



}



}
