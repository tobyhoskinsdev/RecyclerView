package examples.aaronhoskins.com.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    /***************************************************************************************************
     *
     * DO    NOT     DO    THIS
     */
    /****/     public static ArrayList<String> savedList = new ArrayList<>(); //DO NOT DO THIS!!!! **/
     /**************************************************************************************************/



    Intent passedIntent;
    Beverages beverages;
    ListView lstPassedBevageNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        lstPassedBevageNames = findViewById(R.id.lstSimpleListView);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, savedList);

        if(getIntent() != null) {
            passedIntent = getIntent();
            Bundle bundle = passedIntent.getExtras();
            beverages = bundle.getParcelable("bev");
            savedList.add(beverages.getName());
            Toast.makeText(this, beverages.getName() + " was passed" , Toast.LENGTH_LONG).show();
        }

        lstPassedBevageNames.setAdapter(arrayAdapter);

    }
}
