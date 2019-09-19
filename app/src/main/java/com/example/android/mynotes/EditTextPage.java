package com.example.android.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashSet;

public class EditTextPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        final EditText t=(EditText) findViewById(R.id.edit_text);

        Intent i=getIntent();

        final int noteID=i.getIntExtra("noteID",-1);

        if(noteID !=-1){

            t.setText(MainActivity.notes.get(noteID));
        }

        ImageView done=(ImageView) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (noteID != -1)
              {
                MainActivity.notes.set(noteID, t.getText().toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();
             }
              else
              {
                  MainActivity.notes.add(t.getText().toString());
                  MainActivity.arrayAdapter.notifyDataSetChanged();
              }
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences=getApplicationContext()
                        .getSharedPreferences("com.example.android.mynotes", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

                onBackPressed();
              }
            });




        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
