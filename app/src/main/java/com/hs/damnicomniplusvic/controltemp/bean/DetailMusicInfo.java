package com.hs.damnicomniplusvic.controltemp.bean;

import java.util.List;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/7/3.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class DetailMusicInfo {
    /**
     * errorCode : 22000
     * data : {"xcode":"17173bd17415d2c22a7250ec29f7e010","songList":[{"queryId":"247911654","songId":247911654,"songName":"夏洛特烦恼","artistId":"5913","artistName":"金志文","albumId":247911669,"albumName":"夏洛特烦恼","songPicSmall":"http://musicdata.baidu.com/data2/pic/247910612/247910612.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/247910608/247910608.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/247910603/247910603.jpg","lrcLink":"/data2/lrc/248241445/248241445.lrc","version":"影视原声","copyType":0,"time":192,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8","showLink":"http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8","format":"mp3","rate":128,"size":3078749,"relateStatus":"0","resourceType":"0","source":"web"}]}
     */

    private int errorCode;
    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * xcode : 17173bd17415d2c22a7250ec29f7e010
         * songList : [{"queryId":"247911654","songId":247911654,"songName":"夏洛特烦恼","artistId":"5913","artistName":"金志文","albumId":247911669,"albumName":"夏洛特烦恼","songPicSmall":"http://musicdata.baidu.com/data2/pic/247910612/247910612.jpg","songPicBig":"http://musicdata.baidu.com/data2/pic/247910608/247910608.jpg","songPicRadio":"http://musicdata.baidu.com/data2/pic/247910603/247910603.jpg","lrcLink":"/data2/lrc/248241445/248241445.lrc","version":"影视原声","copyType":0,"time":192,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8","showLink":"http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8","format":"mp3","rate":128,"size":3078749,"relateStatus":"0","resourceType":"0","source":"web"}]
         */

        private String xcode;
        private List<SongListBean> songList;

        public String getXcode() {
            return xcode;
        }

        public void setXcode(String xcode) {
            this.xcode = xcode;
        }

        public List<SongListBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongListBean> songList) {
            this.songList = songList;
        }

        public static class SongListBean {
            /**
             * queryId : 247911654
             * songId : 247911654
             * songName : 夏洛特烦恼
             * artistId : 5913
             * artistName : 金志文
             * albumId : 247911669
             * albumName : 夏洛特烦恼
             * songPicSmall : http://musicdata.baidu.com/data2/pic/247910612/247910612.jpg
             * songPicBig : http://musicdata.baidu.com/data2/pic/247910608/247910608.jpg
             * songPicRadio : http://musicdata.baidu.com/data2/pic/247910603/247910603.jpg
             * lrcLink : /data2/lrc/248241445/248241445.lrc
             * version : 影视原声
             * copyType : 0
             * time : 192
             * linkCode : 22000
             * songLink : http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8
             * showLink : http://yinyueshiting.baidu.com/data2/music/247912201/247911654108000128.mp3?xcode=17173bd17415d2c221ec55f2ef944ac8
             * format : mp3
             * rate : 128
             * size : 3078749
             * relateStatus : 0
             * resourceType : 0
             * source : web
             */

            private String queryId;
            private int songId;
            private String songName;
            private String artistId;
            private String artistName;
            private int albumId;
            private String albumName;
            private String songPicSmall;
            private String songPicBig;
            private String songPicRadio;
            private String lrcLink;
            private String version;
            private int copyType;
            private int time;
            private int linkCode;
            private String songLink;
            private String showLink;
            private String format;
            private int rate;
            private int size;
            private String relateStatus;
            private String resourceType;
            private String source;

            public String getQueryId() {
                return queryId;
            }

            public void setQueryId(String queryId) {
                this.queryId = queryId;
            }

            public int getSongId() {
                return songId;
            }

            public void setSongId(int songId) {
                this.songId = songId;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getArtistId() {
                return artistId;
            }

            public void setArtistId(String artistId) {
                this.artistId = artistId;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
                this.albumId = albumId;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public String getSongPicSmall() {
                return songPicSmall;
            }

            public void setSongPicSmall(String songPicSmall) {
                this.songPicSmall = songPicSmall;
            }

            public String getSongPicBig() {
                return songPicBig;
            }

            public void setSongPicBig(String songPicBig) {
                this.songPicBig = songPicBig;
            }

            public String getSongPicRadio() {
                return songPicRadio;
            }

            public void setSongPicRadio(String songPicRadio) {
                this.songPicRadio = songPicRadio;
            }

            public String getLrcLink() {
                return lrcLink;
            }

            public void setLrcLink(String lrcLink) {
                this.lrcLink = lrcLink;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public int getCopyType() {
                return copyType;
            }

            public void setCopyType(int copyType) {
                this.copyType = copyType;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getLinkCode() {
                return linkCode;
            }

            public void setLinkCode(int linkCode) {
                this.linkCode = linkCode;
            }

            public String getSongLink() {
                return songLink;
            }

            public void setSongLink(String songLink) {
                this.songLink = songLink;
            }

            public String getShowLink() {
                return showLink;
            }

            public void setShowLink(String showLink) {
                this.showLink = showLink;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getRelateStatus() {
                return relateStatus;
            }

            public void setRelateStatus(String relateStatus) {
                this.relateStatus = relateStatus;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
