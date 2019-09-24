package com.flover.rifaecom.operation.singupoperation;


import com.flover.rifaecom.R;

public class SignUpOperationFactory {
    public SignUpOperation getInstance(int anyButtonId){
        SignUpOperation anyOperationInstance = null;
        if (R.id.createAccountButton==anyButtonId){
            anyOperationInstance = new SignUpCreateButtonOperation();
        }


        return anyOperationInstance;
    }
}
