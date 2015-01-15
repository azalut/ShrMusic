package com.shrmusic.entity;

/**
 * a class representing file (as a byte array with the filename) downloaded from dropbox
 */
public class DownloadedFile {
    private String filename;
    private byte[] fileBytes;

    public DownloadedFile(String filename, byte[] fileBytes) {
        this.filename = filename;
        this.fileBytes = fileBytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return filename;
    }
}
