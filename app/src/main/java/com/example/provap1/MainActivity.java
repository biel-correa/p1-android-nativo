package com.example.provap1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        try {
            getSupportActionBar().hide();
        } catch (NullPointerException ignored) {}

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            final float scale = getResources().getDisplayMetrics().density;
            final int horizontalPadding = (int) (20 * scale + 0.5f);

            v.setPadding(horizontalPadding, systemBars.top, horizontalPadding, systemBars.bottom);
            return insets;
        });

        this.listenToSubmitClick();
    }

    private void listenToSubmitClick() {
        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(v -> {
            TextView errorText = findViewById(R.id.errorText);
            errorText.setText("");
            errorText.setVisibility(TextView.INVISIBLE);

            EditText finalAmountInput = findViewById(R.id.finalAmountInput);
            if (finalAmountInput.getText().toString().isEmpty()) {
                errorText.setText(R.string.o_campo_montante_final_obrigat_rio);
                errorText.setVisibility(TextView.VISIBLE);
                return;
            }

            EditText ratePerYearInput = findViewById(R.id.ratePerYearInput);
            if (ratePerYearInput.getText().toString().isEmpty()) {
                errorText.setText(R.string.o_campo_qual_a_taxa_de_juros_obrigat_rio);
                errorText.setVisibility(TextView.VISIBLE);
                return;
            }

            EditText numberOfYearsInput = findViewById(R.id.numberOfYearsInput);
            if (numberOfYearsInput.getText().toString().isEmpty()) {
                errorText.setText(R.string.o_campo_quantos_anos_at_a_sua_aposentadoria_obrigat_rio);
                errorText.setVisibility(TextView.VISIBLE);
                return;
            }

            double finalAmount = Double.parseDouble(finalAmountInput.getText().toString());
            double ratePerYear = Double.parseDouble(ratePerYearInput.getText().toString());
            double numberOfYears = Double.parseDouble(numberOfYearsInput.getText().toString());

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("finalAmount", finalAmount);
            intent.putExtra("ratePerYear", ratePerYear);
            intent.putExtra("numberOfYears", numberOfYears);

            startActivity(intent);
        });
    }
}