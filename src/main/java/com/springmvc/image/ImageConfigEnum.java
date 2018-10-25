package com.springmvc.image;

public class ImageConfigEnum {
    private long largeWidth = 500;
    private long largeHeight = 500;
    private long mediumWidth = 200;
    private long mediumHeight = 200;
    private long thumbnailWidth = 50;
    private long thumbnailHeight = 50;

    public long getLargeWidth() {
        return largeWidth;
    }

    public void setLargeWidth(long largeWidth) {
        this.largeWidth = largeWidth;
    }

    public long getLargeHeight() {
        return largeHeight;
    }

    public void setLargeHeight(long largeHeight) {
        this.largeHeight = largeHeight;
    }

    public long getMediumWidth() {
        return mediumWidth;
    }

    public void setMediumWidth(long mediumWidth) {
        this.mediumWidth = mediumWidth;
    }

    public long getMediumHeight() {
        return mediumHeight;
    }

    public void setMediumHeight(long mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public long getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(long thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public long getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(long thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }
}
