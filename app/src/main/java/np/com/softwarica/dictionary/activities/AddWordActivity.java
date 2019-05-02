package np.com.softwarica.dictionary.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.softwarica.dictionary.R;
import np.com.softwarica.dictionary.helpers.MyHelper;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etWord, etMeaning;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        getSupportActionBar().setTitle("Add Word");

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAdd = findViewById(R.id.btnAddWord);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String word = etWord.getText().toString();
        String meaning = etMeaning.getText().toString();

        MyHelper helper = new MyHelper(this);
        long id = helper.insertWord(word, meaning);
        
        if(id > 0){
            Toast.makeText(this, "Success...", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Failed...", Toast.LENGTH_SHORT).show();
        }
    }
}
