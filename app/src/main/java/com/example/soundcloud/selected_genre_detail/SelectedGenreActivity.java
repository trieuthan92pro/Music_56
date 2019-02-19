package com.example.soundcloud.selected_genre_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.soundcloud.R;
import com.example.soundcloud.data.model.Genre;

public class SelectedGenreActivity extends AppCompatActivity {
    private static final String EXTRA_GENERE = "EXTRA_GENERE";

    public static Intent getIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, SelectedGenreActivity.class);
        intent.putExtra(SelectedGenreActivity.EXTRA_GENERE, genre);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_genre);
    }
}
