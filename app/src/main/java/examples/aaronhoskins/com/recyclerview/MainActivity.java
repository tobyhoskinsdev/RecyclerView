package examples.aaronhoskins.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    //declare views
    EditText etName;
    EditText etSize;
    EditText etRating;
    CheckBox chkAlcoholic;
    CheckBox chkCarbonated;
    CheckBox chkCaffinated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind other views used to insert Beverage
        etName = findViewById(R.id.etBevName);
        etSize = findViewById(R.id.etServingSize);
        etRating = findViewById(R.id.etRating);
        chkAlcoholic = findViewById(R.id.chkIsAlcoholic);
        chkCarbonated = findViewById(R.id.chkIsCarbonated);
        chkCaffinated = findViewById(R.id.chkIsCaffinated);

        //Bind RecyclerView
        recyclerView = findViewById(R.id.rvRecyclerView);

        //Recycler View needs 2 items
        //  1. Layout Manager (Can be customized, but we generally us a default
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //  2.RecyclerView adapter (We write this)
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(genetateListOfBeverages());
        recyclerView.setAdapter(myRecyclerViewAdapter);

        /******
         *   If we had a database, call the method from the database helper that returns an
         *         ArrayList (most likely all the databases entries) and pass it the constructor
         *         ex:
         *         myRecyclerViewAdapter = new MyRecyclerViewAdapter(genetateListOfBeverages());
         *         Change to:
         *         ArrayList<Beverage> bevList = beverageDatabaseHelper.getAllBeverages();
         *         myRecyclerViewAdapter = new MyRecyclerViewAdapter(bevList);
         */


    }
    private ArrayList<Beverages> genetateListOfBeverages() {
        ArrayList<Beverages> beveragesArrayList = new ArrayList<>();
        beveragesArrayList.add(new Beverages("Coke", "12 once", 3, true, false, true));
        beveragesArrayList.add(new Beverages("Stella", "12 once", 5, false, true, true));
        beveragesArrayList.add(new Beverages("Jack Daniels", "1 gal", 5, false, true, false));
        beveragesArrayList.add(new Beverages("Grape Juice", "8 once", 2, false,false, false));
        beveragesArrayList.add(new Beverages("Red Bull", "8.5 once", 4, true, false, true));
        beveragesArrayList.add(new Beverages("Monster", "16 once", 2, true, false, true));
        beveragesArrayList.add(new Beverages("Water", "Who really cares", 1, false, false, false));
        beveragesArrayList.add(new Beverages("Blood of your enemy", "All of it",5 ,true, true, false));
        beveragesArrayList.add(new Beverages("Flavor aid", "Depends on sugar", 3, false, false, false));
        beveragesArrayList.add(new Beverages("Pepsi", "12 once", 3, true, false, true));
        beveragesArrayList.add(new Beverages("Coors", "12 once", 5, false, true, true));
        beveragesArrayList.add(new Beverages("Patron", "1 gal", 5, false, true, false));
        beveragesArrayList.add(new Beverages("Apple Juice", "8 once", 2, false,false, false));
        beveragesArrayList.add(new Beverages("Rockstar", "8.5 once", 4, true, false, true));
        beveragesArrayList.add(new Beverages("Coffie", "16 once", 2, false, false, true));
        beveragesArrayList.add(new Beverages("V8", "Who really cares", 1, false, false, false));
        beveragesArrayList.add(new Beverages("Elixer of life", "All of it",5 ,true, true, false));
        beveragesArrayList.add(new Beverages("Purple drank", "Depends on sugar", 3, false, false, false));

        return beveragesArrayList;
    }

    public void onClick(View view) {
        //Get input from user
        String name = etName.getText().toString();
        String size = etSize.getText().toString();
        int rating = Integer.parseInt(etRating.getText().toString());
        boolean isAlc = chkAlcoholic.isChecked();
        boolean isCarb = chkCarbonated.isChecked();
        boolean isCaff = chkCaffinated.isChecked();
        //Make an object that matches the object passed in the ArrayList
        Beverages beveragesToInsert = new Beverages(name,size,rating,isCaff,isAlc,isCarb);

        //Call the method in the adapter to add the beverage to list
        myRecyclerViewAdapter.addBeverageToList(beveragesToInsert);
    }
}
