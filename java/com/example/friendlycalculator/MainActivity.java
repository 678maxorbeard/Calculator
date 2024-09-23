package com.example.friendlycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display = findViewById(R.id.display);
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDot,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button0: appendToInput("0"); break;
            case R.id.button1: appendToInput("1"); break;
            case R.id.button2: appendToInput("2"); break;
            case R.id.button3: appendToInput("3"); break;
            case R.id.button4: appendToInput("4"); break;
            case R.id.button5: appendToInput("5"); break;
            case R.id.button6: appendToInput("6"); break;
            case R.id.button7: appendToInput("7"); break;
            case R.id.button8: appendToInput("8"); break;
            case R.id.button9: appendToInput("9"); break;
            case R.id.buttonDot: appendToInput("."); break;
            case R.id.buttonAdd: setOperator("+"); break;
            case R.id.buttonSubtract: setOperator("-"); break;
            case R.id.buttonMultiply: setOperator("*"); break;
            case R.id.buttonDivide: setOperator("/"); break;
            case R.id.buttonEquals: calculateResult(); break;
        }
    }

    private void appendToInput(String value) {
        currentInput += value;
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
            display.setText("");
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }
}
