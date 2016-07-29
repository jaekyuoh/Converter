package edu.gatech.seclass.converter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterActivity extends AppCompatActivity {
    RadioGroup fromGroup;
    RadioGroup toGroup;
    RelativeLayout layout;
    String fromUnit;
    String toUnit;
    String inputString;

    EditText inputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        layout = (RelativeLayout) findViewById(R.id.relativeLayout);
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });
        fromGroup = (RadioGroup) findViewById(R.id.fromGroup);
        toGroup = (RadioGroup) findViewById(R.id.toGroup);
        inputEditText = (EditText)findViewById(R.id.inputEditText);

        Button convertButton = (Button)findViewById(R.id.convert_button);
        final TextView resultString = (TextView)findViewById(R.id.resultTextView);
        convertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int fromId = fromGroup.getCheckedRadioButtonId();
                int toId = toGroup.getCheckedRadioButtonId();
                //getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
                RadioButton fromRb = (RadioButton) findViewById(fromId);
                RadioButton toRb = (RadioButton) findViewById(toId);
                //EditText inputEditText = (EditText)findViewById(R.id.inputEditText);
                inputString = inputEditText.getText().toString();
                if (!inputString.equals("")) {
                    double input = Double.parseDouble(inputString);
                    fromUnit = fromRb.getText().toString();
                    toUnit = toRb.getText().toString();

                    Conversion conversion = new Conversion(input, fromUnit, toUnit);
                    //double output = conversion.compute();
                    String output = conversion.compute();
                    resultString.setText("" + output);
                }
                else {
                    Toast.makeText(ConverterActivity.this, "Need input value !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button resetButton = (Button)findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton fromMilesRb = (RadioButton) findViewById(R.id.fromMiles);
                RadioButton toMilesRb = (RadioButton) findViewById(R.id.toMiles);
                fromMilesRb.setChecked(true);
                toMilesRb.setChecked(true);

                EditText inputEditText =  (EditText)findViewById(R.id.inputEditText);
                TextView outputEditText =  (TextView)findViewById(R.id.resultTextView);

                inputEditText.setText("");
                outputEditText.setText("");

            }
        });
    }

    /**
     * Hides virtual keyboard
     *
     */
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
