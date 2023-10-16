package com.example.arrayfit.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioAutenticado {

    public static FirebaseUser usuarioLogado(){
        FirebaseAuth usuario = ConfigDb.FirebaseAutenticacao();
        return usuario.getCurrentUser();
    }


}
