package com.example.provap1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        try {
            getSupportActionBar().hide();
        } catch (NullPointerException ignored) {}

        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            final float scale = getResources().getDisplayMetrics().density;
            final int horizontalPadding = (int) (20 * scale + 0.5f);

            v.setPadding(horizontalPadding, systemBars.top, horizontalPadding, systemBars.bottom);
            return insets;
        });

        this.listenToBackButton();

        final double finalAmount = getIntent().getDoubleExtra("finalAmount", 0);
        final double ratePerYear = getIntent().getDoubleExtra("ratePerYear", 0);
        final double numberOfYears = getIntent().getDoubleExtra("numberOfYears", 0);

        final double ratePerMonth = ratePerYear / 12;
        final double numberOfMonths = numberOfYears * 12;

        final double result = (finalAmount * ratePerMonth) / (Math.pow(1 + ratePerMonth, numberOfMonths) - 1);

        final TextView resultText = findViewById(R.id.resultText);
        final String resultString = getResources().getString(R.string.result);
        resultText.setText(String.format(resultString, finalAmount, result));
    }

    private void listenToBackButton() {
        final Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}