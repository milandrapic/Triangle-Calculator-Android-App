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


    private double getValue(int id){
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        if(input == null || input.equals("")) return 0;
        double in = Double.parseDouble(input);
        return in;
    }

    private void setValue(int id, String newValue){
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newValue);
    }

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
        double[] vals = {a,b,c,angA,angB,angC};
        while(i < 6){
            if(vals[i] <= 0)j++;i++;
        }
        if(j > 3) setValue(R.id.answer, "Error:\nnot enough values given");
        else if((a+b+c) <= 0) setValue(R.id.answer, "Error:\nmust enter at least one side");
        else {
            Triangle t = TriangleFinder.calculate(a, b, c, angA, angB, angC);
            setValue(R.id.answer, t.printVE());
        }
    }


}
