// ListViewActivity.java
package com.example.trackeranderson;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.listView);
        String[] projection = {BaseColumns._ID,PokeVider.COLUMN_DEX, PokeVider.COLUMN_NAME, PokeVider.COLUMN_SPECIES};//sets columns to query
        cursor = getContentResolver().query(PokeVider.CONTENT_URI, projection, null, null, null);
        String[] fromColumns = {PokeVider.COLUMN_DEX, PokeVider.COLUMN_NAME, PokeVider.COLUMN_SPECIES};
        int[] toViews = {R.id.subItemTextView, R.id.mainItemTextView, R.id.subItemTextView2}; // These should be the IDs of TextViews in your list item layout

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, fromColumns, toViews, 0);
        listView.setAdapter(adapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))!=-1){
                    int mostRecentID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
                    Log.i("Most recent ID", String.valueOf(mostRecentID));
                    getContentResolver().delete(PokeVider.CONTENT_URI, BaseColumns._ID + "=?", new String[]{String.valueOf(mostRecentID)});

                }
            }
        });
    }
}
