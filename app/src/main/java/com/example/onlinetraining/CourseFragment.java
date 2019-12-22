package com.example.onlinetraining;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CourseFragment extends Fragment
{

View rootView;
Context context;
TextView java;
CallBackInterface callBackInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_course,container,false);

initUi();
return rootView;
    }

public  void setCallBackInterface(CallBackInterface callBackInterface)
{
this.callBackInterface=callBackInterface;
}





    private void initUi()
    {
        context=getContext();
         java=rootView.findViewById(R.id.java);
          java.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {


                  if(callBackInterface!=null)
                  {
                   callBackInterface.callBackMethod();
                  }

              }
          });




    }


}
