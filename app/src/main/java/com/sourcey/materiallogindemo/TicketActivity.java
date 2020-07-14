package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class TicketActivity extends AppCompatActivity {
    View parrentView;
    View parrentView2;
    CardView cardLondon;
    CardView cardNewYork;
    CardView cardTokyo;
    int cardLondonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        parrentView2 = findViewById(R.id.layout1);

//        TextView tvLondon = (TextView) findViewById(R.id.tvLondon);
//        TextView tvNewYork = (TextView) findViewById(R.id.tvNewYork);
//        TextView tvTokyo = (TextView) findViewById(R.id.tvTokyo);
        TextView tvLondon = (TextView) parrentView2.findViewWithTag("tvLondon");
        TextView tvNewYork = (TextView) parrentView2.findViewWithTag("tvNewYork");
        TextView tvTokyo = (TextView) parrentView2.findViewWithTag("tvTokyo");

        parrentView = findViewById(R.id.layout1);
        cardLondon = (CardView) parrentView.findViewWithTag("1");
        cardNewYork = (CardView) parrentView.findViewWithTag("2");
        cardTokyo = (CardView) parrentView.findViewWithTag("3");

        cardLondonId = cardLondon.getId();

        tvLondon.setText("03:30AM ---> 1:50PM\n13,232.80THB\n\n01:05PM ---> 7:20PM\n11,632.80THB");
        tvNewYork.setText("03:30AM ---> 9:20PM\n22,320.00THB");
        tvTokyo.setText("10:40AM ---> 7:00PM\n3,232.80THB\n\n11:50AM ---> 7:30PM\n3,062.20THB\n\n01:55AM ---> 10:15PM\n4,832.00THB");
    }

    public void changeResult(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.chkLondon:
                if (!checked)
                    cardLondon.setVisibility(View.GONE);
                else
                    cardLondon.setVisibility(View.VISIBLE);
                break;
            case R.id.chkNewYork:
                if (!checked)
                    cardNewYork.setVisibility(View.GONE);
                else
                    cardNewYork.setVisibility(View.VISIBLE);
                break;
            case R.id.chkTokyo:
                if (!checked)
                    cardTokyo.setVisibility(View.GONE);
                else
                    cardTokyo.setVisibility(View.VISIBLE);
                break;
        }
    }
}

