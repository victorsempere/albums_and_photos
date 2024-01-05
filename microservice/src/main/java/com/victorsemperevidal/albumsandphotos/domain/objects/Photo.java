package com.victorsemperevidal.albumsandphotos.domain.objects;

import java.io.Serializable;

public class Photo implements Serializable, Comparable<Photo> {
    private final Long albumId;
    private final Long id;
    private final String title;
    private final String url;
    private final String thumbnailUrl;

    public Photo(Long albumId, Long id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Photo other = (Photo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Photo [albumId=" + albumId + ", id=" + id + ", title=" + title + ", url=" + url + ", thumbnailUrl="
                + thumbnailUrl + "]";
    }

    @Override
    public int compareTo(Photo o) {
        return getId().compareTo(o.getId());
    }

}