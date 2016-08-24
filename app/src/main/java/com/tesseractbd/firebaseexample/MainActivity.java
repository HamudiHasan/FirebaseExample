package com.tesseractbd.firebaseexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etPassword;
    EditText etEmail;
    EditText etStatus;

    TextView tvStatus;

    Button btnSubmit;
    Button btnStatus;

    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etPassword = (EditText) findViewById(R.id.et_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        etStatus = (EditText) findViewById(R.id.et_status);

        tvStatus = (TextView) findViewById(R.id.tv_status);
        btnStatus = (Button) findViewById(R.id.btn_status);

        firebaseDatabase = FirebaseDatabase.getInstance();
        // singletone obj
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference("LifeStatus");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String res = dataSnapshot.getValue(String.class);
                tvStatus.setText(res);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.setValue(etStatus.getText().toString());
            }
        });


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                //
                auth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
