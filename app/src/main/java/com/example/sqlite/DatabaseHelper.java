package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_CUSTOMER_ACTIVITY = "CUSTOMAER_ACTIVITY";
    public static final String COLUMN_ID = "ID";

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT ," + COLUMN_CUSTOMER_AGE + " INT ," + COLUMN_CUSTOMER_ACTIVITY + " BOOL )";
    db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DatabaseHelper(@Nullable Context context ) {
        super(context, "Customer.db", null, 1);
    }

    public boolean addOne(Customer_Model customer_model){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME,customer_model.getName());
        cv.put(COLUMN_CUSTOMER_AGE,customer_model.getAge());
        cv.put(COLUMN_CUSTOMER_ACTIVITY,customer_model.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        return insert != -1;
    }
    public void deleteOne(Customer_Model customer_model){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ CUSTOMER_TABLE + " WHERE "+ COLUMN_ID + " = " + customer_model.getId();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
    }

    public List<Customer_Model> getEverone(){
        List <Customer_Model> returnlist = new ArrayList<>();
        String queryList = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryList,null);
        if(cursor.moveToFirst()){
            do{
                int customerID= cursor.getInt(0);
                String customerName= cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean isActivity = cursor.getInt(3) == 1;

                Customer_Model customer_model = new Customer_Model(customerID,customerName,customerAge,isActivity);
                returnlist.add(customer_model);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return returnlist;
    }
}
