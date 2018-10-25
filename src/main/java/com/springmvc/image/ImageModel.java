package com.springmvc.image;

public class ImageModel {
    private String largePath;
    private String largePathRequest;
    private String mediumPath;
    private String mediumPathRequest;
    private String sourcePath;
    private String sourcePathRequest;
    private String thumbnailPath;
    private String thumbnailPathRequest;

    public String getLargePath() {
        return largePath;
    }

    public void setLargePath(String largePath) {
        this.largePath = largePath;
    }

    public String getLargePathRequest() {
        return largePathRequest;
    }

    public void setLargePathRequest(String largePathRequest) {
        this.largePathRequest = largePathRequest;
    }

    public String getMediumPath() {
        return mediumPath;
    }

    public void setMediumPath(String mediumPath) {
        this.mediumPath = mediumPath;
    }

    public String getMediumPathRequest() {
        return mediumPathRequest;
    }

    public void setMediumPathRequest(String mediumPathRequest) {
        this.mediumPathRequest = mediumPathRequest;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourcePathRequest() {
        return sourcePathRequest;
    }

    public void setSourcePathRequest(String sourcePathRequest) {
        this.sourcePathRequest = sourcePathRequest;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getThumbnailPathRequest() {
        return thumbnailPathRequest;
    }

    public void setThumbnailPathRequest(String thumbnailPathRequest) {
        this.thumbnailPathRequest = thumbnailPathRequest;
    }
}
