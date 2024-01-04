package com.victorsemperevidal.albumsandfotos.domain.objects;

import java.io.Serializable;
import java.util.Collection;

public class ExternalData implements Serializable {
    private final Collection<Album> albums;
    private final Collection<Photo> photos;

    public ExternalData(Collection<Album> albums, Collection<Photo> photos) {
        this.albums = albums;
        this.photos = photos;
    }

    public Collection<Album> getAlbums() {
        return albums;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

}
