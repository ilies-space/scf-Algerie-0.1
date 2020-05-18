package mbg.scf.algerie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDbClass extends SQLiteAssetHelper {
    private ArrayList<Continent> continentList = new ArrayList<Continent>();

    private static final String DATABASE_NAME = "testx.db";
    private static final int DATABASE_VERSION = 1 ;
    Context context;


    public MyDbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void getAllData()
    {
        loadSomeData();

        try {



            SQLiteDatabase sqLiteDatabase =getReadableDatabase();
            Toast.makeText(context, "Database is opened ", Toast.LENGTH_SHORT).show();

            if (sqLiteDatabase!=null)
            {
                //Cursor cursor = sqLiteDatabase.rawQuery("select * from tb",null);

            }else {
                Toast.makeText(context, "Database is null ", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(context, "getAllData error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void loadSomeData() {

        ArrayList<Country> countryList = new ArrayList<Country>();



        // test

        countryList = new ArrayList<Country>();
        Country country = new Country("descritpion 10","Capital, réserves et assimilés","10");
        countryList.add(country);
        country = new Country("descritpion 11","Report à nouveau","11");
        countryList.add(country);

        Continent continent = new Continent("CLASSE 1 - COMPTES DE CAPITAUX",countryList);
        continentList.add(continent);


        // 2 :
        countryList = new ArrayList<Country>();
        country = new Country("descritpion 10","Capital, réserves et assimilés","10");
        countryList.add(country);
        country = new Country("descritpion 11","Report à nouveau","11");
        countryList.add(country);

        continent = new Continent("CLASSE 2 - COMPTES DE CAPITAUX",countryList);
        continentList.add(continent);

    }

}
