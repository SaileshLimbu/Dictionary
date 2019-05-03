package np.com.softwarica.dictionary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import np.com.softwarica.dictionary.R;
import np.com.softwarica.dictionary.adapters.DictionaryAdapter;
import np.com.softwarica.dictionary.helpers.MyHelper;
import np.com.softwarica.dictionary.models.Word;

public class DictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private ListView listView;
    private ArrayList<Word> listWords;
    private MyHelper helper;
    private EditText etSearchText;
    private Button btnSearch;
    private DictionaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
        etSearchText = findViewById(R.id.etSearchText);
        btnSearch = findViewById(R.id.btnSearch);

        helper = new MyHelper(this);
        listWords = helper.getAllWord();

        adapter = new DictionaryAdapter(this, listWords);
        listView.setAdapter(adapter);

        fab.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(this, AddWordActivity.class));
        } else {
            adapter.replaceDataSet(helper.searchByWord(etSearchText.getText().toString()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.replaceDataSet(helper.getAllWord());
    }
}