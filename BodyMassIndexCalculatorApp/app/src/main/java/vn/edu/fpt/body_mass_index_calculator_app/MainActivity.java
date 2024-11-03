package vn.edu.fpt.body_mass_index_calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private RadioGroup radioGroupUnits;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewFormula;
    private TextView textViewCalculation;
    private TextView textViewWeightUnit;
    private TextView textViewHeightUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextWeight = findViewById(R.id.editText_weight);
        editTextHeight = findViewById(R.id.editText_height);
        radioGroupUnits = findViewById(R.id.radioGroup_units);
        buttonCalculate = findViewById(R.id.button_calculate);
        textViewResult = findViewById(R.id.textView_result);
        textViewFormula = findViewById(R.id.textView_formula);
        textViewCalculation = findViewById(R.id.textView_calculation);
        textViewWeightUnit = findViewById(R.id.textView_weight_unit);
        textViewHeightUnit = findViewById(R.id.textView_height_unit);

        // Set unit labels based on selected unit type
        radioGroupUnits.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_metric) {
                    textViewWeightUnit.setText("kg");
                    textViewHeightUnit.setText("cm");
                } else if (checkedId == R.id.radio_english) {
                    textViewWeightUnit.setText("lbs");
                    textViewHeightUnit.setText("inches");
                }
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            textViewResult.setText("Please enter both weight and height.");
            textViewFormula.setText("");
            textViewCalculation.setText("");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        int selectedUnitId = radioGroupUnits.getCheckedRadioButtonId();
        if (selectedUnitId == -1) {
            textViewResult.setText("Please select a unit type.");
            textViewFormula.setText("");
            textViewCalculation.setText("");
            return;
        }

        double bmi;
        String formula;
        String calculationDetails;

        if (selectedUnitId == R.id.radio_english) {
            bmi = (weight * 703) / (height * height);
            formula = "BMI = (Weight in Pounds × 703) / (Height in Inches)^2";
            calculationDetails = String.format("Calculation: (%.2f lbs × 703) / (%.2f inches)^2", weight, height);
        } else {
            // Convert height from cm to m for the metric calculation
            height = height / 100.0;
            bmi = weight / (height * height);
            formula = "BMI = Weight in Kilograms / (Height in Meters)^2";
            calculationDetails = String.format("Calculation: %.2f kg / (%.2f m)^2", weight, height);
        }

        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            bmiCategory = "Normal";
        } else if (bmi >= 25 && bmi <= 29.9) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }

        String result = String.format("Your BMI: %.2f (%s)", bmi, bmiCategory);
        textViewResult.setText(result);
        textViewFormula.setText("BMI Formula: " + formula);
        textViewCalculation.setText("Calculation Details: " + calculationDetails);
    }
}
