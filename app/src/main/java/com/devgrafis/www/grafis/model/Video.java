package com.devgrafis.www.grafis.model;

public class Video {
    String titleVideo, subtitleVideo;
    public Video(String titleVideo, String subtitleVideo){
        this.titleVideo = titleVideo;
        this.subtitleVideo = subtitleVideo;
    }

    public String getTitleVideo() {
        return titleVideo;
    }

    public String getSubtitleVideo() {
        return subtitleVideo;
    }
}
