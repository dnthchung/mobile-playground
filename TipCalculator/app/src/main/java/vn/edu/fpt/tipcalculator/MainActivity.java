package vn.edu.fpt.tipcalculator;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

   private EditText amountEditText;
   private TextView percentTextView;
   private SeekBar percentSeekBar;
   private TextView tipTextView;
   private TextView totalTextView;

   private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
   private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

   private double billAmount = 0.0;  // Bill amount entered by the user
   private double tipPercent = 0.15; // Tip percentage set by the user

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      // Initialize views
      amountEditText = findViewById(R.id.amountEditText);
      percentTextView = findViewById(R.id.percentTextView);
      percentSeekBar = findViewById(R.id.percentSeekBar);
      tipTextView = findViewById(R.id.tipTextView);
      totalTextView = findViewById(R.id.totalTextView);

      // Set listeners
      amountEditText.addTextChangedListener(new SimpleTextWatcher() {
         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
               billAmount = Double.parseDouble(s.toString()) / 100.0;
            } catch (NumberFormatException e) {
               billAmount = 0.0; // If invalid input, reset the bill amount
            }
            calculateTipAndTotal();
         }
      });

      percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tipPercent = progress / 100.0; // Convert progress to percentage
            percentTextView.setText(percentFormat.format(tipPercent));
            calculateTipAndTotal();
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {}

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {}
      });

      // Initialize default tip percentage
      percentSeekBar.setProgress(15); // 15% default tip
   }

   private void calculateTipAndTotal() {
      // Calculate the tip and total amount
      double tip = billAmount * tipPercent;
      double total = billAmount + tip;

      // Display the results
      tipTextView.setText(currencyFormat.format(tip));
      totalTextView.setText(currencyFormat.format(total));
   }
}
