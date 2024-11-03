package vn.edu.fpt.gomoku;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int GRID_SIZE = 10;
    private Button[][] buttons = new Button[GRID_SIZE][GRID_SIZE];
    private boolean isPlayerXTurn = true; // True for player X, false for player O

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        createGameGrid(gridLayout);
    }

    private void createGameGrid(GridLayout gridLayout) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = new Button(this);
                button.setLayoutParams(new GridLayout.LayoutParams());
                button.setTextSize(24);
                button.setBackgroundResource(R.drawable.cell_border); // Apply the border drawable
                button.setOnClickListener(new CellClickListener(row, col));
                gridLayout.addView(button);
                buttons[row][col] = button;
            }
        }
    }


    private class CellClickListener implements View.OnClickListener {
        private int row;
        private int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            if (button.getText().toString().isEmpty()) {
                button.setText(isPlayerXTurn ? "X" : "O");
                button.setTextColor(isPlayerXTurn ? Color.RED : Color.BLUE);

                if (checkWinCondition(row, col)) {
                    String winner = isPlayerXTurn ? "Player X" : "Player O";
                    Toast.makeText(MainActivity.this, winner + " wins!", Toast.LENGTH_LONG).show();
                    resetGame();
                } else {
                    isPlayerXTurn = !isPlayerXTurn;
                }
            }
        }
    }

    private boolean checkWinCondition(int row, int col) {
        String currentPlayerSymbol = isPlayerXTurn ? "X" : "O";
        return checkDirection(row, col, 1, 0, currentPlayerSymbol)  // Horizontal
                || checkDirection(row, col, 0, 1, currentPlayerSymbol)  // Vertical
                || checkDirection(row, col, 1, 1, currentPlayerSymbol)  // Diagonal \
                || checkDirection(row, col, 1, -1, currentPlayerSymbol); // Diagonal /
    }

    private boolean checkDirection(int row, int col, int rowDelta, int colDelta, String symbol) {
        int count = 0;
        int r = row;
        int c = col;

        // Count in the positive direction
        while (r >= 0 && r < GRID_SIZE && c >= 0 && c < GRID_SIZE && buttons[r][c].getText().toString().equals(symbol)) {
            count++;
            if (count == 5) return true;
            r += rowDelta;
            c += colDelta;
        }

        // Reset and count in the negative direction
        r = row - rowDelta;
        c = col - colDelta;
        while (r >= 0 && r < GRID_SIZE && c >= 0 && c < GRID_SIZE && buttons[r][c].getText().toString().equals(symbol)) {
            count++;
            if (count == 5) return true;
            r -= rowDelta;
            c -= colDelta;
        }

        return false;
    }

    private void resetGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        isPlayerXTurn = true;
    }
}
