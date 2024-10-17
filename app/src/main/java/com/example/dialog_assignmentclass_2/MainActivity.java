package com.example.dialog_assignmentclass_2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button openDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views and listeners
        initViews();
        initListeners();
    }

    // Method to initialize views
    private void initViews() {
        openDialogButton = findViewById(R.id.openDialogButton);
    }

    // Method to initialize listeners
    private void initListeners() {
        openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }

    // Method to show custom dialog
    private void showCustomDialog() {
        // Create the custom dialog
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_custom);

        // Initialize dialog views
        SeekBar priceSeekBar = dialog.findViewById(R.id.PriceSeekBar);
        TextView priceRangeText = dialog.findViewById(R.id.PriceRangeText);
        CheckBox checkBox1BHK = dialog.findViewById(R.id.Checkbox_1bhk);
        CheckBox checkBox2BHK = dialog.findViewById(R.id.Checkbox_2bhk);
        CheckBox checkBox3BHK = dialog.findViewById(R.id.Checkbox_3bhk);
        Button buttonOk = dialog.findViewById(R.id.Button_ok);

        // Default SeekBar value
        priceSeekBar.setMax(0);
        priceSeekBar.setProgress(0);
        priceRangeText.setText("0");

        // Implement logic to ensure only one checkbox is checked at a time
        initCheckboxLogic(checkBox1BHK, checkBox2BHK, checkBox3BHK);

        // Initialize dialog listeners
        initDialogListeners(dialog, priceSeekBar, priceRangeText, checkBox1BHK, checkBox2BHK, checkBox3BHK, buttonOk);

        // Show the dialog
        dialog.show();
    }

    // Method to initialize checkbox logic
    private void initCheckboxLogic(CheckBox checkBox1BHK, CheckBox checkBox2BHK, CheckBox checkBox3BHK) {
        checkBox1BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox2BHK.setChecked(false);
                checkBox3BHK.setChecked(false);
            }
        });

        checkBox2BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1BHK.setChecked(false);
                checkBox3BHK.setChecked(false);
            }
        });

        checkBox3BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1BHK.setChecked(false);
                checkBox2BHK.setChecked(false);
            }
        });
    }

    // Method to initialize listeners for the dialog
    private void initDialogListeners(Dialog dialog, SeekBar priceSeekBar, TextView priceRangeText, CheckBox checkBox1BHK, CheckBox checkBox2BHK, CheckBox checkBox3BHK, Button buttonOk) {
        // OK button click listener
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for updating the SeekBar value and price range based on checkbox selection
                if (checkBox1BHK.isChecked()) {
                    // Set SeekBar value to 25 for 1 BHK
                    priceSeekBar.setProgress(25);
                    priceRangeText.setText("Price Range: 15 Lakh");
                } else if (checkBox2BHK.isChecked()) {
                    // Set SeekBar value to 50 for 2 BHK
                    priceSeekBar.setProgress(50);
                    priceRangeText.setText("Price Range: 30 Lakh");
                } else if (checkBox3BHK.isChecked()) {
                    // Set SeekBar value to 100 for 3 BHK
                    priceSeekBar.setProgress(100);
                    priceRangeText.setText("Price Range:  50 Lakh");
                }
            }
        });
    }
}

