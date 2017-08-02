package com.hs.damnicomniplusvic.controltemp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/4.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class SongBean implements Parcelable{

    private String fileName;
    private String title;
    private int duration;
    private String singer;
    private String album;
    private String year;
    private String type;
    private String size;
    private String fileUrl;

    protected SongBean(Parcel in) {
        fileName = in.readString();
        title = in.readString();
        duration = in.readInt();
        singer = in.readString();
        album = in.readString();
        year = in.readString();
        type = in.readString();
        size = in.readString();
        fileUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(title);
        dest.writeInt(duration);
        dest.writeString(singer);
        dest.writeString(album);
        dest.writeString(year);
        dest.writeString(type);
        dest.writeString(size);
        dest.writeString(fileUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SongBean> CREATOR = new Creator<SongBean>() {
        @Override
        public SongBean createFromParcel(Parcel in) {
            return new SongBean(in);
        }

        @Override
        public SongBean[] newArray(int size) {
            return new SongBean[size];
        }
    };

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public SongBean() {
        super();
    }

    public SongBean(String fileName, String title, int duration, String singer,
                String album, String year, String type, String size, String fileUrl) {
        super();
        this.fileName = fileName;
        this.title = title;
        this.duration = duration;
        this.singer = singer;
        this.album = album;
        this.year = year;
        this.type = type;
        this.size = size;
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Song [fileName=" + fileName + ", title=" + title
                + ", duration=" + duration + ", singer=" + singer + ", album="
                + album + ", year=" + year + ", type=" + type + ", size="
                + size + ", fileUrl=" + fileUrl + "]";
    }

}
