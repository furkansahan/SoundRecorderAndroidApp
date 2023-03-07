package com.furkansahan.soundrecorder;

public class RecordingsList {
    private String title, duration, fileUri;

    public RecordingsList(String title, String duration, String fileUri) {
        this.title = title;
        this.duration = duration;
        this.fileUri = fileUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getFileUri() {
        return fileUri;
    }
}
