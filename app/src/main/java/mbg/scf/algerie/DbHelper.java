package mbg.scf.algerie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteAssetHelper {


    private static final int DATABASE_VERSION = 1 ;
    Context context;


    public DbHelper(Context context, String lan) {

        super(context, lan, null, DATABASE_VERSION);

        this.context = context;
    }



    public ArrayList<Country> getClass1() {
        SQLiteDatabase db =getReadableDatabase();
        Country country = null;
        ArrayList<Country> countryList = new ArrayList<>();
        //openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM class1", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            country = new Country(cursor.getString(2),cursor.getString(1), cursor.getString(0));
            countryList.add(country);
            cursor.moveToNext();
        }

        cursor.close();
        //closeDatabase();
        return countryList;
    }

    public ArrayList<Country> getClass2() {
        SQLiteDatabase db =getReadableDatabase();
        Country country = null;
        ArrayList<Country> countryList = new ArrayList<>();
        //openDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM class2", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            country = new Country(cursor.getString(2),cursor.getString(1), cursor.getString(0));
            countryList.add(country);
            cursor.moveToNext();
        }

        cursor.close();
        //closeDatabase();
        return countryList;
    }



}
