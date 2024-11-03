package vn.edu.fpt.word_scramble_game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView textViewScrambledWord, textViewScore, textViewDefinition;
    private EditText editTextAnswer;
    private Button buttonSubmit, buttonHint, buttonSkip, buttonReset;
    private String currentWord;
    private String currentHint;
    private int score = 0;
    private boolean hintUsed = false;
    private SharedPreferences sharedPreferences;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        textViewScrambledWord = findViewById(R.id.textView_scrambledWord);
        textViewScore = findViewById(R.id.textView_score);
        textViewDefinition = findViewById(R.id.textView_definition);
        editTextAnswer = findViewById(R.id.editText_answer);
        buttonSubmit = findViewById(R.id.button_submit);
        buttonHint = findViewById(R.id.button_hint);
        buttonSkip = findViewById(R.id.button_skip);
        buttonReset = findViewById(R.id.button_reset);

        // Initialize SharedPreferences for high score
        sharedPreferences = getSharedPreferences("WordScramblePrefs", MODE_PRIVATE);
        score = sharedPreferences.getInt("highScore", 0);
        updateScore();

        // Start the game with a scrambled word
        startNewGame();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint();
            }
        });

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipQuestion();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void startNewGame() {
        String[] words = {
                "elephant", "tiger", "giraffe", "kangaroo", "panda", "penguin",
                "dolphin", "eagle", "crocodile", "cheetah", "hippopotamus",
                "rhinoceros", "ostrich", "zebra", "lion", "parrot", "monkey",
                "koala", "bat", "squirrel", "whale", "shark", "octopus",
                "lobster", "tortoise", "wolf", "bear", "camel", "rabbit", "owl"
        };

        String[] definitions = {
                "Một loài động vật lớn với vòi dài, sống ở châu Phi và châu Á.",
                "Một loài mèo lớn, nổi tiếng với vằn đen và cam.",
                "Một loài động vật cao với cổ dài.",
                "Một loài thú có túi đến từ Úc, nổi tiếng với việc nhảy.",
                "Một loài gấu ăn tre, sống chủ yếu ở Trung Quốc.",
                "Một loài chim không biết bay và sống ở Nam Cực.",
                "Một loài động vật có vú sống dưới nước, rất thông minh.",
                "Một loài chim săn mồi với tầm nhìn rất tốt.",
                "Một loài bò sát lớn sống dưới nước, có da dày.",
                "Động vật nhanh nhất trên mặt đất.",
                "Một loài động vật lớn sống dưới nước, có thân hình tròn.",
                "Một loài động vật lớn với sừng trên mũi.",
                "Một loài chim không bay lớn nhất thế giới.",
                "Một loài ngựa có vằn trắng đen.",
                "Vua của rừng xanh.",
                "Một loài chim biết nói.",
                "Động vật thông minh, thích leo cây.",
                "Một loài gấu nhỏ sống ở Úc.",
                "Một loài thú bay ban đêm.",
                "Một loài động vật nhỏ, thích ăn hạt và leo cây.",
                "Động vật lớn nhất đại dương.",
                "Một loài cá nguy hiểm với hàm răng sắc nhọn.",
                "Một loài động vật biển có tám tay.",
                "Một loài giáp xác với càng lớn.",
                "Một loài rùa sống lâu trên cạn.",
                "Một loài chó hoang sống thành bầy.",
                "Một loài động vật lớn, sống trong rừng và rất mạnh mẽ.",
                "Một loài động vật có hai bướu hoặc một bướu.",
                "Một loài thỏ nhỏ nhắn và đáng yêu.",
                "Một loài chim có thể xoay đầu 270 độ."
        };

        int index = (int) (Math.random() * words.length);
        currentWord = words[index];
        currentHint = definitions[index];
        textViewScrambledWord.setText(scrambleWord(currentWord));
        textViewDefinition.setVisibility(View.GONE);
        hintUsed = false;
    }

    private String scrambleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        StringBuilder scrambled = new StringBuilder();
        for (String letter : letters) {
            scrambled.append(letter);
        }
        return scrambled.toString();
    }

    private void checkAnswer() {
        String userAnswer = editTextAnswer.getText().toString().trim();
        if (userAnswer.equalsIgnoreCase(currentWord)) {
            Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
            score++;

            saveHighScore();
            updateScore();
            startNewGame();
            editTextAnswer.setText("");
        } else {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    private void showHint() {
        if (!hintUsed) {
            textViewDefinition.setText(getString(R.string.hint, currentHint));
            textViewDefinition.setVisibility(View.VISIBLE);
            hintUsed = true;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewDefinition.setVisibility(View.GONE);
                }
            }, 4000); // Hint is shown for 4 seconds
        } else {
            Toast.makeText(this, getString(R.string.hint_already_used), Toast.LENGTH_SHORT).show();
        }
    }

    private void skipQuestion() {
        Toast.makeText(this, getString(R.string.question_skipped), Toast.LENGTH_SHORT).show();
        startNewGame();
        editTextAnswer.setText("");
    }

    private void resetGame() {
        score = 0;
        saveHighScore();
        updateScore();
        Toast.makeText(this, getString(R.string.game_reset), Toast.LENGTH_SHORT).show();
        startNewGame();
        editTextAnswer.setText("");
    }

    private void saveHighScore() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highScore", score);
        editor.apply();
    }

    private void updateScore() {
        textViewScore.setText(getString(R.string.score, score));
    }
}
