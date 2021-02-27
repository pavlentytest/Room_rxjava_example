package ru.pavlenty.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTask, editTextDesc, editTextFinishBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            saveTask();


            }
        });
    }

    private void saveTask() {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError(getResources().getString(R.string.required));
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError(getResources().getString(R.string.required));
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError(getResources().getString(R.string.required));
            editTextFinishBy.requestFocus();
            return;
        }

        Task task = new Task();
        task.setTask(sTask);
        task.setDesc(sDesc);
        task.setFinishBy(sFinishBy);
        task.setFinished(false);

        DBClient.getInstance(getApplicationContext()).getAppDatabase()
                .taskDao()
                .insert(task)
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread()) // данные прийдут в главный поток
                .subscribe( () -> {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                });


    }
}
