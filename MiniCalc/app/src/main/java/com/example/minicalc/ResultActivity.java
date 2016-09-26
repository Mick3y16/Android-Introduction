package com.example.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    /**
     * View where the text is displayed.
     */
    private TextView textView;

    /**
     * Received intent with data from the previous activity.
     */
    private Intent intent;

    /**
     * Button.
     */
    private Button button;

    /**
     * First number of the operation.
     */
    private String firstNumber;

    /**
     * Second number of the operation.
     */
    private String secondNumber;

    /**
     * Operation.
     */
    private String operation;

    /**
     * Result of the operation.
     */
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.button = (Button) findViewById(R.id.button);
        this.intent = getIntent();

        // Stores the extras received from the intent.
        this.firstNumber = this.intent.getStringExtra("firstNumber");
        this.secondNumber = this.intent.getStringExtra("secondNumber");
        this.operation = this.intent.getStringExtra("operation");

        try {
            if (this.operation.equals("+")) {
                this.result = Double.parseDouble(this.firstNumber) + Double.parseDouble(this.secondNumber);
            } else if (this.operation.equals("-")) {
                this.result = Double.parseDouble(this.firstNumber) - Double.parseDouble(this.secondNumber);
            } else if (this.operation.equals("*")) {
                this.result = Double.parseDouble(this.firstNumber) * Double.parseDouble(this.secondNumber);
            } else if (this.operation.equals("/")) {
                this.result = Double.parseDouble(this.firstNumber) / Double.parseDouble(this.secondNumber);
            }

            this.textView = (TextView) findViewById(R.id.textViewResult);
            this.textView.setText("The result is " + String.valueOf(this.result));

            this.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("result", String.valueOf(this.result));
        setResult(RESULT_OK, data);
        super.finish();
    }
}
