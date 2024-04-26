package com.example.mad2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText inputEditText;
    EditText keyEditText; // Change to EditText for key input
    TextView textView;
    Button encryptButton, decryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();

        // Show the toast message for the first 3 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("Developed by: 221B252 (Nirmal Singh)");
                initializeApp();
            }
        }, 1000);
    }

    private void initializeApp() {
        inputEditText = findViewById(R.id.textInputEditText);
        keyEditText = findViewById(R.id.editTextNumber2); // Find the EditText for key input
        textView = findViewById(R.id.textView);
        encryptButton = findViewById(R.id.button);
        decryptButton = findViewById(R.id.button2);
        Button resetButton = findViewById(R.id.button3);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputEditText.getText().toString();
                String key = keyEditText.getText().toString(); // Get the key from EditText
                String encryptedText = encrypt(inputText, key); // Pass the key to the encrypt function
                textView.setText(encryptedText);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encryptedText = inputEditText.getText().toString();
                String key = keyEditText.getText().toString(); // Get the key from EditText
                String decryptedText = decrypt(encryptedText, key); // Pass the key to the decrypt function
                textView.setText(decryptedText);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEditText.setText("");
                textView.setText("");
            }
        });
    }

    private String encrypt(String input, String key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int k = key.charAt(0)-'0'; // Repeat the key if it's shorter than the input
            // Shift ASCII value forward by the value of the corresponding key character
            char encryptedChar = (char) (c + k);
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }

    private String decrypt(String input, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int k = key.charAt(0)-'0'; // Repeat the key if it's shorter than the input
            // Shift ASCII value backward by the value of the corresponding key character
            char decryptedChar = (char) (c - k);
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
