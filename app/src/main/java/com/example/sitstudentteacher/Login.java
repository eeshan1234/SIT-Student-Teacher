package com.example.sitstudentteacher;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] category={"Teacher","Student"};
    String lbname;
    Spinner spinner;

    EditText e1, e2;
    TextView t1;
    Button b1;

    DatabaseReference databaseReference, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, category);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        e1 = (EditText) findViewById(R.id.usn);
        e2 = (EditText) findViewById(R.id.pass);

        t1 = (TextView) findViewById(R.id.login);
        b1 = (Button) findViewById(R.id.but3);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Student");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lbname.equals("Teacher")){

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String usn = e1.getText().toString().trim();
                            String pass = e2.getText().toString().trim();

                            for(DataSnapshot x: dataSnapshot.getChildren())
                            {
                                if(usn.equals(x.getKey())){
                                    if(pass.equals(x.child("Password").getValue())){
                                        Intent i = new Intent(Login.this, HomeActivity.class);
                                        startActivity(i);
                                        break;
                                    }
                                    Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();

                                }


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                if(lbname.equals("Student")){

                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String usn = e1.getText().toString().trim();
                            String pass = e2.getText().toString().trim();

                            for(DataSnapshot x: dataSnapshot.getChildren())
                            {
                                if(usn.equals(x.getKey())){
                                    if(pass.equals(x.child("Password").getValue())){
                                        Intent i = new Intent(Login.this, HomeStudent.class);
                                        startActivity(i);
                                        break;
                                    }
                                    Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();

                                }


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        lbname=category[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(this, "Nothing is selected!!", Toast.LENGTH_SHORT).show();

    }
}
