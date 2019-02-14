package com.example.soundcloud.data.model;

import android.support.annotation.StringDef;
import static com.example.soundcloud.data.model.SongType.TYPE_ONLINE;
import static com.example.soundcloud.data.model.SongType.TYPE_LOCAL;

@StringDef({TYPE_ONLINE, TYPE_LOCAL})
public @interface SongType {
    String TYPE_ONLINE = "online";
    String TYPE_LOCAL = "local";
}
