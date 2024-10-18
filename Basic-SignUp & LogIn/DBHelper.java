import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "LoginDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("Create table Userdetails(fname TEXT primary key,lname TEXT,uname TEXT,pass TEXT,cpass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("Drop table if exists Userdetails");
    }

    public boolean insertuserdata(String fname,String lname,String uname,String pass,String cpass){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("lname",lname);
        contentValues.put("uname",uname);
        contentValues.put("pass",pass);
        contentValues.put("cpass",cpass);
        long result = Db.insert("Userdetails",null,contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
}
