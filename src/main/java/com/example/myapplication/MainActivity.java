package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        setContentView(R.layout.activity_main);
        findViewById(R.id.ttt).setOnClickListener(this);
        findViewById(R.id.dice).setOnClickListener(this);
        findViewById(R.id.list).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.ttt):
                startActivity(new Intent(mContext,TicTacToe.class));
                break;
            case(R.id.dice):
                startActivity(new Intent(mContext,Dice.class));
                break;
            case(R.id.list):
                startActivity(new Intent(mContext, List.class));
                break;
        }
    }
}
