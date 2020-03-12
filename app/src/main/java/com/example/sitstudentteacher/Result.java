package com.example.sitstudentteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Result extends AppCompatActivity {
Intent intent;
Button b;
TextView score,per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score=findViewById(R.id.score);

        b=findViewById(R.id.btn);

        intent=getIntent();
        String res=intent.getExtras().getString("sum").toString();

        score.setText("No of correct questions: "+res);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Result.this,HomeStudent.class);
                startActivity(intent);
            }
        });
       // Toast.makeText(this, "Sum is = "+res, Toast.LENGTH_SHORT).show();
    }
}
