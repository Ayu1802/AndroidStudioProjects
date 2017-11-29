package com.example.ayushib.mycart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnClick(View a) {

        if (a.getId() == R.id.btnCalculate) {
            EditText p = (EditText) findViewById(R.id.txtprice);
            EditText q = (EditText) findViewById(R.id.txtQuantity);

            double p1, q1, cost;
            p1 = Double.parseDouble(p.getText().toString());
            q1 = Double.parseDouble(q.getText().toString());

            cost = p1 * q1;

            TextView total = (TextView) findViewById(R.id.txtTotal);
            total.setText(" " + cost);
        }
    }
}