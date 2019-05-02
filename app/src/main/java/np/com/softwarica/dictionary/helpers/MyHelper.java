package np.com.softwarica.dictionary.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import np.com.softwarica.dictionary.models.Word;

public class MyHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "db_dictionary";
    private final static int DB_VERSION = 1;

    private final static String TABLE_NAME = "tbl_word";


    public MyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, meaning TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertWord(String word, String meaning) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", word);
        contentValues.put("meaning", meaning);

        return this.getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Word> getAllWord() {
        ArrayList<Word> listWords = new ArrayList<>();
        String[] columns = {"id", "word", "meaning"};
        Cursor cursor = this.getReadableDatabase().query(TABLE_NAME, columns, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Word word = new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listWords.add(word);
            }
        }
        return listWords;
    }
}
