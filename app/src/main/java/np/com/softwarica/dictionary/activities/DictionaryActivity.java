package np.com.softwarica.dictionary.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);

        helper = new MyHelper(this);
        listWords = helper.getAllWord();

        DictionaryAdapter adapter = new DictionaryAdapter(this, listWords);
        listView.setAdapter(adapter);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddWordActivity.class));
    }
}
