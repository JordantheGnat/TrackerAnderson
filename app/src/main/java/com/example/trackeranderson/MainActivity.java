package com.example.trackeranderson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
//import android.bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
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
    Button insertButton;
    Button queryButton;
    Button updateButton;
    Button deleteButton;
    Button nextButton;
    Button previousButton;
    EditText firstname;
    EditText lastname;
    EditText defense;
    EditText height;
    EditText weight;
    EditText species;
    EditText hp;
    EditText name;
    EditText dex;
    EditText atk;
    TextView dexText,nameText,speciesText,genderText,heightText,weightText;
    TextView lvlText,hpText,atkText,defText;

    TextView idTv;
    TextView fnameTv;
    TextView lnameTv;
    Cursor mCursor;
    View.OnClickListener spinListen = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
        }
    };
    View.OnClickListener goToLV = new View.OnClickListener(){
        @Override
        public void onClick(View view){

        }
    };
    Uri provideUri = PokeVider.CONTENT_URI;
    String[]dbColumns={
            PokeVider.COLUMN_DEX,
            PokeVider.COLUMN_NAME,
            PokeVider.COLUMN_SPECIES,
            PokeVider.COLUMN_GENDER,
            PokeVider.COLUMN_HEIGHT,
            PokeVider.COLUMN_WEIGHT,
            PokeVider.COLUMN_LEVEL,
            PokeVider.COLUMN_HP,
            PokeVider.COLUMN_ATK,
            PokeVider.COLUMN_DEF
    };
    View.OnClickListener saveListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String temp;
            Boolean canFinish = true;
            EditText dex = findViewById(R.id.dexNumberInput);
            EditText name = findViewById(R.id.nameInput);
            TextView nameTxt = findViewById(R.id.name_text);
            EditText species = findViewById(R.id.speciesInput);

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
                dexText = findViewById(R.id.dexText);
                dexText.setText( dexNum + " failed to accept, was outside of parameters 0-1010");//this works but for some reason lets it complete
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
                    nameText=findViewById(R.id.name_text);
                    nameTxt.setText("Name not taken, contains illegal characters.");
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
            for(int i=0;i<name.length()-1;i++) {
                char ch = temp.charAt(i);//for some reason this has random crashes
                if (Character.isLetter(ch) || ch == ' ') {
                    if(i==0){
                        if(Character.isLowerCase(ch)){
                            canFinish=false;tempBool = false;
                            species.setTextColor(Color.RED);
                            speciesText = findViewById(R.id.speciesText);//this one doesn't work. huh.
                            speciesText.setText("Species name not taken, contains illegal characters, or faulty capitalization");}}
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
                atkText = findViewById(R.id.attackTV);
                atkText.setText("ATK too high or low. 5<=ATK<=526");
                canFinish = false;
            }
            String tempGender = checkButton(null);
            temp = String.valueOf(hp.getText());
            hp.setTextColor(Color.BLACK);
            int hpNum = Integer.valueOf(temp);
            if(hpNum<=1||hpNum>=362){
                hp.setTextColor(Color.RED);
                hpText = findViewById(R.id.hpText);
                hpText.setText("HP too high or low. 1<=HP<=362");
                canFinish = false;
            }

            temp = String.valueOf(defense.getText());
            defense.setTextColor(Color.BLACK);
            int defNum = Integer.valueOf(temp);
            if(defNum<=5||defNum>=614){
                defense.setTextColor(Color.RED);
                defText =findViewById(R.id.defTxt);
                defText.setText("DEF too high or low. 5<=DEF<=614");
                canFinish = false;
            }

            temp = String.valueOf(weight.getText());
            weight.setTextColor(Color.BLACK);
            Double weightNum = Double.valueOf(temp);
            if(defNum<=.1||defNum>=820.0){
                weight.setTextColor(Color.RED);
                weightText = findViewById(R.id.weightText);
                weightText.setText("WEIGHT too high or low. .1<=WEIGHT<=820.0");
                canFinish = false;
            }
            temp = String.valueOf(height.getText());
            height.setTextColor(Color.BLACK);
            Double heightNum = Double.valueOf(temp);
            if(heightNum<=.3||heightNum>=19.99){
                height.setTextColor(Color.RED);
                heightText = findViewById(R.id.heightText);
                heightText.setText("HEIGHT too high or low. .1<=WEIGHT<=19.99"); //just realized none of these switch back.oops.
                canFinish = false;
            }
            if(canFinish==true){
                insertData();
                Toast.makeText(MainActivity.this, "Congrats! Your catch was succesfully submitted", Toast.LENGTH_LONG).show();
                resetFunction();
            }
        }


    };
    public void insertData() {
        ContentValues mNewValues = new ContentValues();
        mNewValues.put(PokeVider.COLUMN_DEX, dex.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_NAME, name.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_SPECIES, species.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_GENDER, checkButton(null).trim());
        mNewValues.put(PokeVider.COLUMN_HEIGHT, height.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_WEIGHT, weight.getText().toString().trim());
        Log.i("Level Selected", level.getSelectedItem().toString());
        mNewValues.put(PokeVider.COLUMN_LEVEL, level.getSelectedItem().toString().trim());// hm
        mNewValues.put(PokeVider.COLUMN_HP, hp.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_ATK, atk.getText().toString().trim());
        mNewValues.put(PokeVider.COLUMN_DEF, defense.getText().toString().trim());
        if (!isDuplicate(mNewValues)) {
            getContentResolver().insert(PokeVider.CONTENT_URI, mNewValues);
        }
        if(getContentResolver().insert(PokeVider.CONTENT_URI,mNewValues)!=null){

        }
        mCursor = getContentResolver().query(provideUri, dbColumns, null, null, null);

    }
    public boolean isDuplicate(ContentValues dupeVals){
        String[] projection = {BaseColumns._ID};
        String selection = PokeVider.COLUMN_DEX + " = ? AND " +
                PokeVider.COLUMN_NAME + " = ? AND " +
                PokeVider.COLUMN_SPECIES + " = ?";
        String[] selectionArgs = {
                dupeVals.getAsString(PokeVider.COLUMN_DEX),
                dupeVals.getAsString(PokeVider.COLUMN_NAME),
                dupeVals.getAsString(PokeVider.COLUMN_SPECIES)
        };
        return false;
    }


        View.OnClickListener resetListen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFunction(); //deletion is in that function, at the bottom

            }};

    RadioGroup radioGroup;
    RadioButton genderButton;
    TextView textView;
    Button resetButton,saveButton;
    Spinner level;
    Group txtBoxGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main); super.onCreate(savedInstanceState);

        //Spinner Code
        String[] lvlArr= new String[50];
        for(int i = 0; i<50;i++){lvlArr[i]=(Integer.toString(i + 1));}//fills the lvls
        level = (Spinner)findViewById(R.id.lvlSpinner);//attaches the spinners
        ArrayList<String> spinnerArray =  new ArrayList<>(Arrays.asList(lvlArr));//takes the lvl array makes it arrList
        ArrayAdapter<String> lvlArrAdapt = new ArrayAdapter<String>//because ArrayAdapters need ArrLists. not arrs.
                (this, android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
                 lvlArrAdapt.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        level.setAdapter(lvlArrAdapt);
        level.findViewById(R.id.lvlSpinner);
        //Lil bit of everything.
        radioGroup = findViewById(R.id.radioGroup);//I know my variable naming is trash I gotta kick that habit
        resetButton = findViewById(R.id.resetButton);
        saveButton = findViewById(R.id.saveButton);
        resetButton.setOnClickListener(resetListen);
        saveButton.setOnClickListener(saveListen);

                defense=findViewById(R.id.defInput);
        height=findViewById(R.id.heightInput);
        weight=findViewById(R.id.weightInput);
        species=findViewById(R.id.speciesInput);
        hp=findViewById(R.id.hpInput);
        name=findViewById(R.id.nameInput);
        dex=findViewById(R.id.dexNumberInput);
        atk=findViewById(R.id.atk_Input);
        Button goToListButton = findViewById(R.id.goToListButton);
        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ListActivity
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        //level.setOnClickListener(spinListen);

        //Is there a way to condense all these?


    }
    public String checkButton(View v) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        genderButton = findViewById(radioID);
        Log.i("Selected Radio Button: ", (String) genderButton.getText());//holy crap it works
        return (String) genderButton.getText();
    }
    public void resetFunction(){
        atk.setText("");
        dex.setText("");
        name.setText("");
        hp.setText("");
        species.setText("");
        weight.setText("");
        height.setText("");
        defense.setText("");
    }
}