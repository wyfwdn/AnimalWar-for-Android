package example.android.my.project1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    /**
 * Created by Administrator on 2017/3/18.
 */

public class HelperActivity extends SQLiteOpenHelper {
        private static final String DB_NAME = "record.db";
        private static final String TBL_NAME = "RECORDTbl";
        private static final String CREATE_TBL = " create table " + " RECORDTbl(_id integer primary key autoincrement,name text,wins text, loses text, time text) ";
        private SQLiteDatabase db;
        HelperActivity(Context context) {
            super(context, DB_NAME, null, 2);
        }
        public void onCreate(SQLiteDatabase db) {
            this.db = db;
            db.execSQL(CREATE_TBL);
        }
        public void insert(ContentValues values) {
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TBL_NAME, null, values);
            db.close();
        }
        public Cursor query() {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
            return cursor;
        }
        public void delete(int id) {
            if (db == null)
                db = getWritableDatabase();
            db.delete(TBL_NAME, "_id=?", new String[] { String.valueOf(id) });
        }
        public void close() {
            if (db != null)
                db.close();
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
