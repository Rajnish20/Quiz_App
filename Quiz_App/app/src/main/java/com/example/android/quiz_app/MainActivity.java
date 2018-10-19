package com.example.android.quiz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void quiz(View view){
        EditText editText=(EditText) findViewById(R.id.name_edit_text);
        String name=editText.getText().toString();



        Intent intent= new Intent(this,Main2Activity.class);
        intent.putExtra(intent.EXTRA_TEXT,"Welcome" +name);
        startActivity(intent);

    }
}
