package com.example.wordlist;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class WordListtest extends AppCompatActivity {

    android.support.v7.app.ActionBar actionBar;

    ListView listView;
    MySQLiteHandler handler;
    Cursor c, Fav_c;
    SimpleCursorAdapter adapter;
    private CheckBox checkBoxEng;
    private CheckBox checkBoxKor;

    private TextToSpeech textToSpeech;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView1);

        Button btnRegister = findViewById(R.id.btnRegister);
       // Button btnQuiz_one = findViewById(R.id.btnQuiz_one);
       // Button btnQuiz_two = findViewById(R.id.btnQuiz_two);
        Button btnCheckbox = findViewById(R.id.btnCheckbox);
        Button btnEditText = findViewById(R.id.btnEditText);
        Button btnEditClear= findViewById(R.id.btnEditClear);
        ImageButton soundBtn = findViewById(R.id.soundBtn);
        final ToggleButton imagetest = (ToggleButton) this.findViewById(R.id.imagetest);

        checkBoxEng = (CheckBox)findViewById(R.id.checkbox_Eng);
        checkBoxKor = (CheckBox)findViewById(R.id.checkbox_Kor);

        final EditText editSearch = (EditText)findViewById(R.id.editSearch);

        handler = MySQLiteHandler.open(getApplicationContext());
        c = handler.selectAll();
        startManagingCursor(c);
        adapter =
                new SimpleCursorAdapter(
                        getApplicationContext(),
                        R.layout.list_row,
                        c,
                        new String[]{"_id", "Word","Meaning"},
                        new int[]{R.id.txtId, R.id.txtWord, R.id.txtMeaning},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);


        //체크박스
        btnCheckbox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(checkBoxEng.isChecked() && checkBoxKor.isChecked()){
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{"_id", "Word","Meaning"},
                                    new int[]{R.id.txtId, R.id.txtWord, R.id.txtMeaning},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);

                }
                else if (checkBoxEng.isChecked() && !checkBoxKor.isChecked()) {
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{"_id", "Word"},
                                    new int[]{R.id.txtId, R.id.txtWord},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);
                }
                else if (!checkBoxEng.isChecked() && checkBoxKor.isChecked()) {
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{"_id", "Meaning"},
                                    new int[]{R.id.txtId, R.id.txtMeaning},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);
                }
                else {
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{},
                                    new int[]{},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);
                }
            }
        });



        //리스트뷰 클릭시 detailactivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                TextView tv = (TextView) v.findViewById(R.id.txtId);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("_id", tv.getText().toString());
                startActivity(intent);
            }
        });

        //단어추가버튼
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inf = getLayoutInflater();
                View convertView = inf.inflate(R.layout.wordadd, null);

                final EditText editWord = (EditText) convertView.findViewById(R.id.editWord);
                final EditText editMeaning = (EditText) convertView.findViewById(R.id.editMeaning);
                final EditText editTag = (EditText) convertView.findViewById(R.id.editTag);

                AlertDialog.Builder builder = new AlertDialog.Builder(WordListtest.this);
                builder.setTitle("단어등록");
                builder.setIcon(android.R.drawable.stat_sys_warning);
                builder.setView(convertView);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String Word = editWord.getText().toString();
                        String Meaning = editMeaning.getText().toString();
                        String Tag = editTag.getText().toString();

                        handler.insert(Word, Meaning, Tag);
                        c.requery();
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
            }//end
        });

        //검색버튼
        btnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Search = editSearch.getText().toString();

                if(Search.length() == 0) {
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{"_id", "Word","Meaning"},
                                    new int[]{R.id.txtId, R.id.txtWord, R.id.txtMeaning},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);
                }

                else{
                    c = handler.selectBySearch(Search);
                    startManagingCursor(c);
                    adapter =
                            new SimpleCursorAdapter(
                                    getApplicationContext(),
                                    R.layout.list_row,
                                    c,
                                    new String[]{"_id", "Word","Meaning"},
                                    new int[]{R.id.txtId, R.id.txtWord, R.id.txtMeaning},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                    listView.setAdapter(adapter);
                }
            }
        });

        btnEditClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSearch.setText(null);

                c = handler.selectAll();
                startManagingCursor(c);

                adapter =
                        new SimpleCursorAdapter(
                                getApplicationContext(),
                                R.layout.list_row,
                                c,
                                new String[]{"_id", "Word","Meaning"},
                                new int[]{R.id.txtId, R.id.txtWord, R.id.txtMeaning},
                                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                listView.setAdapter(adapter);
            }
        });


//
//        btnQuiz_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//        btnQuiz_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }





}

