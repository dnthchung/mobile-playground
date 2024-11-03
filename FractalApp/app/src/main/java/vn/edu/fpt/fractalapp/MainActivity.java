package vn.edu.fpt.fractalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private FractalView fractalView;
    private SeekBar levelSeekBar;
    private TextView levelTextView;
    private Button colorButton;
    private int selectedColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fractalView = findViewById(R.id.fractalView);
        levelSeekBar = findViewById(R.id.levelSeekBar);
        levelTextView = findViewById(R.id.levelTextView);
        colorButton = findViewById(R.id.colorButton);

        levelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levelTextView.setText("Cấp độ: " + progress);
                fractalView.setLevel(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        colorButton.setOnClickListener(v -> {
            showColorPickerDialog();
        });
    }

    private void showColorPickerDialog() {
        final String[] colors = {"Đen", "Đỏ", "Xanh lá", "Xanh dương", "Vàng", "Tím"};
        final int[] colorValues = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn màu sắc");

        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedColor = colorValues[which];
                fractalView.setColor(selectedColor);
            }
        });

        builder.show();
    }
}
