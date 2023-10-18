package com.example.arrayfit.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.arrayfit.R;
import com.example.arrayfit.util.ConfigDb;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView nomeUsuario, emailUsuario;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        IniciarComponentes();
        auth = ConfigDb.FirebaseAutenticacao();

    }

    //Recuperando dados do banco de dados
    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();//define a String email como o email cadastrado no FirebaseUser
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();//pega o id do usuario no FirebaseAuth

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);//abre a coleção Usuarios do firestore
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                     if(documentSnapshot != null){
                         nomeUsuario.setText(documentSnapshot.getString("nome"));
                         emailUsuario.setText(email);
                     }
            }
        });



    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.txtNome);
        emailUsuario = findViewById(R.id.txtEmail);
    }


    public void deslogar(View view){
        try{
            auth.signOut();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnTreinar(View view){
        Intent intent = new Intent(HomeActivity.this, TreinoActivity.class);
        startActivity(intent);
    }
}