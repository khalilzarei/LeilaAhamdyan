package com.google.leilaahamdyan;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private              SQLiteDatabase myDataBase;
    private final        Context        myContext;
    public static final  String         DATABASE_NAME    = "questions.db";
    public static final  String         TABLE_QUESTION   = "questions";
    private final static String         DATABASE_PATH    = "/data/data/com.google.leilaahamdyan/databases/";
    private static final int            DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        createDatabase();
    }


    public void createDatabase() {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            Log.e("DB Exists", "db exists");
        } else {
            this.getReadableDatabase();
            try {
                this.close();
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    //Check database already exist or not
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File   dbfile = new File(myPath);
            checkDB = dbfile.exists();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return checkDB;
    }


    private void copyDataBase() throws IOException {
        InputStream  mInput      = myContext.getAssets().open(DATABASE_NAME);
        String       outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput     = new FileOutputStream(outFileName);
        byte[]       mBuffer     = new byte[2024];
        int          mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //delete database
    public void db_delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    //Open database
    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase() throws SQLException {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.e("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

    }

    public List<QuestionItem> getAllQuestions() {
        List<QuestionItem> questionItems = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db     = this.getWritableDatabase();
        Cursor         cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String question   = cursor.getString(1);
                String answer     = cursor.getString(2);
                String answerDesc = cursor.getString(3);
                String answer1    = cursor.getString(4);
                String answer2    = cursor.getString(5);
                String answer3    = cursor.getString(6);
                String answer4    = cursor.getString(7);

                questionItems.add(new QuestionItem(question, answer, answerDesc, answer1, answer2, answer3, answer4));

            } while (cursor.moveToNext());
        }

        // return contact list
        return questionItems;
    }
}