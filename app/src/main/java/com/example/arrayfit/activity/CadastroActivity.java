package com.example.arrayfit.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.arrayfit.R;
import com.example.arrayfit.model.Usuario;
import com.example.arrayfit.util.ConfigDb;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText edtNome, edtSenha, edtEmail;
    Button btnCadastrar;
    RadioGroup rdgGenero;
    RadioButton gMasculino, gFeminino;

    NumberPicker npTreinos;
    String usuarioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        inicializar();
        configNunPicker();

    }

    //método criado apenas para fins de organização
    private void inicializar(){
        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);
        edtEmail = findViewById(R.id.edtEmail);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        npTreinos = findViewById(R.id.npDias);
        rdgGenero = findViewById(R.id.rdgGenero);
        gMasculino = findViewById(R.id.gMasculino);
        gFeminino = findViewById(R.id.gFeminino);
    }

    //verifica o genero selecionado e retorna a String relacionado a ele
    private String generoSelecionado() {
        int generoSelecionadoId = rdgGenero.getCheckedRadioButtonId();

        if(generoSelecionadoId == R.id.gFeminino){
            return "feminino";
        }
        if (generoSelecionadoId == R.id.gMasculino){
            return "masculino";
        }
        else {
            return null;
        }
    }

    private void configNunPicker(){
        npTreinos.setMaxValue(7);
        npTreinos.setMinValue(1);
    }



    public void validarCampos(View view){
        String genero = generoSelecionado();
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        int qtdTreinos = npTreinos.getValue();
        //Verifica se os campos estão preenchidos
        
        if(!nome.isEmpty()){//TIRAR
            if (!email.isEmpty()){
                if (!senha.isEmpty()){
                    if(qtdTreinos != 0) {
                        if(genero != null){
                            //estanciando objeto usuario
                            usuario = new Usuario();
                            usuario.setNome(nome);
                            usuario.setEmail(email);
                            usuario.setSenha(senha);
                            usuario.setGenero(genero);
                            usuario.setTreinos(qtdTreinos);
                            //cadastro do usuario
                            cadastrarUsuario();
                        }else{
                            Toast.makeText(this, "Selecione o genero corretamente", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "Selecione a media de treinos por semana corretamente", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Preencha o campo senha", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Preencha o campo email", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
        }
    }


    private void cadastrarUsuario() {
        autenticacao = ConfigDb.FirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    SalvarDadosUsuario();//Salva Usuario no Banco de dados
                    Toast.makeText(CadastroActivity.this, "Sucesso ao Cadastrar o Usuario", Toast.LENGTH_SHORT).show();
                    abrirHome();
                }else {
                    String exececao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        exececao = "Digite uma senha com no mínimo 6 caracteres";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exececao = "Digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        exececao = "Essa conta já existe";
                    }catch (Exception e){
                        exececao = "Erro ao cadastrar usuario " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, exececao, Toast.LENGTH_SHORT).show();
                }
            }});
    }

    private void SalvarDadosUsuario(){
        String nome = usuario.getNome();
        String genero = usuario.getGenero();
        int mediaTreinos = usuario.getTreinos();

        Log.d("DEBUG", "Nome e celular para salvar no Firestore: " + nome + ", " + genero+ "," + mediaTreinos); // Log de debug para verificar os dados que estão sendo salvos


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        usuarios.put("genero", genero);
        usuarios.put("media_treino",mediaTreinos);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("DEBUG", "Sucesso ao salvar dados no Firestore"); // Log de debug para verificar se os dados foram salvos com sucesso
            }
        })
         .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ERROR", "Erro ao salvar dados no Firestore: " + e.toString()); // Log de erro em caso de falha

            }
        });

    }
    private void abrirHome() {
        Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void voltarTC (View view){
        finish();
    }
}