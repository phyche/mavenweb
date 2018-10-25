package com.springmvc.image;

public class ImageGroup {
    private long largeWidth;
    private long largeHeight;
    private long mediumWidth;
    private long mediumHeight;
    private long thumbnailWidth;
    private long thumbnailHeight;
    private long sourceWidth;
    private long sourceHeight;

    private String sourceUrl;
    private String largeUrl;
    private String mediumUrl;
    private String thumbnailUrl;


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

    public long getSourceWidth() {
        return sourceWidth;
    }

    public void setSourceWidth(long sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public long getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(long sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
