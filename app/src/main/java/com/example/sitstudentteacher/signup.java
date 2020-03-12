package com.example.sitstudentteacher;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] category={"Teacher","Student"};
    String lbname;
    Spinner spinner;

    EditText e1, e2, e3, e4;
    TextView t1;
    Button b1;

    DatabaseReference databaseReference, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,category);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        e1 = (EditText) findViewById(R.id.editText13);
        e2 = (EditText) findViewById(R.id.editText14);
        e3 = (EditText) findViewById(R.id.editText15);
        e4 = (EditText) findViewById(R.id.usn);
        t1 = (TextView) findViewById(R.id.login);
        b1 = (Button) findViewById(R.id.button3);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Student");

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup.this,Login.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = e1.getText().toString().trim();
                String email = e2.getText().toString().trim();
                String pass = e3.getText().toString().trim();
                String usn = e4.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(usn)){
                    Toast.makeText(signup.this, "Please Enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    if(lbname.equals("Teacher")) {

                        databaseReference.child(usn).child("Name").setValue(name);
                        databaseReference.child(usn).child("Email").setValue(email);
                        databaseReference.child(usn).child("Password").setValue(pass);
                        databaseReference.child(usn).child("Registered ID").setValue(usn);

                        Toast.makeText(signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(signup.this, HomeActivity.class);
                        startActivity(i);
                    }

                    else
                    {


                        databaseReference2.child(usn).child("Name").setValue(name);
                        databaseReference2.child(usn).child("Email").setValue(email);
                        databaseReference2.child(usn).child("Password").setValue(pass);
                        databaseReference2.child(usn).child("Registered ID").setValue(usn);

                        Toast.makeText(signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(signup.this, HomeStudent.class);
                        startActivity(i);
                    }
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
