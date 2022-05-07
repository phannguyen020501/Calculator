package com.example.calculator;

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
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    public void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }
    public void zeroBTN(View view){
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void mulBTN(View view){
        updateText("x");
    }
    public void divideBTN(View view){
        updateText("/");
    }

    public void substractBTN(View view){
        updateText("-");
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void clearBTN(View view){
        display.setText("");
    }
    public void equalBTN(View view) {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("x", "*");

        Expression expression = new Expression(userExp);

        String rs = String.valueOf(expression.calculate());
        display.setText(rs);
        display.setSelection(rs.length());

    }
    public void bsBTN(View view){
        int cursorBTN = display.getSelectionStart();
        int textlen = display.getText().length();

        if(cursorBTN != 0 && textlen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorBTN-1, cursorBTN, "");
            display.setText(selection);
            display.setSelection(cursorBTN-1);
        }
    }

    public void parBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openBar = 0;
        int closeBar = 0;
        int textLen = display.getText().length();

        for(int i =0; i < cursorPos; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openBar+=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closeBar+=1;
            }
        }
        if(openBar == closeBar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos+1);
        }else if(
                closeBar <openBar &&  !display.getText().toString().substring(textLen-1, textLen).equals("(")
        ){
            updateText(")");
            display.setSelection(cursorPos+1);
        }
    }
}