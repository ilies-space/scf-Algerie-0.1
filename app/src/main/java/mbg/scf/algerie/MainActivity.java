package mbg.scf.algerie;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Continent> continentList = new ArrayList<Continent>();
    public DbHelper db;
    private List<Country> mUserslist;
    public String Lan ;
    public String[] title;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        Lan = "infoar.db";


        //Load db :
        db = new DbHelper(this , Lan);

        //display the list
        displayList();
        //expand all Groups
        expandAll();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadData();


        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(MainActivity.this, continentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        // click listener :

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {



                TextView tv= (TextView) v.findViewById(R.id.code);

                String data= tv.getText().toString();

                Toast.makeText(MainActivity.this, ""+data, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    private void loadData() {

        if (Lan.equals("infofr.db"))
        {
                title = new String[]{"CLASSE 1 - COMPTES DE CAPITAUX",
                        "CLASSE 2 – COMPTES D'IMMOBILISATIONS"};
        }else
        {
                title = new String[]{"الصنف 1- حسابات رؤوس الأموال",
                        "2- الصنف  حسابات التثبيتات"};
        }


        ArrayList<Country> x = db.getClass1();
        Continent continent = new Continent(title[0], x);
        continentList.add(continent);

        ArrayList<Country> x2 = db.getClass2();

        continent = new Continent(title[1], x2);
        continentList.add(continent);


/*
        // 2 :
        countryList = new ArrayList<Country>();
        country = new Country("descritpion 10","Capital, réserves et assimilés","10");
        countryList.add(country);
        country = new Country("descritpion 11","Report à nouveau","11");
        countryList.add(country);

        continent = new Continent("CLASSE 2 - COMPTES DE CAPITAUX",countryList);
        continentList.add(continent);
*/

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }





}