package com.example.sitstudentteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText qno,ques,opt1,opt2,opt3;
    Button next,done;
    Spinner spinner;
    String sub="Maths";
    String qn,qu,op1,op2,op3,lbname;
    Intent i;

    String[] category={"1","2","3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spino);
        spinner.setOnItemSelectedListener(this);
        i=getIntent();
        sub=i.getExtras().getString("Sub");

        ArrayAdapter aa=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,category);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(aa);


        qno=findViewById(R.id.qno);
        ques=findViewById(R.id.ques);
        opt1=findViewById(R.id.opt1);
        opt2=findViewById(R.id.opt2);
        opt3=findViewById(R.id.opt3);
        done=findViewById(R.id.done);
        next=findViewById(R.id.Next);
        spinner=findViewById(R.id.spino);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Subjects").child(sub);

//        qn=qno.getText().toString();
//        qu=ques.getText().toString();
//        op1=opt1.getText().toString();
//        op2=opt2.getText().toString();
//        op3=opt3.getText().toString();



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    qn=qno.getText().toString();
                    qu=ques.getText().toString();
                    op1=opt1.getText().toString();
                    op2=opt2.getText().toString();
                    op3=opt3.getText().toString();
                    // Toast.makeText(MainActivity.this, "Ques: "+qn+"Opt1: "+op1, Toast.LENGTH_SHORT).show();
                    databaseReference.child(qn).child("Question").setValue(qu);
                    databaseReference.child(qn).child("Question no").setValue(qn);
                    databaseReference.child(qn).child("Option 1").setValue(op1);
                    databaseReference.child(qn).child("Option 2").setValue(op2);
                    databaseReference.child(qn).child("Option 3").setValue(op3);
                    databaseReference.child(qn).child("Correct Option").setValue(lbname);

                    Toast.makeText(MainActivity.this, "Submitted !!", Toast.LENGTH_SHORT).show();

                    qno.setText("");
                    ques.setText("");
                    opt1.setText("");
                    opt2.setText("");
                    opt3.setText("");
                }



        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    qn=qno.getText().toString();
                    qu=ques.getText().toString();
                    op1=opt1.getText().toString();
                    op2=opt2.getText().toString();
                    op3=opt3.getText().toString();
                    // Toast.makeText(MainActivity.this, "Ques: "+qn+"Opt1: "+op1, Toast.LENGTH_SHORT).show();
                    databaseReference.child(qn).child("Question").setValue(qu);
                    databaseReference.child(qn).child("Option 1").setValue(op1);
                     databaseReference.child(qn).child("Question no").setValue(qn);
                    databaseReference.child(qn).child("Option 2").setValue(op2);
                    databaseReference.child(qn).child("Option 3").setValue(op3);
                    databaseReference.child(qn).child("Correct Option").setValue(lbname);

                    Toast.makeText(MainActivity.this, "Finished !!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);


                }



        });



    }

    @Override

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
        //Toast.makeText(this, ""+category[position], Toast.LENGTH_SHORT).show();
        lbname=category[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing is selected!!", Toast.LENGTH_SHORT).show();
    }
}
