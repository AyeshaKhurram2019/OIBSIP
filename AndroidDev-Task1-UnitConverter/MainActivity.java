package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory, spinnerFrom, spinnerTo;
    private EditText editTextInput;
    private Button buttonConvert;
    private TextView textViewResult;

    private String[] categories = {"Length", "Weight", "Temperature"};
    
    private String[] lengthUnits = {"Centimeters", "Meters", "Inches", "Feet"};
    private String[] weightUnits = {"Grams", "Kilograms", "Pounds"};
    private String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextInput = findViewById(R.id.editTextInput);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubSpinners(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void updateSubSpinners(int categoryPosition) {
        String[] currentUnits;
        if (categoryPosition == 0) {
            currentUnits = lengthUnits;
        } else if (categoryPosition == 1) {
            currentUnits = weightUnits;
        } else {
            currentUnits = tempUnits;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currentUnits);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(unitAdapter);
        spinnerTo.setAdapter(unitAdapter);
    }

    private void performConversion() {
        String inputStr = editTextInput.getText().toString().trim();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a valid numeric value!", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;
        try {
            inputValue = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format!", Toast.LENGTH_SHORT).show();
            return;
        }

        int categoryIndex = spinnerCategory.getSelectedItemPosition();
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();
        double result = 0;

        if (categoryIndex == 0) {
            result = convertLength(inputValue, fromUnit, toUnit);
        } else if (categoryIndex == 1) {
            result = convertWeight(inputValue, fromUnit, toUnit);
        } else if (categoryIndex == 2) {
            result = convertTemperature(inputValue, fromUnit, toUnit);
        }

        textViewResult.setText(String.format("Result: %.2f %s", result, toUnit));
    }

    private double convertLength(double val, String from, String to) {
        double meters = 0;
        switch (from) {
            case "Centimeters": meters = val / 100; break;
            case "Meters": meters = val; break;
            case "Inches": meters = val * 0.0254; break;
            case "Feet": meters = val * 0.3048; break;
        }
        switch (to) {
            case "Centimeters": return meters * 100;
            case "Meters": return meters;
            case "Inches": return meters / 0.0254;
            case "Feet": return meters / 0.3048;
            default: return meters;
        }
    }

    private double convertWeight(double val, String from, String to) {
        double kg = 0;
        switch (from) {
            case "Grams": kg = val / 1000; break;
            case "Kilograms": kg = val; break;
            case "Pounds": kg = val * 0.453592; break;
        }
        switch (to) {
            case "Grams": return kg * 1000;
            case "Kilograms": return kg;
            case "Pounds": return kg / 0.453592;
            default: return kg;
        }
    }

    private double convertTemperature(double val, String from, String to) {
        double celsius = 0;
        if (from.equals("Celsius")) celsius = val;
        else if (from.equals("Fahrenheit")) celsius = (val - 32) * 5 / 9;
        else if (from.equals("Kelvin")) celsius = val - 273.15;

        if (to.equals("Celsius")) return celsius;
        else if (to.equals("Fahrenheit")) return (celsius * 9 / 5) + 32;
        else if (to.equals("Kelvin")) return celsius + 273.15;

        return celsius;
    }
        }
                           
