package com.example.jo_1.reference_fp.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodDatabaseHelper extends SQLiteOpenHelper {
    public static final String ACTIVITY_NAME = "FoodDatabaseHelper";
    public static final String DATABASE_NAME = "Activity.db";
    public static final int VERSION_NUMBER = 1;

    public static final String TABLE_NAME = "ACTIVITY_TABLE";
    public static final String KEY_ACTIVITY = "Activity";
    public static final String KEY_ID = "id";
    public static final String KEY_COMMENT = "Comment";
    public static final String KEY_ACTIVITY_TIME = "Time";


    public static final String CREATE_TABLE_ACTIVITY =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_ACTIVITY + " TEXT, "
                    + KEY_COMMENT + " TEXT, "
                    + KEY_ACTIVITY_TIME + " TEXT"
                    + ");";

    public static final String[] ACTIVITY_FIELDS = new String[]{
            KEY_ID,
            KEY_ACTIVITY,
            KEY_COMMENT,
            KEY_ACTIVITY_TIME
    };


    //public Cursor getActivity(SQLiteDatabase sDB){
    //return sDB.query(TABLE_NAME,ACTIVITY_FIELDS,null,null,null,null,null);
    //}

    public Cursor getActivity(SQLiteDatabase sDB, String activity) {
        //Cursor findEntry = db.query("sku_table", columns, "owner=?", new String[] { owner }, null, null, null);
        return sDB.query(TABLE_NAME, ACTIVITY_FIELDS, KEY_ACTIVITY + "=?", new String[]{activity.toLowerCase()}, null, null, null);
    }


    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY);
        Log.i(ACTIVITY_NAME, "calling onCreate() from DatabaseHelper");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
        Log.i(ACTIVITY_NAME, "Calling onUpdate() old version: " + i + " new version: " + i1);

    }

}

//    public final static String name = "MyTable";
//    public final static String DATABASE_NAME = "Messages.db";
//    public final static int VERSION_NUM = 5;
//    public final static String KEY_ID = "id";
//    public final static String KEY_MESSAGE = "message";
//    public SQLiteDatabase mdb;
//    protected static final String KEY_ACTIVITY_TIME = "Time";
//
//    public static final String[] KEYS = new String[]{
//            KEY_ID, KEY_MESSAGE};
//
//
//
//    public FoodDatabaseHelper(Context ctx) {
//        super(ctx, DATABASE_NAME, null, VERSION_NUM);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + name + " ("
//                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
//                + KEY_MESSAGE + " TEXT" + ");");
//        Log.i("FoodDatabaseHelper", "Calling onCreate");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
//        db.execSQL("DROP TABLE IF EXISTS " + name);
//        onCreate(db);
//        Log.i("FoodDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + "newVersion=" + newVer);
//    }
//
//
//    // open database
//    public FoodDatabaseHelper open() {
//        if(mdb == null){
//            mdb = getWritableDatabase();
//        }
//        return this;
//    }
//
//    public void close(){
//        if(mdb != null){
//            mdb.close();
//        }
//    }
//
//    // retrieving data from database
//    public Cursor getChatMessages(){
//        return mdb.query(name, KEYS, null, null, null, null, null);
//    }
//
//    public String getMessageFromCursor(Cursor cursor){
//        String msg = cursor.getString(cursor.getColumnIndex(KEY_MESSAGE));
//        return msg;
//    }
//
//    public int getIdFromCursor(Cursor cursor){
//        int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
//        return id;
//    }
//
//    public void insert(ContentValues content){
//        mdb.insert(name, null, content);
//    }
//
//    public void remove(long id){
//        int deletedRecord =  mdb.delete(name, "_id" + "=" + id, null);
//        Log.i("Deleted ",Integer.toString(deletedRecord));
//    }
//
//}
