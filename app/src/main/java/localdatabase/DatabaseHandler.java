package localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import organizer.app.data.data.Note;
import organizer.app.data.data.Person;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "OrganizerDb";
    private static final String NOTES_TABLE = "Notes";
    private static final String NOTE_ID = "id";
    private static final String OWNER_ID = "ownerId";
    private static final String CONTENT = "Content";
    private static final String NOTE_NAME = "noteName";
    private static final String PERSON_TABLE = "Person";
    private static final String PERSON_ID = "id";
    private static final String PERSON_NAME = "ownerId";
    private static final String PERSON_NUMBER = "Content";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + NOTES_TABLE + "("
                + NOTE_ID + " INTEGER PRIMARY KEY," + OWNER_ID + " INTEGER," + CONTENT + " TEXT," + NOTE_NAME + " TEXT" + ");";
        db.execSQL(CREATE_NOTES_TABLE);
        String CREATE_PERSON_TABLE = "CREATE TABLE " + PERSON_TABLE + "("
                + PERSON_ID + " INTEGER PRIMARY KEY," + PERSON_NAME + " TEXT," + PERSON_NUMBER + " INTEGER" + ");";
        db.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);

        onCreate(db);
    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTE_ID, note.getId());
        values.put(OWNER_ID, note.getOwnerId());
        values.put(NOTE_NAME, note.getNoteName());
        values.put(CONTENT, note.getNoteContent());

        db.insert(NOTES_TABLE, null, values);
    }

    public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NOTES_TABLE, new String[] { NOTE_ID,
                        OWNER_ID, CONTENT,NOTE_NAME }, NOTE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(2), cursor.getString(3));
        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notesList = new ArrayList<Note>();
        String selectQuery = "SELECT  * FROM " + NOTES_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setOwnerId(Integer.parseInt(cursor.getString(1)));
                note.setContent(cursor.getString(2));
                note.setNoteName(cursor.getString(3));
                notesList.add(note);
            } while (cursor.moveToNext());
        }

        return notesList;
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES_TABLE, NOTE_ID + " = ?",
                new String[] { String.valueOf(note.getId()) });
        db.close();
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + NOTES_TABLE;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()) {
            count = cursor.getCount();
            cursor.close();
        }

        return count;
    }

    //------------------------------------------------------PERSON---------------------------------------------------------------------//

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSON_ID, person.getId());
        values.put(PERSON_NAME, person.getName());
        values.put(PERSON_NUMBER, person.getPhoneNumber());

        db.insert(PERSON_TABLE, null, values);
    }

    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PERSON_TABLE, new String[] { PERSON_ID,
                        PERSON_NAME, PERSON_NUMBER}, PERSON_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return person;
    }

    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + PERSON_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setPhoneNumber(cursor.getString(2));
                personList.add(person);
            } while (cursor.moveToNext());
        }

        return personList;
    }

    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PERSON_TABLE, PERSON_ID + " = ?",
                new String[] { String.valueOf(person.getId()) });
        db.close();
    }

    public int getPersonCount() {
        String countQuery = "SELECT  * FROM " + PERSON_TABLE;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()) {
            count = cursor.getCount();
            cursor.close();
        }

        return count;
    }

}
