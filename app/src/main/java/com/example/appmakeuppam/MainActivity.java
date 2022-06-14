package com.example.appmakeuppam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmakeuppam.DAO.UserDAO;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "usuarioLogado.json";
    private EditText edtTxtEmail, edtTxtPassword;
    private TextView txtcadastro;
    private Button btnLogin;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        txtcadastro = findViewById(R.id.txtcadastro);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(v -> {
            String emailLogin = String.valueOf(edtTxtEmail.getText());
            String passwordLogin = String.valueOf(edtTxtPassword.getText());

            checkField();

            userDAO = new UserDAO(getApplicationContext());

            if(userDAO.checkLogin(emailLogin, passwordLogin)){
                User user = userDAO.selectUserByEmail(emailLogin);

                Gson gson = new Gson();
                String json = gson.toJson(user);
                printUser(json);

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Usuário ou senha não correspondem", Toast.LENGTH_SHORT).show();
            }
        });

        txtcadastro.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // VALIDAR CAMPOS
    private void checkField(){
        boolean verification = false;

        String email = edtTxtEmail.getText().toString();
        String senha = edtTxtPassword.getText().toString();

        if (verification = nullField(email)) {
            edtTxtEmail.requestFocus();
            Toast.makeText(this, "Preencha o campo e-mail.", Toast.LENGTH_SHORT).show();
        } else if (verification = nullField(senha)) {
            edtTxtPassword.requestFocus();
            Toast.makeText(this, "Preencha o campo senha.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean nullField (String field){
        boolean verification = (TextUtils.isEmpty(field) || field.trim().isEmpty());
        return verification;
    }

    // SAVED INSTANCE
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String email = edtTxtEmail.getText().toString();
        outState.putString("UserEmail", email);
    }

    // ARMAZENAR DADOS
    private void printUser(String json) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Usuário logado.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}