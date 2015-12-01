package com.yx.yxnote.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yx.yxnote.activity.NoteViewActivity;

/**
 * Created by YX on 2015/12/1.
 */
public class NoteSender {
    private Context context;
    private int position;

    public NoteSender(Context context, int position) {
        this.context = context;
        this.position = position;
    }

    public Intent sendNote() {
        Intent intent = new Intent(context, NoteViewActivity.class);
        SQLiteDatabase db = new NoteDB(context).getWritableDatabase();
        Cursor cursor = db.query(NoteDB.DB_TABLE_NAME, null, null, null, null, null, NoteDB.DB_NOTE_TIME + " DESC", null);
        cursor.moveToPosition(position);
        intent.putExtra(NoteDB.DB_COLUMN_ID, cursor.getInt(cursor.getColumnIndex(NoteDB.DB_COLUMN_ID)));
        intent.putExtra(NoteDB.DB_NOTE_TITLE, cursor.getString(cursor.getColumnIndex(NoteDB.DB_NOTE_TITLE)));
        intent.putExtra(NoteDB.DB_NOTE_CONTENT, cursor.getString(cursor.getColumnIndex(NoteDB.DB_NOTE_CONTENT)));
        intent.putExtra(NoteDB.DB_NOTE_TIME, cursor.getString(cursor.getColumnIndex(NoteDB.DB_NOTE_TIME)));

        return intent;
    }
}
