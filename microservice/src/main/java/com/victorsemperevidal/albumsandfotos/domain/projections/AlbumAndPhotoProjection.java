package com.victorsemperevidal.albumsandfotos.domain.projections;

public class AlbumAndPhotoProjection {
    private final Long albumId;
    private final Long userId;
    private final String albumTitle;
    private final Long photoId;
    private final String photoTitle;
    private final String photoUrl;
    private final String photoThumbnailUrl;

    public AlbumAndPhotoProjection(Long albumId, Long userId, String albumTitle, Long photoId, String photoTitle,
            String photoUrl, String photoThumbnailUrl) {
        this.albumId = albumId;
        this.userId = userId;
        this.albumTitle = albumTitle;
        this.photoId = photoId;
        this.photoTitle = photoTitle;
        this.photoUrl = photoUrl;
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getPhotoThumbnailUrl() {
        return photoThumbnailUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
        result = prime * result + ((photoId == null) ? 0 : photoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlbumAndPhotoProjection other = (AlbumAndPhotoProjection) obj;
        if (albumId == null) {
            if (other.albumId != null)
                return false;
        } else if (!albumId.equals(other.albumId))
            return false;
        if (photoId == null) {
            if (other.photoId != null)
                return false;
        } else if (!photoId.equals(other.photoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AlbumAndPhotoProjection [albumId=" + albumId + ", userId=" + userId + ", albumTitle=" + albumTitle
                + ", photoId=" + photoId + ", photoTitle=" + photoTitle + ", photoUrl=" + photoUrl
                + ", photoThumbnailUrl=" + photoThumbnailUrl + "]";
    }

}
