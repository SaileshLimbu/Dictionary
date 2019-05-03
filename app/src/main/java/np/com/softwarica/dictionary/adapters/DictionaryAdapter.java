package np.com.softwarica.dictionary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import np.com.softwarica.dictionary.R;
import np.com.softwarica.dictionary.activities.UpdateWordActivity;
import np.com.softwarica.dictionary.helpers.MyHelper;
import np.com.softwarica.dictionary.models.Word;

public class DictionaryAdapter extends BaseAdapter {

    private ArrayList<Word> listWords;
    private Context context;

    public DictionaryAdapter(Context context, ArrayList<Word> listWords) {
        this.context = context;
        this.listWords = listWords;
    }

    @Override
    public int getCount() {
        return listWords.size();
    }

    @Override
    public Object getItem(int position) {
        return listWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_word, null);
        TextView tv = v.findViewById(R.id.tvWord);
        tv.setText(listWords.get(position).getWord());
        ImageView imgDelete = v.findViewById(R.id.imgDelete);
        ImageView imgUpdate = v.findViewById(R.id.imgEdit);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper helper = new MyHelper(context);
                if (helper.deleteWord(listWords.get(position).getId() + "")) {
                    Toast.makeText(context, "Deleted...", Toast.LENGTH_SHORT).show();
                    replaceDataSet(helper.getAllWord());
                } else {
                    Toast.makeText(context, "Failed...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = listWords.get(position);
                Intent intent = new Intent(context, UpdateWordActivity.class);
                intent.putExtra("id", word.getId());
                intent.putExtra("word", word.getWord());
                intent.putExtra("meaning", word.getMeaning());
                context.startActivity(intent);
            }
        });


        return v;
    }

    public void replaceDataSet(ArrayList<Word> lists) {
        listWords.clear();
        listWords.addAll(lists);
        this.notifyDataSetChanged();
    }

    public void addWord(Word word) {
        listWords.add(word);
        this.notifyDataSetChanged();
    }
}
