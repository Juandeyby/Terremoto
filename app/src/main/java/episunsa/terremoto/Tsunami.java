package episunsa.terremoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tsunami extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tsunami);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expadible5);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //imgButton1 =(ImageButton)findViewById(R.id.imageButton1);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getResources().getString(R.string.antes));
        listDataHeader.add(getResources().getString(R.string.durante));
        listDataHeader.add(getResources().getString(R.string.despues));

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add(getResources().getString(R.string.tsunami_antes_1));
        top250.add(getResources().getString(R.string.tsunami_antes_2));
        top250.add(getResources().getString(R.string.tsunami_antes_3));
        top250.add(getResources().getString(R.string.tsunami_antes_4));
        top250.add(getResources().getString(R.string.tsunami_antes_5));
        top250.add(getResources().getString(R.string.tsunami_antes_6));
        top250.add(getResources().getString(R.string.tsunami_antes_7));
        top250.add(getResources().getString(R.string.tsunami_antes_8));
        top250.add(getResources().getString(R.string.tsunami_antes_9));
        top250.add(getResources().getString(R.string.tsunami_antes_10));

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add(getResources().getString(R.string.tsunami_durante_1));
        nowShowing.add(getResources().getString(R.string.tsunami_durante_2));
        nowShowing.add(getResources().getString(R.string.tsunami_durante_3));
        nowShowing.add(getResources().getString(R.string.tsunami_durante_4));
        nowShowing.add(getResources().getString(R.string.tsunami_durante_5));
        nowShowing.add(getResources().getString(R.string.tsunami_durante_6));

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add(getResources().getString(R.string.tsunami_despues_1));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_2));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_3));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_4));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_5));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_6));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_7));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_8));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_9));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_10));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_11));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_12));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_13));
        comingSoon.add(getResources().getString(R.string.tsunami_despues_14));

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
    @Override
    protected void onResume(){
        super.onResume();

        //imgButton1.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent goToAppIntent = new Intent(Terre1.this, MainActivity.class);
        //    }
        //});
    }
}
