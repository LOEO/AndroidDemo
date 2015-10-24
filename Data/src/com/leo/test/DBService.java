package com.leo.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by LT on 2015-05-21.
 */
public class DBService extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME="test.db";


    public DBService(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table [test]( [_id] AUTOINC," +
                "[name] varchar(20) not null on conflict fail,"+
                "constraint [sqlite_autoindex_t_test_1] primary key ([_id]))";
        db.execSQL(sql);
        Random random = new Random();
        for(int i=0; i<20; i++){
            String s = "";
            for(int j=0;j<10;j++){
                s += (char)(97+random.nextInt(26));
            }
            db.execSQL("insert into test(name) values(?)",new Object[]{s});
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor query(String sql,String[] args){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(sql,args);
    }
}
