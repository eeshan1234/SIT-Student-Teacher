package com.example.sitstudentteacher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Taketest extends AppCompatActivity {

    RadioButton rb1,rb2,rb3;
    TextView qno,ques;
    RadioGroup radioGroup;
    Button b;
    String subcode="Mat01";
    int sum;
    String coption1;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taketest);
        radioGroup=findViewById(R.id.rg1);
        qno=findViewById(R.id.qno);
        ques=findViewById(R.id.ques);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        b=findViewById(R.id.submit);

       final DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("Subjects");
        sum=0;
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String quesno=dataSnapshot.child(subcode).child("1").child("Question no").getValue().toString();
                String question=dataSnapshot.child(subcode).child("1").child("Question").getValue().toString();
                String option1=dataSnapshot.child(subcode).child("1").child("Option 1").getValue().toString();
                String option2=dataSnapshot.child(subcode).child("1").child("Option 2").getValue().toString();
                String option3=dataSnapshot.child(subcode).child("1").child("Option 3").getValue().toString();
                String coption=dataSnapshot.child(subcode).child("1").child("Correct Option").getValue().toString();

                int getid=Integer.parseInt(coption);
                qno.setText(quesno);
                ques.setText(question);
                rb1.setText(option1);
                rb2.setText(option2);
                rb3.setText(option3);

                int getans=Integer.parseInt(coption);
                int a,ans=1;
                if((a=radioGroup.getCheckedRadioButtonId())==2131165311)
                {
                    ans=1;
                }
                else if((a=radioGroup.getCheckedRadioButtonId())==2131165312)
                {
                    ans=2;
                }
                else if
                ((a=radioGroup.getCheckedRadioButtonId())==2131165313)
                {
                    ans=3;
                }

                if(getans==ans)
                {
                    sum=1;

                    Toast.makeText(Taketest.this, "Correct", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Taketest.this, "Sum: "+sum, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       // final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Subjects");
        i=2;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       // Toast.makeText(Taketest.this, "i= "+dataSnapshot.child(subcode).getChildrenCount(), Toast.LENGTH_SHORT).show();
                        if(i > dataSnapshot.child(subcode).getChildrenCount())
                        {
                            Intent intent=new Intent(Taketest.this,Result.class);
//                            sum=sum/(int)dataSnapshot.getChildrenCount()*100;
                            String sumstr=""+sum+" / "+(dataSnapshot.getChildrenCount()-1);
                            intent.putExtra("sum",sumstr);
                            startActivity(intent);
                        }
                        if(i <= dataSnapshot.child(subcode).getChildrenCount())
                        {
                            String quesno=dataSnapshot.child(subcode).child(""+i).child("Question no").getValue().toString();
                            String question=dataSnapshot.child(subcode).child(""+i).child("Question").getValue().toString();
                            String option1=dataSnapshot.child(subcode).child(""+i).child("Option 1").getValue().toString();
                            String option2=dataSnapshot.child(subcode).child(""+i).child("Option 2").getValue().toString();
                            String option3=dataSnapshot.child(subcode).child(""+i).child("Option 3").getValue().toString();
                            coption1=dataSnapshot.child(subcode).child(""+i).child("Correct Option").getValue().toString();


                            qno.setText(quesno);
                            ques.setText(question);
                            rb1.setText(option1);
                            rb2.setText(option2);
                            rb3.setText(option3);



                            i++;
                        }

                        int getans=Integer.parseInt(coption1);
                        int a,ans=1;
                        if((a=radioGroup.getCheckedRadioButtonId())==2131165311)
                        {
                            ans=1;
                        }
                        else if((a=radioGroup.getCheckedRadioButtonId())==2131165312)
                        {
                            ans=2;
                        }
                        else if
                        ((a=radioGroup.getCheckedRadioButtonId())==2131165313)
                        {
                            ans=3;
                        }

                        if(getans==ans)
                        {
                            sum=sum+1;
                           //Toast.makeText(Taketest.this, "Correct", Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}
