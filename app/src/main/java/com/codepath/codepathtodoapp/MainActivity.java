package com.codepath.codepathtodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoTasks;
    ArrayAdapter<String> aToDoAdapter;
    ListView lvTasks;
    EditText etTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvTasks = (ListView) findViewById(R.id.lvTasks);
        lvTasks.setAdapter(aToDoAdapter);
        etTasks = (EditText) findViewById(R.id.etTasks);
        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoTasks.remove(position);
                aToDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Edit = new Intent(MainActivity.this, EditTasks.class);
                Edit.putExtra("position", position);
                startActivityForResult(Edit, REQUEST_CODE);
            }
        });
    }

    private final int REQUEST_CODE = 20;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String etEditTask = data.getExtras().getString("etEditTask");
            Toast.makeText(MainActivity.this, etEditTask, Toast.LENGTH_SHORT).show();
        }
    }

    public void populateArrayItems() {
        //Adds items to the list.
        todoTasks = new ArrayList<String>();
        todoTasks.add("Add Task 1");
        todoTasks.add("Add Task 2");
        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoTasks);
    }

    private void readItems() {
        //Reads a file.
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        //Catches IOException Errors.
        try{
            todoTasks = new ArrayList<String>(FileUtils.readLines(file));
        }catch (IOException Err) {

        }
    }

    private void writeItems() {
        //Writes items to a file.
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        //Catches IOException Errors.
        try{
            FileUtils.writeLines(file, todoTasks);
        }catch (IOException Err) {

        }
    }

    public void onAddTask(View view) {
        aToDoAdapter.add(etTasks.getText().toString());
        etTasks.setText("");
        writeItems();
    }
}
