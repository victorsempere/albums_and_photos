package com.victorsemperevidal.albumsandfotos.domain.objects;

import java.io.Serializable;
import java.util.List;

public class AlbumPhotos implements Serializable {
    private final Album album;
    private final List<Photo> photos;

    public AlbumPhotos(Album album, List<Photo> photos) {
        this.album = album;
        this.photos = photos;
    }

    public Album getAlbum() {
        return album;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album == null) ? 0 : album.hashCode());
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
        AlbumPhotos other = (AlbumPhotos) obj;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AlbumPhotos [album=" + album + ", photos=" + photos + "]";
    }
}
