package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pedro on 12/08/2016.
 */
public class TaskSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Name of the table of the tasks.
     */
    public static final String TABLE_TASKS = "tasks";

    /**
     * Name of the column with the id of the taks.
     */
    public static final String COLUMN_ID = "id";

    /**
     * Name of the column with the description of the task.
     */
    public static final String COLUMN_TASK = "task";

    /**
     * Name of the database.
     */
    private static final String DATABASE_NAME = "tasks.db";

    /**
     * Version of the database.
     */
    private static final int DATABASE_VERSION = 9;

    /**
     * SQL instruction to create the database.
     */
    private static final String DATABASE_CREATE = "" +
            "create table " + TABLE_TASKS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TASK + " text not null);";

    /**
     * Creates an instance of a Task SQLite Helper through a context.
     *
     * @param context Application context.
     */
    public TaskSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TaskSQLiteHelper.class.getName(), "Upgrading database from version" + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(sqLiteDatabase);
    }

}
