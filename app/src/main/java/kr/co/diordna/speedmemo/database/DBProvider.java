package kr.co.diordna.speedmemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.joda.time.DateTime;

import java.util.ArrayList;

import kr.co.diordna.speedmemo.model.Memo;

/**
 * Created by ryans on 2018-01-21.
 */

public class DBProvider extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    private static String DB_NAME = "speedmemo.db";

    private static String MEMO_TABLE = "memo";
    private static String COLUMN_INDEX = "_id";
    private static String COLUMN_CONTENT = "content";
    private static String COLUMN_UPDATE_AT = "update_at";
    private static String COLUMN_CREATE_AT = "create_at";

    public DBProvider(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE IF NOT EXISTS " + MEMO_TABLE + " ("
                + COLUMN_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CONTENT + " TEXT, "
                + COLUMN_UPDATE_AT + " TEXT, "
                + COLUMN_CREATE_AT + " TEXT)";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }

    public void insertMemo(String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_UPDATE_AT, new DateTime().getMillis());
        values.put(COLUMN_CREATE_AT, new DateTime().getMillis());

        // 새로운 Row 추가
        db.insert(MEMO_TABLE, null, values);
        db.close(); // 연결종료
    }

    public void updateMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, memo.getContent());
        values.put(COLUMN_UPDATE_AT, new DateTime().getMillis());

        // 새로운 Row 추가
        String[] whereArgs = new String[]{String.valueOf(memo.getIndex())};
        db.update(MEMO_TABLE, values, COLUMN_INDEX + "=?", whereArgs);
        db.close(); // 연결종료
    }

    public void deleteMemo(int index) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = new String[]{String.valueOf(index)};
        db.delete(MEMO_TABLE, COLUMN_INDEX + "=?", whereArgs);
        db.close();
    }

    public ArrayList<Memo> selectAllMemo() {
        ArrayList<Memo> memos = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MEMO_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        while (cursor.moveToNext()) {
            Memo data = new Memo();
            data.setIndex(cursor.getInt(0));
            data.setContent(cursor.getString(1));
            data.setUpdateAt(cursor.getString(2));
            data.setCreateAt(cursor.getString(3));

            memos.add(data);
        }

        cursor.close();
        return memos;
    }

}
