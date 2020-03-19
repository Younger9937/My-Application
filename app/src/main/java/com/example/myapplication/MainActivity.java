package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //共使用了6种View
    private TextView textMath;
    private EditText editName;
    private EditText editOther;
    private EditText editMath;
    private Button buttonFinish;
    private Button buttonChange;
    private ImageView imageView;
    private RadioButton manButton;
    private RadioButton womanButton;
    private CheckBox checkDraw;
    private CheckBox checkSing;
    private CheckBox checkDance;
    private CheckBox checkBall;
    private int add1;
    private int add2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        buttonFinish = findViewById(R.id.buttonFinish);
        textMath = findViewById(R.id.textMath);
        buttonChange = findViewById(R.id.buttonChange);
        imageView = findViewById(R.id.imageView);
        manButton = findViewById(R.id.manButton);
        womanButton = findViewById(R.id.womanButton);
        checkDraw = findViewById(R.id.checkDraw);
        checkSing = findViewById(R.id.checkSing);
        checkDance = findViewById(R.id.checkDance);
        checkBall = findViewById(R.id.checkBall);
        editOther = findViewById(R.id.editOther);
        editMath = findViewById(R.id.editMath);

        //随机产生一个加法式
        add1 = (int)(Math.random()*10);
        add2 = (int)(Math.random()*20);
        String str = String.valueOf(add1)+ "+" + String.valueOf(add2) + "=";
        textMath.setText(str);

        //初始时，由于信息还未填写，故应为照片'no'
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.no));

        //完成按钮被点击
        buttonFinish.setOnClickListener(button_finish);

        //更换题目的按钮被点击
        buttonChange.setOnClickListener(button_change);
    }

    Button.OnClickListener button_finish = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            Log.d(TAG,"buttonFinished is clicked");

            int judge=0;//用于判断所有信息是否已全部填写完毕

            if(TextUtils.isEmpty(editName.getText())){
                Log.d(TAG,"No name");
                Toast.makeText(getApplicationContext(), "Sorry,you didn't fill in your name", Toast.LENGTH_SHORT).show();
            }
            else{
                Log.d(TAG,"Have name");
                if(!manButton.isChecked() && !womanButton.isChecked()){
                    Log.d(TAG,"No gender");
                    Toast.makeText(getApplicationContext(), "Sorry,you didn't choose your gender", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d(TAG,"Have gender");
                    if(!checkDraw.isChecked() && !checkSing.isChecked() && !checkDance.isChecked() && !checkBall.isChecked() && TextUtils.isEmpty(editOther.getText())){
                        Log.d(TAG,"No hobby");
                        Toast.makeText(getApplicationContext(), "Sorry,you didn't choose your hobby", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d(TAG,"Have hobby");
                        if(TextUtils.isEmpty(editMath.getText()) || Integer.parseInt(editMath.getText().toString()) != add1 + add2){
                            Log.d(TAG,"Answer wrong");
                            Toast.makeText(getApplicationContext(), "Sorry,your answer is wrong", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.d(TAG,"Answer right");
                            Log.d(TAG,"Success!!Replace the photo with a 'yes' symbol");
                            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.yes));
                            judge=1;
                        }
                    }
                }
            }
            if(judge == 0){
                Log.d(TAG,"Fail!!Replace the photo with a 'no' symbol");
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.no));
            }
        }
    };

    Button.OnClickListener button_change = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            Log.d(TAG,"buttonChange is clicked");
            Log.d(TAG,"Change question");
            add1 = (int)(Math.random()*10);
            add2 = (int)(Math.random()*20);
            String str = String.valueOf(add1)+ "+" + String.valueOf(add2) + "=";
            textMath.setText(str);
        }
    };
}
