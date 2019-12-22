package com.example.onlinetraining;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    View rootview;
    Context context;
    TextView login;
    CallBackInterface callBackInterface;
    Button register;
    Spinner spinner;
    EditText phoneNumber;
    TextInputLayout inputEmail;
    EditText email, name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_register, container, false);
        initUi();
        return rootview;
    }

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        this.callBackInterface = callBackInterface;
    }


    private void initUi() {
        context = getContext();
        login = rootview.findViewById(R.id.logn);
        register = rootview.findViewById(R.id.register);
        spinner = rootview.findViewById(R.id.spinner);
        phoneNumber = rootview.findViewById(R.id.mobilenumber);
        email = rootview.findViewById(R.id.email);
        inputEmail = rootview.findViewById(R.id.inputemail);
        name = rootview.findViewById(R.id.name);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, CountryData.countryAreaCodes);
        spinner.setAdapter(arrayAdapter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBackInterface != null) {

                    callBackInterface.callbackmethod1();
                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = phoneNumber.getText().toString().trim();
                String email2 = email.getText().toString().trim();
                String name1 = name.getText().toString();
                if (number.isEmpty() || number.length() < 10) {
                    phoneNumber.setError("Number is required");
                    phoneNumber.requestFocus();
                } else if (TextUtils.isEmpty(email2)) {
                    inputEmail.setError("Email is Mandatory");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
                    Toast.makeText(context, "Email provided is invalid", Toast.LENGTH_SHORT).show();
                } else if (name1.isEmpty()) {
                    name.setError("Name is required");
                    name.requestFocus();

                }


               else if (callBackInterface != null) {
                    String mobileNumber = "" + code + " " + number;
                    callBackInterface.callbackMethod2(mobileNumber);
                }
else
                {

                }

            }
        });


    }

}