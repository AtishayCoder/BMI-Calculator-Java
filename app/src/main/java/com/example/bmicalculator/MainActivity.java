package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        feet = findViewById(R.id.feet);
        inches = findViewById(R.id.inches);
        weight = findViewById(R.id.weight);
        calculate = findViewById(R.id.calc_b);
        resultText = findViewById(R.id.result);
    }

    /** @noinspection Convert2Lambda*/
    private void setupButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBmi();
                displayResult(bmi);
            }
        });
    }

    private double calculateBmi() {
        String feetValue = feet.getText().toString();
        String inchesValue = inches.getText().toString();
        String weightValue = weight.getText().toString();

        int feetInt = Integer.parseInt(feetValue);
        int inchesInt = Integer.parseInt(inchesValue);
        int weightInt = Integer.parseInt(weightValue);

        int totalInches = (feetInt * 12) + inchesInt;
        double meters = totalInches * 0.0254;
        return weightInt / (meters * meters);
    }

    private void displayResult(double bmi) {
        DecimalFormat df = new DecimalFormat("0.00");
        String bmiString = df.format(bmi);

        if (bmi < 18.5) {
            resultText.setText(bmiString + ". Underweight.");
        } else if (bmi < 25) {
            resultText.setText(bmiString + ". Normal weight.");
        } else if (bmi < 30) {
            resultText.setText(bmiString + ". Overweight.");
        } else {
            resultText.setText(bmiString + ". Obese.");
        }
    }
}