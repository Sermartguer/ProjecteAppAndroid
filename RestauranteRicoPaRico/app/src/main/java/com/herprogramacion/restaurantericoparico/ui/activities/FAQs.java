package com.herprogramacion.restaurantericoparico.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.herprogramacion.restaurantericoparico.R;

public class FAQs extends AppCompatActivity {
    View section1, section2, section3,section4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        section1 = findViewById(R.id.section1);
        section2 = findViewById(R.id.section2);
        section3 = findViewById(R.id.section3);
        section4=findViewById(R.id.section4);
        View header1 = findViewById(R.id.header1);
        header1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section1.getVisibility() == View.GONE)
                {
                    section1.setVisibility(View.VISIBLE);
                }
                else
                {
                    section1.setVisibility(View.GONE);
                }
            }
        });

        View header2 = findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section2.getVisibility() == View.GONE)
                {
                    section2.setVisibility(View.VISIBLE);
                }
                else
                {
                    section2.setVisibility(View.GONE);
                }
            }
        });

        View header3 = findViewById(R.id.header3);
        header3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section3.getVisibility() == View.GONE)
                {
                    section3.setVisibility(View.VISIBLE);
                }
                else
                {
                    section3.setVisibility(View.GONE);
                }
            }
        });
        View header4 = findViewById(R.id.header4);
        header4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (section4.getVisibility() == View.GONE)
                {
                    section4.setVisibility(View.VISIBLE);
                }
                else
                {
                    section4.setVisibility(View.GONE);
                }
            }
        });
    }}
