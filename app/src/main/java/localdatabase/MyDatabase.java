package localdatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import organizer.app.DAO.NoteDAO;
import organizer.app.data.data.Note;



@Database(entities = {Note.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract NoteDAO noteDao();

    private static MyDatabase INSTANCE;

    public static MyDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class,
                                    "MyDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}




