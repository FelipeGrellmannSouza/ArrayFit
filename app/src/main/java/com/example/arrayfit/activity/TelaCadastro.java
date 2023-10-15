package com.example.arrayfit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arrayfit.R;
import com.example.arrayfit.model.Usuario;
import com.google.firebase.StartupTime;
import com.google.firebase.auth.FirebaseAuth;

public class TelaCadastro extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText edtNome, edtSenha, edtEmail, edtCelular;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        inicializar();
    }

    //método criado apenas para fins de organização
    private void inicializar(){
        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);
        edtEmail = findViewById(R.id.edtEmail);
        edtCelular = findViewById(R.id.edtCelular);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    public void validarCampos(View view){
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        String celular = edtCelular.getText().toString();

        //Verifica se os campos estão preenchidos
        if(!nome.isEmpty()){
            if(!celular.isEmpty()){
                if (!email.isEmpty()){
                    if (!senha.isEmpty()){

                        //estanciando objeto usuario
                        Usuario usuario = new Usuario();

                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        usuario.setCelular(celular);
                        
                        //cadastro do usuario
                        cadastrarUsuario();





                    }else{
                        Toast.makeText(this, "Preencha o campo senha", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Preencha o campo email", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Preencha o campo celular", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {

    }


    public void voltarTC (View view){
        finish();
    }
}