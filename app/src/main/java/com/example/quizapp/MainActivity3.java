package com.example.quizapp;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btnsub;
    TextView txt1, txt2, txt3;
    int score=0;
    int totalQuestion= QuestionAnswers.Questions.length;
     int currentQuestionIndex =0;
     String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        txt1= findViewById(R.id.totalQuestions);
        txt2= findViewById(R.id.longQuestion);
        txt3= findViewById(R.id.textViewTimer);
        //
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000){
            @Override
            public void onTick(long l) {
                String finalDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                        , TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l)
                                -TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                txt3.setText(finalDuration);
            }

            @Override
            public void onFinish() {
                txt3.setVisibility(View.GONE);
                Toast.makeText(MainActivity3.this, "Time Up!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();

        btn1=findViewById(R.id.Ans1);
        btn2=findViewById(R.id.Ans2);
        btn3=findViewById(R.id.Ans3);
        btn4=findViewById(R.id.Ans4);
        btnsub=findViewById(R.id.submit);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        txt1.setText("Total Questions :-"+totalQuestion);
        LoadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        Button finalButton = (Button) view;
        btn1.setBackgroundColor(Color.WHITE);
        btn2.setBackgroundColor(Color.WHITE);
        btn3.setBackgroundColor(Color.WHITE);
        btn4.setBackgroundColor(Color.WHITE);

            if (finalButton.getId() == R.id.submit) {
                if (selectedAnswer.equals(QuestionAnswers.correct[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
                LoadNewQuestion();
            } else {
                selectedAnswer = finalButton.getText().toString();
                finalButton.setBackgroundColor(Color.MAGENTA);
            }

        }

    void LoadNewQuestion() {
            if (currentQuestionIndex == totalQuestion) {
                finishQuiz();
                return;
            }
            txt2.setText(QuestionAnswers.Questions[currentQuestionIndex]);
            btn1.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
            btn2.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
            btn3.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
            btn4.setText(QuestionAnswers.choices[currentQuestionIndex][3]);


    }

    void finishQuiz(){
        String PassStatus="";
        if(score> totalQuestion*0.60){
            PassStatus="PASS";
        }
        else
        {
            PassStatus="TRY AGAIN";
        }
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle(PassStatus).setMessage(" YOUR SCORE IS "+ score+" OUT OF "+totalQuestion).setPositiveButton("RESTART QUIZ", (dialogInterface, i) -> restartQuiz()).setNegativeButton("EXIT APP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        }).setCancelable(false).show();
    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        LoadNewQuestion();
    }
}