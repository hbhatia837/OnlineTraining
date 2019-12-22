package com.example.onlinetraining;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Login extends Fragment {
    View rootView;
    Context context;
    Button next;
    Spinner spinner;
    EditText editText;
    EditText phoneNumber;
    CallBackInterface callBackInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_login,container,false);
        initUi();
        return rootView;
    }

    public   void setCallBackInterface(CallBackInterface callBackInterface)
    {
        this.callBackInterface=callBackInterface;
    }



    private void initUi()
    {
context=getContext();
next=rootView.findViewById(R.id.next);
spinner=rootView.findViewById(R.id.spinner);
phoneNumber=rootView.findViewById(R.id.mobilenumber);
        ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,CountryData.countryAreaCodes);
        spinner.setAdapter(arrayAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
       String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
       String number=phoneNumber.getText().toString().trim();
       if(number.isEmpty() || number.length()<10)
       {
           phoneNumber.setError("Number is required");
           phoneNumber.requestFocus();
       }
String mobileNumber=""+code+" "+number;
if(callBackInterface!=null)
{
    callBackInterface.callbackMethod3(mobileNumber);

}


            }
        });



    }
}
