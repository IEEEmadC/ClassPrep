package com.aerial.classprep;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 6/26/2017.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "ClassPrep", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS SUBJECT_TABLE "+"( SID TEXT PRIMARY KEY, NAME TEXT, CODE TEXT, START_DATE TEXT, END_DATE TEXT, DAY TEXT, WEEKS INTEGER "+")");
        db.execSQL("CREATE TABLE IF NOT EXISTS LESSON_TABLE"+"( LID TEXT PRIMARY KEY, NAME TEXT, SNAME TEXT, DAY TEXT, DONE TEXT, SID TEXT, FOREIGN KEY (SID) REFERENCES SUBJECT_TABLE(SID)"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SUBJECT_TABLE");
        db.execSQL("DROP TABLE IF EXISTS LESSON_TABLE");
        onCreate(db);
    }

    public void insertLessonData(Lesson l){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("LID",l.getLid());
        values.put("NAME",l.getName());
        values.put("SID",l.getSid());
        values.put("SNAME",l.getsName());
        values.put("DAY",l.getDay());
        values.put("DONE","0");
        db.insert("LESSON_TABLE",null,values);
    }

    public void insertSubjectData(Subject s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("SID",s.getSid());
        values.put("NAME",s.getName());
        values.put("CODE",s.getCode());
        values.put("START_DATE",s.getStDate());
        values.put("END_DATE",s.getEndDate());
        values.put("DAY",s.getDay());
        values.put("WEEKS",s.getWeeks());
        db.insert("SUBJECT_TABLE",null,values);
    }

    public String getLastSID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM SUBJECT_TABLE ORDER BY SID DESC LIMIT 1", null);
        System.out.println("I love WannaOne : "+res);
        res.moveToFirst();
        if(res.getCount()==0){
            return "0";
        }else{
            return  res.getString(0);
        }

    }

    public String getLastLID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM LESSON_TABLE ORDER BY LID DESC LIMIT 1", null);
        res.moveToFirst();

        if(res.getCount()==0){
            return "0";
        }else{
            return  res.getString(0);
        }
    }

    public Lesson getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM LESSON_TABLE WHERE LID= '" + name+"'", null);
        res.moveToFirst();
        //System.out.println(res.getString(0));
        /*Subject s=new Subject();
        s.setSid(res.getString(0));
        s.setName(res.getString(1));
        s.setCode(res.getString(2));
        s.setStDate(res.getString(3));
        s.setEndDate(res.getString(4));
        s.setDay(res.getString(5));
        s.setWeeks(Integer.parseInt(res.getString(6)));
        return s;*/

        Lesson ls=new Lesson();
        ls.setLid(res.getString(0));
        ls.setLid(res.getString(1));
        ls.setName(res.getString(2));
        return ls;
    }

    public ArrayList<DaySL> getLessons(String day) {
        ArrayList<DaySL> subs=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAME , SNAME , LID FROM LESSON_TABLE WHERE DAY='"+day+"' AND DONE='0' ", null);
        res.moveToFirst();
        ArrayList<String> donelist=new ArrayList<>();

        if(res.getCount()==0){
            return null;
        }else{
            String ss="";
            while(!res.isLast()){
                if(!ss.equals(res.getString(1))){
                    DaySL f=new DaySL();
                    f.setLes(res.getString(0));
                    f.setSub(res.getString(1));
                    subs.add(f);
                    ss=res.getString(1);
                    donelist.add(res.getString(2));
                }
                System.out.println(ss);
                res.moveToNext();
            }


            if(!ss.equals(res.getString(1))){
                System.out.println("inn");
                DaySL f=new DaySL();
                f.setLes(res.getString(0));
                f.setSub(res.getString(1));
                subs.add(f);
                donelist.add(res.getString(2));
            }

            SQLiteDatabase dbd = this.getWritableDatabase();
            for(String s:donelist){
                Cursor c=dbd.rawQuery(" UPDATE LESSON_TABLE SET DONE = '1' WHERE LID = '"+ss+"' ",null);
                c.moveToFirst();
                c.close();
            }

            return subs;
        }


    }

    /*public ArrayList<Fan> getAll(){
        ArrayList<Fan> fans=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM FAN_DETAILS", null);
        res.moveToFirst();

        while(!res.isLast()){
            Fan f=new Fan();
            f.setName(res.getString(0));
            f.setGroup(res.getString(1));
            f.setTelephone(res.getString(2));
            fans.add(f);
            res.moveToNext();
        }

        Fan f=new Fan();
        f.setName(res.getString(0));
        f.setGroup(res.getString(1));
        f.setTelephone(res.getString(2));
        fans.add(f);

        return fans;
    }*/
}
