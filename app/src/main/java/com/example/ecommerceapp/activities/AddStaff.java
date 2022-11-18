package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddStaff extends AppCompatActivity {

    EditText namaas,usernameas,pasas,conpasas;
    TextView conpasalert;
    Button addStaff;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        namaas = findViewById(R.id.namaAS);
        usernameas = findViewById(R.id.usernameAS);
        pasas = findViewById(R.id.pasAS);
        conpasas = findViewById(R.id.conpasAS);
        conpasalert = findViewById(R.id.ConfirmPassAlertAS);
        addStaff = findViewById(R.id.addASBtn);

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(namaas);
                checkField(usernameas);
                checkField(pasas);
                checkField(conpasas);

                if (valid) {
                    fAuth.createUserWithEmailAndPassword(usernameas.getText().toString(),pasas.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(AddStaff.this, "Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("FullName",namaas.getText().toString());
                            userInfo.put("UserEmail",usernameas.getText().toString());
                            userInfo.put("Password",pasas.getText().toString());
                            userInfo.put("ConfirmPassword",conpasas.getText().toString());

                            df.set(userInfo);
                        }
                    }).addOnFailureListener(new OnFailureListener(){
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddStaff.this, "Failed to Create Account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean validatePassword() {
        String pasRe = pasas.getText().toString().trim();
        String conpasRe = conpasas.getText().toString().trim();
        if (!pasRe.equals(conpasRe)) {
            conpasalert.setText("Password tidak sesuai");
            return false;
        } else {
            conpasalert.setText("Password Sesuai");
            return true;
        }
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}