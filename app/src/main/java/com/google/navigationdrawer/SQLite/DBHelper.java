package com.google.navigationdrawer.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.navigationdrawer.activities.ImportContactsActivity;
import com.google.navigationdrawer.activities.NewStudentActivity;
import com.google.navigationdrawer.models.Contact;
import com.google.navigationdrawer.models.Student;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    private static final int DATABASE_VER = 1;
    public static final String DATABASE_NAME = "SQLCipher.db";

    public static final String PASS_PHRASE = "!E)kTsfe)%";

    public static final String TABLE_NAME = "Students";

    public static final String CLM_ID = "StudentID";
    public static final String CLM_FIRST_NAME = "FirstName";
    public static final String CLM_LAST_NAME = "LastName";
    public static final String CLM_LEVEL = "Level";
    public static final String CLM_MAJOR = "Major";
    public static final String CLM_BIRTH = "Birth";
    public static final String CLM_SUPPORTER = "Supporter";
    public static final String CLM_PHONE1 = "Phone1";
    public static final String CLM_PHONE2 = "Phone2";
    public static final String CLM_PARENT_PHONE = "ParentPhone";

    private static final String[] STUDENT_COLUMNS = {
            CLM_ID, CLM_FIRST_NAME, CLM_LAST_NAME, CLM_LEVEL, CLM_MAJOR, CLM_BIRTH,
            CLM_SUPPORTER, CLM_PHONE1, CLM_PHONE2, CLM_PARENT_PHONE
    };

    private static final String CMD = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' ( '" +
            CLM_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '" +
            CLM_FIRST_NAME + "' TEXT, '" +
            CLM_LAST_NAME + "' TEXT NOT NULL, '" +
            CLM_LEVEL + "' TEXT, '" +
            CLM_MAJOR + "' TEXT, '" +
            CLM_BIRTH + "' TEXT, '" +
            CLM_SUPPORTER + "' TEXT, '" +
            CLM_PHONE1 + "' TEXT NOT NULL UNIQUE, '" +
            CLM_PHONE2 + "' TEXT, '" +
            CLM_PARENT_PHONE + "' TEXT " +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER); //just to call superclass correctly
    }

    static public synchronized DBHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DBHelper(context); //couldnt use context here if we wouldn't called superclass
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CMD);
        Log.i("MYTAG", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.i("MYTAG", "Table dropped");

        onCreate(db);
    }

    public boolean insertNewStudent(Student student) {

        SQLiteDatabase db = getWritableDatabase(PASS_PHRASE);

        long insertId = db.insert(TABLE_NAME, null, student.getContentValues());

        db.close();

        if (insertId == -1) {
            return false;
        } else {
            return true;
        }

    }

    public int insertContacts(ArrayList<Contact> contacts) {

        int success = 0;
        SQLiteDatabase db = getWritableDatabase(PASS_PHRASE);

        db.beginTransaction();

        for (Contact contact : contacts) {

            long insertId = db.insert(TABLE_NAME, null, contact.getContentValues());

            if (insertId != -1) {
                success++;
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();

        return success;
    }

    public List<Student> getAllStudents() {

        SQLiteDatabase db = getReadableDatabase(PASS_PHRASE);
        List<Student> students = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM '" + TABLE_NAME + "'", null);

        if (cursor.moveToFirst()) {
            do {
                //process each row
                Student student = Student.cursorToStudent(cursor);
                students.add(student);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return students;
    }

    public void update(Student student, int studentID) {

        SQLiteDatabase db = getWritableDatabase(PASS_PHRASE);

        int count = db.update(TABLE_NAME, student.getContentValues(),CLM_ID + " = " + studentID, null);

        Log.i("MYTAG" , count + " rows updated");

        if (db.isOpen()) db.close();
    }
}
