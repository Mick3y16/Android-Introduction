package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 13/08/2016.
 */
public class TaskDataSource {

    /**
     * SQLite database to where task data is written.
     */
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Helps manage the table which will contain the data.
     */
    private TaskSQLiteHelper taskSQLiteHelper;

    /**
     * Array containing the name of all the columns in the task table.
     */
    private String[] columns = new String[] { TaskSQLiteHelper.COLUMN_ID, TaskSQLiteHelper.COLUMN_TASK };

    /**
     * Creates na instance of task data source that will handle the database.
     *
     * @param context Application context.
     */
    public TaskDataSource(Context context) {
        this.taskSQLiteHelper = new TaskSQLiteHelper(context);
    }

    /**
     * Open the SQLite database.
     *
     * @throws SQLException Thrown when an SQL error occurs.
     */
    public void open() throws SQLException {
        this.sqLiteDatabase = this.taskSQLiteHelper.getWritableDatabase();
    }

    /**
     * Closes the SQLite helper.
     */
    public void close() {
        this.taskSQLiteHelper.close();
    }

    /**
     * Creates a task given it description.
     *
     * @param task Description of the task.
     * @return The newly created task.
     */
    public Task createTask(String task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskSQLiteHelper.COLUMN_TASK, task);

        long id = this.sqLiteDatabase.insert(TaskSQLiteHelper.TABLE_TASKS, null, contentValues);

        Cursor cursor = this.sqLiteDatabase.query(TaskSQLiteHelper.TABLE_TASKS, this.columns,
                TaskSQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();

        return newTask;
    }

    /**
     * Deletes a task from the database.
     *
     * @param task Task to delete.
     */
    public void deleteTask(Task task) {
        long id = task.getId();
        this.sqLiteDatabase.delete(TaskSQLiteHelper.TABLE_TASKS, TaskSQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    /**
     * Returns all the tasks selected by the user.
     *
     * @return List of selected tasks.
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        Cursor cursor = this.sqLiteDatabase.query(TaskSQLiteHelper.TABLE_TASKS,
                this.columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            tasks.add(cursorToTask(cursor));
            cursor.moveToNext();
        }

        // Closing the cursor.
        cursor.close();

        return tasks;
    }

    /**
     * Extracts a task from a cursor.
     *
     * @param cursor Cursor containing a task.
     * @return Task extracted from the cursor.
     */
    private Task cursorToTask(Cursor cursor) {
        return new Task(cursor.getLong(0), cursor.getString(1));
    }

}
