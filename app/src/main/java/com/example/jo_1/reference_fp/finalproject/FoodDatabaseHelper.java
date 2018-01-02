package com.example.jo_1.reference_fp.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodDatabaseHelper extends SQLiteOpenHelper {

        public static final String ACTIVITY_NAME = "FoodDatabaseHelper";
        public static final String DATABASE_NAME = "Activity101.db";
        public static final int VERSION_NUMBER = 1;

        public static final String TABLE_NAME = "ACTIVITY_TABLE";
        public static final String KEY_ACTIVITY = "Activity";
        public static final String KEY_ID = "id";
        public static final String KEY_COMMENT = "Comment";
        public static final String KEY_ACTIVITY_TIME = "Time";
        private final Context mctx;
        public SQLiteDatabase mdb;

        public static final String CREATE_TABLE_ACTIVITY =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + KEY_ACTIVITY + " TEXT, "
                        + KEY_COMMENT + " TEXT, "
                        + KEY_ACTIVITY_TIME + " TEXT"
                        +");";

        public static final String[] ACTIVITY_FIELDS = new String[]{
                KEY_ID,
                KEY_ACTIVITY,
                KEY_COMMENT,
                KEY_ACTIVITY_TIME
        };


        public FoodDatabaseHelper (Context context){
            super(context, DATABASE_NAME, null, VERSION_NUMBER);
            this.mctx = context;
        }



        // only creating the table
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_ACTIVITY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.w(ACTIVITY_NAME, "Upgrading database from version "+ oldVersion + " to " + newVersion + ", which " +
                    "will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }


        // open database
        public FoodDatabaseHelper open() {
            if(mdb == null){
                mdb = getWritableDatabase();
            }

            return this;
        }

        public void close(){
            if(mdb != null){
                mdb.close();
            }
        }

        // retrieving data
        public Cursor getChatMessages(){
            return mdb.query(TABLE_NAME, ACTIVITY_FIELDS, null, null, null, null, null);
        }

        public Cursor getActivity(String activityName){
            return mdb.query(TABLE_NAME,ACTIVITY_FIELDS,KEY_ACTIVITY+"=?",new String[] { activityName.toLowerCase() },null,null,null);
        }

        public String getMessageFromCursor(Cursor cursor){
            String msg = cursor.getString(cursor.getColumnIndex(KEY_COMMENT));
            return msg;

        }

        public int getIdFromCursor(Cursor cursor){
            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            return id;

        }

        public void insert(ContentValues content){
            mdb.insert(TABLE_NAME, null, content);

        }

        public void remove(long id){
            //String s_id;
            //s_id = String.valueOf(id);
            // mdb.execSQL("DELETE FROM "  + CHAT_TABLE  + " WHERE _id = " + id);
            //mdb.delete(CHAT_TABLE, KEY_ID, +" = ?", new String[] { s_id });


            //mdb.execSQL("DELETE FROM " + CHAT_TABLE + " WHERE " + KEY_ID + "= " + id);

            int deletedRecord =  mdb.delete(TABLE_NAME, "id" + "=" + id, null);
            Log.i("Deleted ",Integer.toString(deletedRecord));
        }
    }

