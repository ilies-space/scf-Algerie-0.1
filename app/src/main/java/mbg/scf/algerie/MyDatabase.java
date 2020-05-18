package mbg.scf.algerie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {
    //Database Information ( location in Assest )
    private static final String DATABASE_NAME = "mydb.db";
    private static final int DATABASE_VERSION = 1;


    //Constructor :
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    SQLiteDatabase db = getReadableDatabase();


    public List<user> getListUsers() {
        user user = null;
        List<user> userslist = new ArrayList<>();
        //openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            user = new user(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            userslist.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        //closeDatabase();
        return userslist;
    }

    public user getUserEmail(long id)
    {

        Cursor c = db.rawQuery("select * from user where id = '"+id+"' ", null);
        c.moveToFirst();

        user Selecteduser = null;
        if (c.moveToFirst()) {

             Selecteduser = new user(""+c.getString(c.getColumnIndex("name")),
                     ""+c.getString(c.getColumnIndex("location")),
                     ""+c.getString(c.getColumnIndex("email")),
                        0);

        }
        return Selecteduser;

    }




}
