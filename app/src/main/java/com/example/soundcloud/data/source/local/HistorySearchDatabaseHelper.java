package com.example.soundcloud.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.soundcloud.data.model.History;
import com.example.soundcloud.data.source.SearchHistoryDataSource;

import java.util.ArrayList;
import java.util.List;

public class HistorySearchDatabaseHelper extends SQLiteOpenHelper implements SearchHistoryDataSource.LocalDataSource {
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

    public void addHistories(List<History> searchHistories,
                             SearchHistoryDataSource.CallBack callBack) {
        SQLiteDatabase db = getWritableDatabase();
        boolean isAddSuccess = true;
        Exception exception = null;
        try {
            ContentValues values = new ContentValues();
            for (History history : searchHistories) {
                values.put(COLLUMN_SEARCH_KEY, history.getSearchKey());
                db.insertOrThrow(TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            isAddSuccess = false;
            exception = e;
            e.printStackTrace();
        }
        if (!isAddSuccess) {
            callBack.onFailed(exception);
        } else {
            callBack.onSuccess();
        }
    }

    public void selectHistories(String limit, SearchHistoryDataSource.HistorySearchCallback callBack) {
        List<History> histories = new ArrayList<>();
        Exception exception = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, limit);
        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {
                String searchKey = cursor.getString(cursor.getColumnIndex(COLLUMN_SEARCH_KEY));
                History history = new History(searchKey);
                histories.add(history);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            exception = e;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (exception != null) {
                callBack.onFailed(exception);
            } else {
                callBack.onSuccess(histories);
            }
        }
    }

    public void deleteHistories(SearchHistoryDataSource.CallBack callBack) {
        SQLiteDatabase db = getWritableDatabase();
        Exception exception = null;
        try {
            db.delete(TABLE_NAME, null, null);
        } catch (Exception e) {
            exception = e;
        } finally {
            if (exception != null) {
                callBack.onFailed(exception);
            } else {
                callBack.onSuccess();
            }
        }
    }

    @Override
    public void getHistories(String limit, SearchHistoryDataSource.HistorySearchCallback callBack) {
        selectHistories(limit, callBack);
    }

    @Override
    public void saveHistories(List<History> searchHistories, SearchHistoryDataSource.CallBack callBack) {
        addHistories(searchHistories, callBack);
    }

    @Override
    public void clearHistories(SearchHistoryDataSource.CallBack callBack) {
        deleteHistories(callBack);
    }
}
