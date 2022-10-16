package com.keremmuhcu.calcultor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });

    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.display).toString().equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void clearButton(View view) {
        display.setText("");
    }

    public void equalsButton(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression expression = new Expression(userExp);

        String result = String.valueOf(expression.calculate());

        if (result.endsWith(".0")) {
            double resultDouble = Double.parseDouble(result);
            String resultWithNoDot = String.valueOf((int) resultDouble);
            display.setText(resultWithNoDot);
            display.setSelection(resultWithNoDot.length());
        } else {
            display.setText(result);
            display.setSelection(result.length());
        }


    }

    public void divideButton(View view) {
        updateText("÷");
    }

    public void multiplyButton(View view) {
        updateText("×");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void subtractButton(View view) {
        updateText("-");
    }

    public void backspaceButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLength = display.getText().length();

        if (cursorPos != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void parenthesesButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openParentheses = 0;
        int closeParentheses = 0;
        int textLength = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, (i + 1)).equals("(")) {
                openParentheses += 1;
            }
            if (display.getText().toString().substring(i, (i + 1)).equals(")")) {
                closeParentheses += 1;
            }
        }

        if (openParentheses == closeParentheses || display.getText().toString().charAt(textLength - 1) == '(') {
            updateText("(");
        } else if (openParentheses > closeParentheses && display.getText().toString().charAt(textLength - 1) != '(') {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);

    }

    public void exponentButton(View view) {
        updateText("^");
    }

    public void pointButton(View view) {
        updateText(".");
    }

    public void plusMinusButton(View view) {
        updateText("-");
    }

}