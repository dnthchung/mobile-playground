package vn.edu.fpt.brickgameapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private FrameLayout gameArea;
    private Button fireButton;
    private int score = 0;
    private ArrayList<View> bricks;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.textView_score);
        gameArea = findViewById(R.id.game_area);
        fireButton = findViewById(R.id.button_fire);
        bricks = new ArrayList<>();
        random = new Random();

        // Wait until gameArea is laid out before setting up the game
        gameArea.post(new Runnable() {
            @Override
            public void run() {
                setupGame();
            }
        });

        fireButton.setOnClickListener(v -> {
            checkCollision();
        });
    }

    private void setupGame() {
        // Ensure gameArea has a width greater than zero
        if (gameArea.getWidth() <= 0 || gameArea.getHeight() <= 0) return;

        // Create bricks dynamically with different colors and add them to the game area
        for (int i = 0; i < 10; i++) {
            View brick = new View(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 50);
            params.setMargins(random.nextInt(gameArea.getWidth() - 150), random.nextInt(gameArea.getHeight() - 50), 0, 0);
            brick.setLayoutParams(params);

            int color = getRandomBrickColor();
            brick.setBackgroundColor(ContextCompat.getColor(this, color));
            brick.setTag(color);

            bricks.add(brick);
            gameArea.addView(brick);
        }
    }

    private int getRandomBrickColor() {
        int[] colors = {
                R.color.brickRed,
                R.color.brickYellow,
                R.color.brickGreen,
                R.color.brickBlue
        };
        return colors[random.nextInt(colors.length)];
    }

    private void checkCollision() {
        if (bricks.isEmpty()) return;

        // Iterate through the bricks to simulate a collision check
        for (int i = 0; i < bricks.size(); i++) {
            View brick = bricks.get(i);
            int color = (int) brick.getTag();
            int shotsRequired = getShotsRequired(color);

            shotsRequired--;

            if (shotsRequired <= 0) {
                // Brick is destroyed, remove it and update the score
                gameArea.removeView(brick);
                bricks.remove(i);
                i--; // Adjust index after removal
                score += 10;
                scoreTextView.setText(getString(R.string.score_label) + " " + score);
            } else {
                // Update the tag to represent the remaining shots needed
                brick.setTag(shotsRequired);
            }
        }
    }


    private int getShotsRequired(int color) {
        if (color == R.color.brickRed) return 3;
        if (color == R.color.brickYellow) return 6;
        if (color == R.color.brickGreen) return 4;
        return 2; // Default for blue bricks
    }
}
