package com.example.soundcloud.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.soundcloud.data.model.SearchHistory;
import com.example.soundcloud.data.source.SearchHistoryDataSource;

import java.util.ArrayList;
import java.util.List;

public class HistorySearchDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sound_cloud";
    private static final String TABLE_NAME = "search_history";
    private static final String COLLUMN_SEARCH_KEY = "search_key";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "( " + COLLUMN_SEARCH_KEY + ")";
    private static final String SQL_DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static HistorySearchDatabaseHelper sInstance;

    private HistorySearchDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static HistorySearchDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HistorySearchDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_QUERY);
        onCreate(db);
    }

    public void addHistorySearch(List<SearchHistory> searchHistories,
                                 SearchHistoryDataSource.HistorySearchCallback callBack) {
        SQLiteDatabase db = getWritableDatabase();
        boolean isAddSuccess = true;
        String err = null;
        try {
            ContentValues values = new ContentValues();
            for (SearchHistory searchHistory : searchHistories) {
                values.put(COLLUMN_SEARCH_KEY, searchHistory.getSearchKey());
                db.insertOrThrow(TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            isAddSuccess = false;
            err = e.getMessage();
            e.printStackTrace();
        }
        if (!isAddSuccess) {
            callBack.onFailed(err);
        } else {
            callBack.onSuccess();
        }
    }

    public void getAllSearchHistory(SearchHistoryDataSource.HistorySearchCallback callBack) {
        List<SearchHistory> histories = new ArrayList<>();
        String errorMessage = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {
                String searchKey = cursor.getString(cursor.getColumnIndex(COLLUMN_SEARCH_KEY));
                SearchHistory searchHistory = new SearchHistory(searchKey);
                histories.add(searchHistory);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (errorMessage != null) {
                callBack.onFailed(errorMessage);
            } else {
                callBack.onSuccess(histories);
            }
        }
    }

    public void deleteAllHistories(SearchHistoryDataSource.HistorySearchCallback callBack) {
        SQLiteDatabase db = getWritableDatabase();
        String err = null;
        try {
            db.delete(TABLE_NAME, null, null);
        } catch (Exception e) {
            err = e.getMessage();
        } finally {
            if (err != null) {
                callBack.onFailed(err);
            } else {
                callBack.onSuccess();
            }
        }
    }
}
