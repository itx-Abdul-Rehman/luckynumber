package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button wishButton;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        wishButton=findViewById(R.id.wishButton);
        spinner=findViewById(R.id.spinner);

        String[] values=getResources().getStringArray(R.array.my_array);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,values);
        spinner.setAdapter(adapter);

        wishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                if(userName.isEmpty()){
                    name.setError("Please enter name!");
                }else{
                    Intent intent=new Intent(getApplicationContext(),SecondActivity.class);

                    intent.putExtra("name",userName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}