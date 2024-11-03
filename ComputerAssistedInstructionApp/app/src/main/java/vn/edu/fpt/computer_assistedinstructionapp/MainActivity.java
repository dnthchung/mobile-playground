package vn.edu.fpt.computer_assistedinstructionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.button.MaterialButton;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView questionTextView;
    private TextInputEditText answerEditText;
    private MaterialButton checkButton;
    private MaterialTextView feedbackTextView;
    private int correctAnswer;
    private final String[] correctResponses = {
            "Very good!", "Excellent!", "Nice work!", "Keep up the good work!"
    };
    private final String[] incorrectResponses = {
            "No. Please try again.", "Wrong. Try once more.", "Don't give up!", "No. Keep trying."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.textView_question);
        answerEditText = findViewById(R.id.editText_answer);
        checkButton = findViewById(R.id.button_check);
        feedbackTextView = findViewById(R.id.textView_feedback);

        generateQuestion();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = answerEditText.getText().toString().trim();
                if (!userInput.isEmpty()) {
                    int userAnswer = Integer.parseInt(userInput);
                    if (userAnswer == correctAnswer) {
                        feedbackTextView.setText(getRandomResponse(correctResponses));
                        generateQuestion();
                    } else {
                        feedbackTextView.setText(getRandomResponse(incorrectResponses));
                    }
                }
            }
        });
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(9) + 1; // Random number between 1 and 9
        int num2 = random.nextInt(9) + 1; // Random number between 1 and 9
        int operation = random.nextInt(3); // Random operation: 0 = +, 1 = -, 2 = *

        String operationSymbol;
        switch (operation) {
            case 0:
                operationSymbol = "+";
                correctAnswer = num1 + num2;
                break;
            case 1:
                operationSymbol = "-";
                correctAnswer = num1 - num2;
                break;
            default:
                operationSymbol = "×";
                correctAnswer = num1 * num2;
                break;
        }

        String questionText = "How much is " + num1 + " " + operationSymbol + " " + num2 + "?";
        questionTextView.setText(questionText);
        answerEditText.setText("");
    }

    private String getRandomResponse(String[] responses) {
        Random random = new Random();
        int index = random.nextInt(responses.length);
        return responses[index];
    }
}
