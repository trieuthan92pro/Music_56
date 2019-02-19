package com.example.soundcloud.selected_genre_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.soundcloud.R;

public class SelectedGenreActivity extends AppCompatActivity {
    public static final String GENERE_KEY = "GENERE_KEY";

    public static Intent getIntent(Context context) {
        return new Intent(context, SelectedGenreActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_genre);
    }
}
