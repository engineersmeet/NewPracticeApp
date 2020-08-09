package com.pravin.lede.gl.myapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pravin.lede.gl.myapplication.models.QuestionDataModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Question_db";
    public static final String TABLE_NAME = "Question_Table";
    public static final String COL_id = "id";
    public static final String COL_question = "Question";
    public static final String COL_opt1 = "Option_1";
    public static final String COL_opt2 = "Option_2";
    public static final String COL_opt3 = "Option_3";
    public static final String COL_opt4 = "Option_4";
    public static final String COL_answer = "Answer";

    public QuestionDb(@Nullable Context context, int version) {
        super(context, DATABASE_NAME, null,
                version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Question_Table (id integer primary key, Question text, Option_1 text, Option_2 text, Option_3 text, Option_4 text, Answer text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Question_Table");
        onCreate(db);
    }

    public boolean insertQuestionData(QuestionDataModel model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_question, model.getQuetion());
        contentValues.put(COL_opt1, model.getOpt1());
        contentValues.put(COL_opt2, model.getOpt2());
        contentValues.put(COL_opt3, model.getOpt3());
        contentValues.put(COL_opt4, model.getOpt4());
        contentValues.put(COL_answer, model.getAnswer());

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<QuestionDataModel> getAllQuestion() {
        ArrayList<QuestionDataModel> questionDataList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String question = cursor.getString(1);
                String opt1 = cursor.getString(2);
                String opt2 = cursor.getString(3);
                String opt3 = cursor.getString(4);
                String opt4 = cursor.getString(5);
                String answer = cursor.getString(6);
                QuestionDataModel questionDataModel = new QuestionDataModel(question, opt1, opt2, opt3, opt4, answer);
                questionDataList.add(questionDataModel);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionDataList;
    }
}

