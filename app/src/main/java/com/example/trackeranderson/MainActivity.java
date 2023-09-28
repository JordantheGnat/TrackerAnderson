package com.example.trackeranderson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener spinListen = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
        }
    };
    View.OnClickListener saveListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String temp;
            Boolean canFinish = true;
            EditText dex=findViewById(R.id.dexNumberInput);
            EditText name=findViewById(R.id.nameInput);
            TextView nameTxt=findViewById(R.id.name_text);
            EditText species=findViewById(R.id.speciesInput);

            /*

            RadioGroup gender = findViewById(R.id.radioGroup);
            gender.getChildAt();
            int genderId = gender.getCheckedRadioButtonId();   just couldn't get this working in time
            int genderTester = gender.getChildAt(2).getId();
            if (genderId==genderTester){
                Toast.makeText(MainActivity.this, "Gender not selected", Toast.LENGTH_LONG).show();
                canFinish =false;

            }*/
            EditText weight=findViewById(R.id.weightInput);
            EditText height=findViewById(R.id.heightInput);

            EditText defense=findViewById(R.id.defInput);
            EditText atk=findViewById(R.id.atk_Input);
            EditText hp=findViewById(R.id.hpInput);

            temp = String.valueOf(dex.getText());
            int dexNum=Integer.valueOf(temp);//
            if(dexNum<=0||dexNum>=1010) {
                canFinish = false;
                Toast.makeText(MainActivity.this, "Dex Number" + dexNum + " failed to accept, was outside of parameters 0-1010", Toast.LENGTH_LONG).show();
                dex.setTextColor(Color.RED);
            } else{dex.setTextColor(Color.BLACK); canFinish=true;}


            Boolean tempBool =true;
            temp =String.valueOf(name.getText());//changes from dex input to name input
            if(temp.length()<3 && temp.length()>12){

                canFinish = false;
                tempBool=false;
                name.setTextColor(Color.RED);
            }else{
                for(int i=0;i<name.length();i++) {
                char ch = temp.charAt(i);
                //Toast.makeText(MainActivity.this," "+temp,Toast.LENGTH_LONG).show();
                if (Character.isLetter(ch) || ch == ' '||ch=='.') {
                }else{
                    canFinish=false;
                    name.setTextColor(Color.RED);//I have literally no idea why this doesn't work
                    //nameTxt.setTextColor(Color.RED);//So I made the other text change isntead
                    //This is seriously crazy, the EXACT SAME LINE OF CODE work for the dex above. The toast shows up, and even when I
                    //put the color change elsewhere, it doesn't do anything. I think something is difficult somewhere, and I can not figure it out
                    //if this worked correctly, the code would work, I tried it by changing the color of dex, so i have no clue


                    //I HAVE NO IDEA WHAT FIXED IT
                    tempBool=false;

                    Toast.makeText(MainActivity.this, "Name not taken, contains illegal characters.", Toast.LENGTH_LONG).show();
                }
                }}

            if(tempBool==true){
                name.setTextColor(Color.BLACK);
                //nameTxt.setTextColor(Color.BLACK);
                canFinish=true;
            }
            temp =String.valueOf(species.getText());//changes from name input to species input
            tempBool = true;
            if(temp.length()<3||temp.length()>12){
                canFinish = false;
                tempBool=false;
                species.setTextColor(Color.RED);
            }else{
            for(int i=0;i<name.length();i++) {
                char ch = temp.charAt(i);//for some reason this has random crashes
                if (Character.isLetter(ch) || ch == ' ') {
                    if(i==0){
                        if(Character.isLowerCase(ch)){
                            canFinish=false;tempBool = false;
                            species.setTextColor(Color.RED);
                            Toast.makeText(MainActivity.this, "Species name not taken, contains illegal characters, or faulty capitalization", Toast.LENGTH_LONG).show();}}
                }else{
                    canFinish=false;
                    tempBool=false;
                    species.setTextColor(Color.RED);}}
            }
            if(tempBool==true){
                species.setTextColor(Color.BLACK);}


            temp = String.valueOf(atk.getText());//you get the point, changes last one to this one
            atk.setTextColor(Color.BLACK);
            int atkNum = Integer.valueOf(temp);
            if(atkNum<=5||atkNum>=526){
                atk.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "ATK too high or low. 5<=ATK<=526", Toast.LENGTH_LONG).show();
                canFinish = false;
            }

            temp = String.valueOf(hp.getText());
            hp.setTextColor(Color.BLACK);
            int hpNum = Integer.valueOf(temp);
            if(hpNum<=1||hpNum>=362){
                hp.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "HP too high or low. 1<=HP<=362", Toast.LENGTH_LONG).show();
                canFinish = false;
            }

            temp = String.valueOf(defense.getText());
            defense.setTextColor(Color.BLACK);
            int defNum = Integer.valueOf(temp);
            if(defNum<=5||defNum>=614){
                defense.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "DEF too high or low. 5<=DEF<=614", Toast.LENGTH_LONG).show();
                canFinish = false;
            }

            temp = String.valueOf(weight.getText());
            weight.setTextColor(Color.BLACK);
            Double weightNum = Double.valueOf(temp);
            if(defNum<=.1||defNum>=820.0){
                weight.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "WEIGHT too high or low. .1<=WEIGHT<=820.0", Toast.LENGTH_LONG).show();
                canFinish = false;
            }
            temp = String.valueOf(height.getText());
            height.setTextColor(Color.BLACK);
            Double heightNum = Double.valueOf(temp);
            if(defNum<=.3||defNum>=19.99){
                height.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "HEIGHT too high or low. .3<=HEIGHT<=19.99", Toast.LENGTH_LONG).show();
                canFinish = false;
            }
            if(canFinish==true){
                Toast.makeText(MainActivity.this, "Congrats! Your catch was succesfully submitted", Toast.LENGTH_LONG).show();
                resetFunction();
            }
        }


    };


        View.OnClickListener resetListen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //I TRIED DOING THIS WITH A GROUP FOR 5 HOURS AGGGGHHHH
                //why is there no children in a group????
                //I know there is a way, but I was having difficulty figuring out viewgroups
                resetFunction(); //deletion is in that function, at the bottom

            }};

    RadioGroup radioGroup;
    RadioButton genderButton;
    TextView textView;
    Button resetButton,saveButton;
    Spinner spinner;
    Group txtBoxGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.table); super.onCreate(savedInstanceState);

        //Spinner Code
        String[] lvlArr= new String[50];
        for(int i = 0; i<50;i++){lvlArr[i]=(Integer.toString(i + 1));}//fills the lvls
        spinner = (Spinner)findViewById(R.id.lvlSpinner);//attaches the spinners
        ArrayList<String> spinnerArray =  new ArrayList<>(Arrays.asList(lvlArr));//takes the lvl array makes it arrList
        ArrayAdapter<String> lvlArrAdapt = new ArrayAdapter<String>//because ArrayAdapters need ArrLists. not arrs.
                (this, android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
                 lvlArrAdapt.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(lvlArrAdapt);
        spinner.findViewById(R.id.lvlSpinner);
        //Lil bit of everything.
        radioGroup = findViewById(R.id.radioGroup);//I know my variable naming is trash I gotta kick that habit
        resetButton = findViewById(R.id.resetButton);
        saveButton = findViewById(R.id.saveButton);
        resetButton.setOnClickListener(resetListen);
        saveButton.setOnClickListener(saveListen);
        spinner.setOnClickListener(spinListen);

        //Is there a way to condense all these?


    }
    public void checkButton(View v) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        genderButton = findViewById(radioID);
     //   System.out.println("Selected Radio Button: " + genderButton.getText());
    }
    public void resetFunction(){
        EditText atk=findViewById(R.id.atk_Input); atk.setText("");
        EditText dex=findViewById(R.id.dexNumberInput); dex.setText("");
        EditText name=findViewById(R.id.nameInput); name.setText("");
        EditText hp=findViewById(R.id.hpInput); hp.setText("");
        EditText species=findViewById(R.id.speciesInput); species.setText("");
        EditText weight=findViewById(R.id.weightInput); weight.setText("");
        EditText height=findViewById(R.id.heightInput); height.setText("");
        EditText defense=findViewById(R.id.defInput); defense.setText("");
    }
}