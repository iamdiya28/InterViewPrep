package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView= findViewById(R.id.list);
        ArrayList<String> arrayList= new ArrayList<String>();

        arrayList.add("1. You will have only 59 seconds to answer 5 questions\n" +
                "2. Once you select your answer, it can be undone.\n" +
                "3. You can't select any option once time goes off.\n" +
                "4. You can't exit from the Quiz while you're playing.\n" +
                "5. You'll get points on the basis of your correct answer\n");

        ArrayAdapter<String>  arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        Button btn2= findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(inte);
            }
        });

    }
}