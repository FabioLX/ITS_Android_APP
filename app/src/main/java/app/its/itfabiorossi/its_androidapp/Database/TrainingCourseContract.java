package app.its.itfabiorossi.its_androidapp.Database;

import android.provider.BaseColumns;

/**
 * Created by FABIO.ROSSI on 07/02/2018.
 */

public class TrainingCourseContract {
    private TrainingCourseContract() {}

    public static class TrainingCourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "TrainingCourse";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_START = "start";
        public static final String COLUMN_NAME_END = "end";
    }
}