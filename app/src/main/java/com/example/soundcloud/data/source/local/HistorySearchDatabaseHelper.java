package com.example.soundcloud.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.soundcloud.data.model.SearchHistory;
import com.example.soundcloud.data.source.SearchHistoryDataSource;

import java.util.ArrayList;
import java.util.List;

public class HistorySearchDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sound_cloud";
    private static final int LIMIT = 50;
    private static final String TAG = "ERROR";
    private static final String TABLE_NAME = "search_history";
    private static final String COLLUMN_SEARCH_KEY = "search_key";
    private static final String COLLUMN_SEARCH_HISTORY_ID = BaseColumns._ID;
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "( " + COLLUMN_SEARCH_KEY + ")";
    private static final String SQL_DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String MSG_DELETE_ERR = "Error while trying to delete all history search";
    private static final String MSG_READ_ERR = "Error while trying to get search history from database";
    private static final String MSG_INSERT_ERR = "Error while trying to add search history to database";
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
        db.beginTransaction();
        boolean isAddSuccess = true;
        String err = null;
        try {
            for (SearchHistory searchHistory : searchHistories) {
                ContentValues values = new ContentValues();
                values.put(COLLUMN_SEARCH_KEY, searchHistory.getSearchKey());
                db.insertOrThrow(TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            isAddSuccess = false;
            err = e.getMessage();
            Log.d(TAG, MSG_INSERT_ERR);
        } finally {
            db.endTransaction();
        }
        if (!isAddSuccess) {
            callBack.onFailed(err);
        } else {
            callBack.onSuccess();
        }
    }

    public void getAllSearchHistory(SearchHistoryDataSource.HistorySearchCallback callBack) {
        List<SearchHistory> histories = new ArrayList<>();
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM " + TABLE_NAME + " LIMIT " + LIMIT);
        String errorMessage = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String searchKey = cursor.getString(cursor.getColumnIndex(COLLUMN_SEARCH_KEY));
                    int id = cursor.getInt(cursor.getColumnIndex(COLLUMN_SEARCH_HISTORY_ID));
                    SearchHistory searchHistory = new SearchHistory(id, searchKey);
                    histories.add(searchHistory);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            Log.d(TAG, MSG_READ_ERR);
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
        db.beginTransaction();
        String err = null;
        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            err = e.getMessage();
            Log.d(TAG, MSG_DELETE_ERR);
        } finally {
            db.endTransaction();
            if (err != null) {
                callBack.onFailed(err);
            } else {
                callBack.onSuccess();
            }
        }
    }
}
