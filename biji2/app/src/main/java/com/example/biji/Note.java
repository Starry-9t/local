package com.example.biji;

public class Note {
    private long id;//每个笔记的数字id，自增长
    private String content;
    private String time;
    private int tag;//类别

    public Note(){

    }

    public Note(String content, String time, int tag){
        this.content = content;
        this.time = time;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return content + "\n" + time.substring(5,16) + " " + id;
    }

}
