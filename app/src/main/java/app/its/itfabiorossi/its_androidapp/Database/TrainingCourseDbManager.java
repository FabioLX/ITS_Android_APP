package app.its.itfabiorossi.its_androidapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by FABIO.ROSSI on 07/02/2018.
 */

public class TrainingCourseDbManager {

    private TrainingCourseDbHelper mHelper;

    public TrainingCourseDbManager(Context context){ //2- ci facciamo passare il context

        mHelper= new TrainingCourseDbHelper(context); //1 - gli passiamo il ctx


    }



    public  void insertRecord( String description, String start, String end){

        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values= new ContentValues(); //creazione oggetto content values, ID cre
        values.put(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_START, start);
        values.put(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_END, end);

        long l= db.insert(TrainingCourseContract.TrainingCourseEntry.TABLE_NAME, null, values);

        Log.d("LX", "database insert: "+l);


    }

    public void deleteRecord(int id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String where = TrainingCourseContract.TrainingCourseEntry._ID + " = ?";
        String[] values= {Integer.toString(id)}; //array di string!!!
        int l=db.delete(TrainingCourseContract.TrainingCourseEntry.TABLE_NAME,where, values); //array di string

        Log.d("LX", "delete OK: "+l);
    }

    public void readRecord(){

        SQLiteDatabase db = mHelper.getWritableDatabase();

        String[] select={
                TrainingCourseContract.TrainingCourseEntry._ID,
                TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_DESCRIPTION,
                TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_START,
                TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_END
        };

        String where= null;//" * ";
        String[] whereArgs= null;

        //sort
        String sort=  TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_START + " DESC";

        Cursor cursor= db.query(
                TrainingCourseContract.TrainingCourseEntry.TABLE_NAME,
                select,
                where,
                whereArgs,
                null,
                null,
                sort
                );

        while (cursor.moveToNext()){

            int trainingCourseID= cursor.getInt(cursor.getColumnIndex(TrainingCourseContract.TrainingCourseEntry._ID)); //vuole l'indice della colonna
            String descr= cursor.getString(cursor.getColumnIndex(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_DESCRIPTION));
            String start= cursor.getString(cursor.getColumnIndex(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_START));
            String end= cursor.getString(cursor.getColumnIndex(TrainingCourseContract.TrainingCourseEntry.COLUMN_NAME_END));

            Log.d("LX", "ID: "+trainingCourseID+" start "+start+" end "+ end+" descr "+ descr);

        }

    }

}
