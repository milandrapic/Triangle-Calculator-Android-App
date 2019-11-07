package com.drapic.milan.trianglecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //gets value from input field
    private double getValue(int id){
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        if(input == null || input.equals("")) return 0;
        double in = Double.parseDouble(input);
        return in;
    }
    //changes value of text field in application
    private void setValue(int id, String newValue){
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newValue);
    }
    //function that occurs when compute button is clicked on app
    public void compute(View view){
        double a = getValue(R.id.edgeA);
        double b = getValue(R.id.edgeB);
        double c = getValue(R.id.edgeC);
        double angA = getValue(R.id.angleA);
        double angB = getValue(R.id.angleB);
        double angC = getValue(R.id.angleC);

        int i,j;
        i = 0;
        j = 0;
        //array of all values
        double[] vals = {a,b,c,angA,angB,angC};
        //counts how many nonzero values were entered
        while(i < 6){
            if(vals[i] <= 0)j++;i++;
        }
        //if more than 3 nonzero values are entered then output error message
        if(j > 3) setValue(R.id.answer, "Error:\nnot enough values given");
        //if a side value isnt given then output error message
        else if((a+b+c) <= 0) setValue(R.id.answer, "Error:\nmust enter at least one side");
        //if there are no errors then continue with normal output
        else {
            Triangle t = TriangleFinder.calculate(a, b, c, angA, angB, angC);
            setValue(R.id.answer, t.printVE());
        }
    }


}
