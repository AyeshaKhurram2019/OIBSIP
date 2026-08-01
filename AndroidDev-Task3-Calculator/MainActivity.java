package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDisplay;
    private StringBuilder inputBuilder = new StringBuilder();
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        int[] buttonIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply,
            R.id.btnDivide, R.id.btnClear, R.id.btnBackspace, R.id.btnEquals
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Button b = (Button) v;
        String text = b.getText().toString();

        if (id == R.id.btnClear) {
            inputBuilder.setLength(0);
            tvDisplay.setText("0");
            isNewOp = true;
        } else if (id == R.id.btnBackspace) {
            if (inputBuilder.length() > 0) {
                inputBuilder.deleteCharAt(inputBuilder.length() - 1);
                tvDisplay.setText(inputBuilder.length() > 0 ? inputBuilder.toString() : "0");
            }
        } else if (id == R.id.btnEquals) {
            calculateResult();
        } else {
            if (isNewOp && !isOperator(text)) {
                inputBuilder.setLength(0);
            }
            isNewOp = false;

            if (isOperator(text) && inputBuilder.length() > 0) {
                char lastChar = inputBuilder.charAt(inputBuilder.length() - 1);
                if (isOperator(String.valueOf(lastChar))) {
                    inputBuilder.setCharAt(inputBuilder.length() - 1, text.charAt(0));
                    tvDisplay.setText(inputBuilder.toString());
                    return;
                }
            }

            inputBuilder.append(text);
            tvDisplay.setText(inputBuilder.toString());
        }
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("−") || s.equals("×") || s.equals("÷");
    }

    private void calculateResult() {
        String expr = inputBuilder.toString();
        if (expr.isEmpty()) return;

        try {
            String[] tokens;
            char op = ' ';
            if (expr.contains("+")) { op = '+'; tokens = expr.split("\\+"); }
            else if (expr.contains("−")) { op = '−'; tokens = expr.split("−"); }
            else if (expr.contains("×")) { op = '×'; tokens = expr.split("×"); }
            else if (expr.contains("÷")) { op = '÷'; tokens = expr.split("÷"); }
            else return;

            if (tokens.length < 2) return;

            double num1 = Double.parseDouble(tokens[0]);
            double num2 = Double.parseDouble(tokens[1]);
            double result = 0;

            if (op == '÷') {
                if (num2 == 0) {
                    tvDisplay.setText("Error");
                    inputBuilder.setLength(0);
                    isNewOp = true;
                    return;
                }
                result = num1 / num2;
            } else if (op == '+') result = num1 + num2;
            else if (op == '−') result = num1 - num2;
            else if (op == '×') result = num1 * num2;

            String resultStr = (result % 1 == 0) ? String.valueOf((long) result) : String.valueOf(result);
            tvDisplay.setText(resultStr);
            inputBuilder.setLength(0);
            inputBuilder.append(resultStr);
            isNewOp = true;

        } catch (Exception e) {
            tvDisplay.setText("Error");
            inputBuilder.setLength(0);
            isNewOp = true;
        }
    }
              }
