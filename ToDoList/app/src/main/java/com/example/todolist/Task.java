package com.example.todolist;

/**
 * Created by pedro on 12/08/2016.
 */
public class Task {

    /**
     * Id of the task.
     */
    private long id;

    /**
     * Description of the task.
     */
    private String task;

    /**
     * Creates an instance of a task through an id and the task's description.
     *
     * @param id ID of the task.
     * @param task Description of the task.
     */
    public Task(long id, String task) {
        this.id = id;
        this.task = task;
    }

    /**
     * Returns the ID of the task.
     *
     * @return The ID of the task.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
