package com.example.trackeranderson;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokeVider extends ContentProvider {

    public final static String DBNAME = "PokeTracker";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };


    public final static String TABLE_POKETRACKER = "PokeTracker";
    public final static String COLUMN_DEX = "Dex";
    public final static String COLUMN_NAME= "Name";
    public final static String COLUMN_SPECIES = "Species";
    public final static String COLUMN_GENDER = "Gender";
    public final static String COLUMN_HEIGHT = "Height";
    public final static String COLUMN_WEIGHT = "Weight";
    public final static String COLUMN_LEVEL = "Level";
    public final static String COLUMN_HP = "HP";
    public final static String COLUMN_ATK = "Attack";
    public final static String COLUMN_DEF = "Defense";



    public static final String AUTHORITY = "com.example.pokeprovider";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_POKETRACKER);

    private static UriMatcher sUriMatcher;

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_POKETRACKER +  // Table's name
            "(" +               // The columns in the table
            " _id INTEGER PRIMARY KEY, " +
            COLUMN_DEX +
            " TEXT," +
            COLUMN_NAME +
            " TEXT," +
            COLUMN_SPECIES +
            " TEXT," +
            COLUMN_GENDER+
            " TEXT," +
            COLUMN_HEIGHT +
            " TEXT," +
            COLUMN_WEIGHT +
            " TEXT," +
            COLUMN_LEVEL +
            " TEXT," +
            COLUMN_HP +
            " TEXT," +
            COLUMN_ATK +
            " TEXT,"+
            COLUMN_DEF+
            " TEXT)";



    public PokeVider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_POKETRACKER, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String dexNum = values.getAsString(COLUMN_DEX).trim();
        String pokeName = values.getAsString(COLUMN_NAME).trim();
        String pokeSpecies = values.getAsString(COLUMN_SPECIES).trim();
        String pokeGender = values.getAsString(COLUMN_GENDER).trim();
        String pokeHeight = values.getAsString(COLUMN_HEIGHT).trim();
        Boolean pokeWeight = values.getAsBoolean(COLUMN_WEIGHT);//this does register as false, but for some reason in values it registers as Pop On, which is it's string resource
        String pokeLevel = values.getAsString(COLUMN_LEVEL).trim();
        String pokeHP = values.getAsString(COLUMN_HP).trim();
        String pokeATK = values.getAsString(COLUMN_ATK).trim();
        String pokeDEF = values.getAsString(COLUMN_DEF).trim();

        if (dexNum.equals(""))
            return null;

        if (pokeName.equals(""))
            return null;
        if (pokeSpecies.equals(""))
            return null;
        if (pokeGender.equals("")||pokeHeight.equals("")||pokeWeight.equals(""))
            return null;
        if(pokeLevel.equals("")||pokeHP.equals("")||pokeATK.equals("")||pokeDEF.equals(""))
            return null;

        long id = mOpenHelper.getWritableDatabase().insert(TABLE_POKETRACKER, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_POKETRACKER, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_POKETRACKER, values, selection, selectionArgs);
    }
}
