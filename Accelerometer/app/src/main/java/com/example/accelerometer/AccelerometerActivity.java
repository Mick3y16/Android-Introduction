package com.example.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    /**
     * X coordinate.
     */
    private TextView xCoordinate;

    /**
     * Y coordinate.
     */
    private TextView yCoordinate;

    /**
     * Z coordinate.
     */
    private TextView zCoordinate;

    /**
     * Sensor Manager.
     */
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        this.xCoordinate = (TextView) findViewById(R.id.xcoord);
        this.yCoordinate = (TextView) findViewById(R.id.ycoord);
        this.zCoordinate = (TextView) findViewById(R.id.zcoord);
        this.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Adds a listener to the sensor manager.
        this.sensorManager.registerListener(this, this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sensorManager.registerListener(this, this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Update directions
            this.xCoordinate.setText("X: " + sensorEvent.values[0]);
            this.yCoordinate.setText("Y: " + sensorEvent.values[1]);
            this.zCoordinate.setText("Z: " + sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do nothing...
    }
}
