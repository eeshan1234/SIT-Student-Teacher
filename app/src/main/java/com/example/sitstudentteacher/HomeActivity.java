package com.example.sitstudentteacher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    CardView cdhistory,cddonate;

    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);



        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(100).setStartDelay(800);
        textsplash.animate().translationY(540).alpha(0).setDuration(900).setStartDelay(500);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);
        cdhistory=findViewById(R.id.cdhistory);

        cddonate=findViewById(R.id.cddonate);

        cddonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(Home.this,MainActivity.class);
                Toast.makeText(HomeActivity.this, "Opening drive ..", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://drive.google.com/open?id=1_jQVO8vKZikqOMq81hjvXMhZlCV83wd-"));
//                Intent intent=getIntent();
//                String phno=intent.getExtras().getString("phno","8777022293");
//                i.putExtra("phno",phno);
               startActivity(i);
               // Toast.makeText(HomeActivity.this, "Opening drive ..", Toast.LENGTH_SHORT).show();
            }
        });
        cdhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,drive.class);
//                Intent intent=getIntent();
//                String phno=intent.getExtras().getString("phno","8777022293");
//                i.putExtra("phno",phno);
                startActivity(i);

            }
        });

    }
}

