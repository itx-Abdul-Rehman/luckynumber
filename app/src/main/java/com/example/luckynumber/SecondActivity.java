package com.example.luckynumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView result;
    Button shareNumber;

    CheckBox gaming;
    CheckBox reading;
    RadioGroup genders;
    RadioButton male;
    RadioButton female;
    String name;
    int randomNumber;

    String gender;

    String hobbies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        result=findViewById(R.id.result);
        shareNumber=findViewById(R.id.shareNumber);
        gaming=findViewById(R.id.gaming);
        reading=findViewById(R.id.reading);
        genders=findViewById(R.id.gender);
        male=findViewById(R.id.male);
         female=findViewById(R.id.female);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");

         randomNumber=randomNumberGenerated();
        result.setText(randomNumber+"");




        genders.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton check=findViewById(checkedId);
                if(check==male){
                  gender="Male"  ;
                }else if(check==female){
                    gender="Female";
                }

            }
        });



        shareNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(male.isChecked()||female.isChecked()){
                    shareData(name,randomNumber);
                }else{
                    Toast.makeText(getApplicationContext(),"Please select gender", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private int randomNumberGenerated(){
        Random random=new Random();
        int maximum=1000;

        int generatedNumber=random.nextInt(maximum);
        return generatedNumber;
    }

    private void shareData(String name,int rdmNumber){

      Intent intent=new Intent(Intent.ACTION_SEND);
      intent.setType("plain/text");

        if(gaming.isChecked()&&reading.isChecked()){
            hobbies="Gaming,Reading";
        }else if(reading.isChecked()){
            hobbies="Reading";
        }else if(gaming.isChecked()){
            hobbies="Gaming";
        }else{
            hobbies="No Hobbies";
        }

      intent.putExtra(Intent.EXTRA_SUBJECT,name+" got the Lucky!");
      intent.putExtra(Intent.EXTRA_TEXT,"My Lucky Number is: "+rdmNumber+"\n Gender: "+gender+"\n Hobbies: "+hobbies);
      startActivity(Intent.createChooser(intent,"Choose a platform"));
     // finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelect=item.getItemId();

        if(itemSelect==R.id.share_bar){
            if(male.isChecked()||female.isChecked()){
                shareData(name,randomNumber);
            }else{
                Toast.makeText(getApplicationContext(),"Please select gender", Toast.LENGTH_SHORT).show();

            }
        }

        return super.onOptionsItemSelected(item);
    }


}