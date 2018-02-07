package app.its.itfabiorossi.its_androidapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FABIO.ROSSI on 07/02/2018.
 */

public class TrainingCourseDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1; //versione attuale
    public static final String DATABASE_NAME = "TrainingCourseDB.db"; //nome del DB

    public TrainingCourseDbHelper(Context context) { //costruttore di SQLiteOpenHelper
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //NON CI INTERESSA
    }

    //CREATE TABLE
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TrainingCourseContract.TrainingCourseEntry.TABLE_NAME + " (" +
                    TrainingCourseContract.TrainingCourseEntry._ID + " INTEGER PRIMARY KEY," + //id definito nella classe in cui facciamo override
                    TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_START + " TEXT," +
                    TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_END + " TEXT)";

}