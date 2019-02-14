package com.example.soundcloud.data.source.remote;

import com.example.soundcloud.data.model.DataHelper.SoundCloud;
import com.example.soundcloud.data.model.Song;
import com.example.soundcloud.data.model.SongType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SongLoaderUtils {
    private static final String UNKNOWN = "Unknown";

    static String getJSONFromAPI(String urlString) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(urlString);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(SoundCloud.METHOD_GET);
        httpURLConnection.setConnectTimeout(SoundCloud.CONNECTION_TIMEOUT);
        httpURLConnection.setReadTimeout(SoundCloud.READ_TIMEOUT);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        String jsonString = stringBuilder.toString();
        httpURLConnection.disconnect();
        return jsonString;
    }

    public static List<Song> getSongFromJSONString(String jsonString) throws JSONException {
        List<Song> songs = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray songsCollection = root.getJSONArray(Song.SongJSonKey.COLLECTION);
        for (int i = 0; i < songsCollection.length(); i++) {
            JSONObject jsonObject = songsCollection.getJSONObject(i)
                    .getJSONObject(Song.SongJSonKey.TRACK);
            Song song = new Song(jsonObject);
            song.setSongType(SongType.TYPE_ONLINE);
            songs.add(song);
        }
        return songs;
    }

    public static String getPublisherMetadataItem(JSONObject jsonObject, String key) {
        try {
            String string = jsonObject.getJSONObject(Song.SongJSonKey.PUBLISHER_METADATA)
                    .getString(key);
            return string;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return UNKNOWN;
    }
}
