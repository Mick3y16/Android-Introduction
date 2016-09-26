package com.example.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * Works directly with SQLite.
     */
    private TaskDataSource taskDataSource;

    /**
     * View where the tasks are displayed.
     */
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.taskDataSource = new TaskDataSource(this);
        this.taskDataSource.open();

        List<Task> tasks = this.taskDataSource.getAllTasks();
        final ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, tasks);

        this.listView = (ListView) findViewById(R.id.listView);
        this.listView.setAdapter(arrayAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task task = (Task) adapterView.getItemAtPosition(position);
                MainActivity.this.taskDataSource.deleteTask(task);
                arrayAdapter.remove(task);
            }
        });
    }

    @Override
    protected void onResume() {
        this.taskDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        this.taskDataSource.close();
        super.onPause();
    }

    /**
     * Represents an action listener called when clicking the add task or exit buttons.
     *
     * @param view View calling this method.
     */
    public void onClick(View view) {
        final ArrayAdapter<Task> arrayAdapter = (ArrayAdapter<Task>) this.listView.getAdapter();

        switch (view.getId()) {
            case R.id.add:
                final CharSequence[] items = {"Cinema", "Shopping", "Running", "Dentist",
                        "Gambling", "Nursery", "Play Football"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick a task").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String selectedTask = (String) items[position];
                        Task task = MainActivity.this.taskDataSource.createTask(selectedTask);
                        arrayAdapter.add(task);
                    }
                });

                // Create alert dialog.
                builder.create().show();
                arrayAdapter.notifyDataSetChanged();

                break;
            case R.id.exit:
                moveTaskToBack(true);
                break;
        }
    }

}
