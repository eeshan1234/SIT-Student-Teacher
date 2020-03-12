package com.example.sitstudentteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class drive extends AppCompatActivity {
    Button b1;
    EditText e1;
   String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        b1=findViewById(R.id.btn);
        e1=findViewById(R.id.sub);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=e1.getText().toString();
                if(!str.equals(""))
                {
                    Intent intent=new Intent(drive.this,MainActivity.class);
                    intent.putExtra("Sub",str);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(drive.this, "Fill the subject code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
