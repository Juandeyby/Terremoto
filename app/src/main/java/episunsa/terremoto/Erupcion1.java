package episunsa.terremoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Erupcion1 extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erupcion);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expadible2);

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
        top250.add(getResources().getString(R.string.erupcion_antes_1));
        top250.add(getResources().getString(R.string.erupcion_antes_2));
        top250.add(getResources().getString(R.string.erupcion_antes_3));
        top250.add(getResources().getString(R.string.erupcion_antes_4));
        top250.add(getResources().getString(R.string.erupcion_antes_5));
        top250.add(getResources().getString(R.string.erupcion_antes_6));

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add(getResources().getString(R.string.erupcion_durante_1));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_2));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_3));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_4));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_5));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_6));
        nowShowing.add(getResources().getString(R.string.erupcion_durante_7));

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add(getResources().getString(R.string.erupcion_despues_1));
        comingSoon.add(getResources().getString(R.string.erupcion_despues_2));
        comingSoon.add(getResources().getString(R.string.erupcion_despues_3));
        comingSoon.add(getResources().getString(R.string.erupcion_despues_4));
        comingSoon.add(getResources().getString(R.string.erupcion_despues_5));

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
