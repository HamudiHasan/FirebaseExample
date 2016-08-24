package com.tesseractbd.firebaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
    private Button getLoc;
    private Button setLoc;
    private FirebaseAuth auth;

    private EditText studentNameET;
    private TextView studentListTV;
    private Button addStudentBtn;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getLoc = (Button) findViewById(R.id.btn_get_loc);
        setLoc = (Button) findViewById(R.id.btn_set_loc);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Location");
        studentListTV = (TextView) findViewById(R.id.tv);
        getLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] tem = new String[1];
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("userList", dataSnapshot.getValue().toString());
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Location location = child.getValue(Location.class);
                            long lat = (long) 54.444;
                            child.getRef().setValue(new Location(lat,lat));
                           // String temp = studentListTV.getText().toString();
                            tem[0] = tem[0] +"\n"+location.getLat() + " " + location.getLang();
                            Toast.makeText(Main3Activity.this, location.getLat() + " " + location.getLang(), Toast.LENGTH_SHORT).show();
                            Log.d("Res",  "\n" + location.getLat() + " " + location.getLang());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                studentListTV.setText(tem.toString());
            }
        });

        setLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MapsActivity.class));
//                long lat = (long) 43.444;
//                ref.push().setValue(new Location(lat, lat));
            }
        });
    }
}
