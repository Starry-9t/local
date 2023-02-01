package com.example.biji;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;//初始化定义创建数据库
import android.database.sqlite.SQLiteOpenHelper;//打开已有数据库

import java.util.ArrayList;
import java.util.List;
//增加、删除、改动、查找功能
/**把note传入数据库 》 适配器适配 》 传到listview**/
public class CRUD {
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    private static final String[] columns = {
            NoteDatabase.ID,
            NoteDatabase.CONTENT,
            NoteDatabase.TIME,
            NoteDatabase.MODE
    };
    public CRUD(Context context) {
        dbHandler = new NoteDatabase(context);
    }
    //打开数据库处理器
    public void open(){
        db = dbHandler.getWritableDatabase();
    }
    //关闭数据库处理器
    public void close(){
        dbHandler.close();
    }



    //把note 加入到数据库里面
    public Note addNote(Note note){
        //add a note object to database
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDatabase.CONTENT, note.getContent());
        contentValues.put(NoteDatabase.TIME, note.getTime());
        contentValues.put(NoteDatabase.MODE, note.getTag());
        long insertId = db.insert(NoteDatabase.TABLE_NAME, null, contentValues);
        note.setId(insertId);
        return note;
    }

    public Note getNote(long id){
        //使用指针从数据库获取note
        //定义一个指针
        Cursor cursor = db.query(NoteDatabase.TABLE_NAME, columns, NoteDatabase.ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Note e = new Note(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        return e;
    }
        //获取所有的note
    @SuppressLint("Range")
    public List<Note> getAllNotes(){
        Cursor cursor = db.query(NoteDatabase.TABLE_NAME, columns, null, null, null, null, null);

        List<Note> notes = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndex(NoteDatabase.ID)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NoteDatabase.CONTENT)));
                note.setTime(cursor.getString(cursor.getColumnIndex(NoteDatabase.TIME)));
                note.setTag(cursor.getInt(cursor.getColumnIndex(NoteDatabase.MODE)));
                notes.add(note);
            }
        }
        return notes;
    }

    public int updateNote(Note note) {
        //更新
        ContentValues values = new ContentValues();
        values.put(NoteDatabase.CONTENT, note.getContent());
        values.put(NoteDatabase.TIME, note.getTime());
        values.put(NoteDatabase.MODE, note.getTag());
        //updating row
        return db.update(NoteDatabase.TABLE_NAME, values,
                NoteDatabase.ID + "=?", new String[] { String.valueOf(note.getId())});
    }

    public void removeNote(Note note){
        //根据id删除一条note
        db.delete(NoteDatabase.TABLE_NAME, NoteDatabase.ID + "=" + note.getId(), null);
    }

}
