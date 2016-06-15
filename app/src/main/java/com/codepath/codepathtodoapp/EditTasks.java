package com.codepath.codepathtodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tasks);
        String etEditTask = getIntent().getStringExtra("etEditTask");

    }

    public void onSubmit(View v) {
        //EditText etTask = (EditText) findViewById(R.id.etEditTask);
        Intent data = new Intent();
        data.putExtra("etEditTask", "etTask.getText().toString()");
        int position = data.getIntExtra("position", 0);
        setResult(RESULT_OK, data);
        finish();
    }
}
