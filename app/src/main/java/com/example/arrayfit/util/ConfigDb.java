package com.example.arrayfit.util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfigDb {

    private static FirebaseAuth auth;

    public static FirebaseAuth FirebaseAutenticacao(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
