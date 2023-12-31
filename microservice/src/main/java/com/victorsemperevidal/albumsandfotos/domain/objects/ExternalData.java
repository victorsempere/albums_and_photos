package com.victorsemperevidal.albumsandfotos.domain.objects;

import java.io.Serializable;
import java.util.List;

public class ExternalData implements Serializable {
    private final List<Album> albums;
    private final List<Photo> photos;

    public ExternalData(List<Album> albums, List<Photo> photos) {
        this.albums = albums;
        this.photos = photos;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

}
