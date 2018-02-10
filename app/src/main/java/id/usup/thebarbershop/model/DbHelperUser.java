package id.usup.thebarbershop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Acer on 12/14/2017.
 */

public class DbHelperUser extends SQLiteOpenHelper{
    private static final String TAG="DbHelperUser";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="UserBarberShop.db";


    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + UserContract.User.TABLE_NAME+ " ("+
                    " "+ UserContract.User._ID +" INTEGER PRIMARY KEY ,"+
                    " "+ UserContract.User.COLUM_NAME_USER +" TEXT,"+
                    " "+ UserContract.User.COLUM_GENDER_USER +" TEXT, "+
                    " "+ UserContract.User.COLUM_NOHP_USER +" TEXT, "+
                    " "+ UserContract.User.COLUM_EMAIL_USER +" TEXT, "+
                    " "+ UserContract.User.COLUM_PASSWORD_USER +" TEXT "+
                    ")";
    private static String SQL_DROP_TABLE="DROP TABLE IF EXIST "+UserContract.User.TABLE_NAME;


    public DbHelperUser(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    public DbHelperUser() {
        super(null, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate: "+SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        Log.d(TAG,"onUpgrade"+SQL_DROP_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onDowngrade"+SQL_CREATE_TABLE);
        super.onDowngrade(db, oldVersion, newVersion);
    }
    public boolean insertData(String name, String gender, String noHP,String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.User.COLUM_NAME_USER,name);
        values.put(UserContract.User.COLUM_GENDER_USER,gender);
        values.put(UserContract.User.COLUM_NOHP_USER,noHP);
        values.put(UserContract.User.COLUM_EMAIL_USER,email);
        values.put(UserContract.User.COLUM_PASSWORD_USER,password);
        long hasil = db.insert(UserContract.User.TABLE_NAME, null,values);
        db.close();
        //cek data masuk
        if (hasil == -1){
            Log.d(TAG,"Data gagal di save");
            return false;
        }else {
            Log.d(TAG,"Data sukses di save");
            return true;
        }
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(UserContract.User.TABLE_NAME," ID=? ",new String[]{id});
        return result;
    }
    public boolean checkUser(String email, String password){
        String[] colums = {UserContract.User._ID};

        SQLiteDatabase db = this.getReadableDatabase();
        String selection= UserContract.User.COLUM_EMAIL_USER +" = ? "+" AND "+ UserContract.User.COLUM_PASSWORD_USER+" =?";
        //selection argument
        String[] selectionArgs ={email,password};
        Cursor cursor= db.query(UserContract.User.TABLE_NAME,
                colums,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount >0){
            return true;
        }else {
            return false;
        }


    }
}
