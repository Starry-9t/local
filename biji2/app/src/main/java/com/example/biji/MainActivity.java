package com.example.biji;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class MainActivity extends AppCompatActivity {

    //hooks
    View star_1,star_2,star_3,star_4;
    ImageView logo;
    TextView tv;


    //animations
    Animation topAnimation,middleAnimation,bottomAnimation,endAnimation;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ImageButton imageButton= (ImageButton) findViewById(R.id.ib_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        //开屏动画
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation);
        endAnimation=AnimationUtils.loadAnimation(this,R.anim.end_animation);
        star_1=findViewById(R.id.cloud);
        star_2=findViewById(R.id.star_2);
        star_3=findViewById(R.id.star_3);
        star_4=findViewById(R.id.star_4);
        logo=findViewById(R.id.logo);
        tv=findViewById(R.id.tv);


        star_2.setAnimation(topAnimation);
        star_3.setAnimation(topAnimation);
        star_4.setAnimation(topAnimation);
        star_1.setAnimation(middleAnimation);
        logo.setAnimation(endAnimation);
        tv.setAnimation(bottomAnimation);
    }


}