package com.example.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class List extends AppCompatActivity {
    private ListView mShoppingList;
    private EditText mItemEdit;
    private Button mAddButton;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mShoppingList = findViewById(R.id.shopping_listView);
        mItemEdit = findViewById(R.id.item_editText);
        mAddButton = findViewById(R.id.add_button);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mShoppingList.setAdapter(mAdapter);
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                mAdapter.notifyDataSetChanged();
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                mItemEdit.setText("");

            }
        });
        mShoppingList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.remove(mAdapter.getItem(position));
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        writeToFile();
        super.onPause();
    }

    private void writeToFile() {
        String data="";
        for(int i=0;i<mAdapter.getCount();i++)
            data+=mAdapter.getItem(i)+"\n";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("list.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        readFromFile();
        super.onResume();
    }

    private void readFromFile() {
        try {
            InputStream inputStream = openFileInput("list.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    mAdapter.add(receiveString);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
