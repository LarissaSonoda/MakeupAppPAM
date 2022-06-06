package com.example.appmakeuppam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.appmakeuppam.DAO.UserDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText edtTxtName, edtTxtEmail, edtTxtPassword;
    String userName, userEmail, userPassword;
    ImageButton btnAddImage;
    ImageView imgViewUser;
    Button btnRegister;
    final int REQUEST_CODE_GALLERY = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnAddImage = findViewById(R.id.btnAddImage);
        btnRegister = findViewById(R.id.btnRegister);

        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        imgViewUser = findViewById(R.id.imgViewUser);

        btnAddImage.setOnClickListener(v -> viewGallery());

        btnRegister.setOnClickListener(v-> {
            userName = edtTxtName.getText().toString();
            userEmail = edtTxtEmail.getText().toString();
            userPassword = edtTxtPassword.getText().toString();

            User user = new User(null, userName, userEmail, userPassword, imageViewToByte(imgViewUser));

            UserDAO userDAO = new UserDAO(RegisterActivity.this);

            try{
                userDAO.insertUser(user);
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // MANIPULAÇÃO DE IMAGENS
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgViewUser.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(),"Acesso à galeria negado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewGallery() {
        ActivityCompat.requestPermissions(
            RegisterActivity.this,
            new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },
            REQUEST_CODE_GALLERY
        );
    }

    private byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }

    // SAVED INSTANCE
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        userName = edtTxtName.getText().toString();
        userEmail = edtTxtEmail.getText().toString();
        userPassword = edtTxtPassword.getText().toString();

        outState.putString("UserName", userName);
        outState.putString("UserEmail", userEmail);
        outState.putString("UserPassword", userPassword);
    }
}