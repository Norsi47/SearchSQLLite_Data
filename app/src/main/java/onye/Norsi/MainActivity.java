package onye.Norsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import onye.Norsi.Adapter.SearchAdapter;
import onye.Norsi.DataBase.DataBase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter searchAdapter;
    DataBase dataBase;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initView(Video Example)
        //id is from the activitymain.xml
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //id is from the activitymain.xml
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);

        //initDB (video example)
        //can do this at the top as well, when Database was first called
        dataBase = new DataBase(this);

        //setup search bar
        //matches hint in activitymain.xml?
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                List<String> suggest = new ArrayList<>();
                for (String search:suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    //restore to default after search is closed
                    searchAdapter = new SearchAdapter(getBaseContext(), dataBase.getFriends());
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            //confirms search by pressing enter or hitting search
            public void onSearchConfirmed(CharSequence text) {
                //can only pick one method
//                startSearch(text.toString());
                startSearchByAddress(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {


            }
        });

        //init adapter default set all result
        searchAdapter = new SearchAdapter(this, dataBase.getFriends());
        recyclerView.setAdapter(searchAdapter);



    }

    private void startSearch(String text) {
        //this shows what we want to see in the search adapter (the hints)
        searchAdapter = new SearchAdapter(this, dataBase.getFriendByName(text));
        recyclerView.setAdapter(searchAdapter);
    }

    //finding by address
    private void  startSearchByAddress(String address) {
        searchAdapter = new SearchAdapter(this, dataBase.getFriendByAddress(address));
        recyclerView.setAdapter(searchAdapter);

    }

    //for streak *2
    //calls in the database sql code
    //sets everything in the dbd files into the search bar
    private void loadSuggestList() {
        suggestList = dataBase.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}