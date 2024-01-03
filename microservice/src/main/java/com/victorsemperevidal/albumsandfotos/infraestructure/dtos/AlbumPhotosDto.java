package com.victorsemperevidal.albumsandfotos.infraestructure.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumPhotosDto implements Serializable {
    private final AlbumDto album;
    private final List<PhotoDto> photos;

    public AlbumPhotosDto(AlbumDto album) {
        this(album, new ArrayList<>());
    }

    @JsonCreator
    public AlbumPhotosDto(@JsonProperty("album") AlbumDto album, @JsonProperty("photos") List<PhotoDto> photos) {
        super();
        this.album = album;
        this.photos = photos;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public AlbumPhotosDto addPhto(PhotoDto photoDto) {
        this.photos.add(photoDto);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album == null) ? 0 : album.hashCode());
        result = prime * result + ((photos == null) ? 0 : photos.hashCode());
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
        AlbumPhotosDto other = (AlbumPhotosDto) obj;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        if (photos == null) {
            if (other.photos != null)
                return false;
        } else if (!photos.equals(other.photos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AlbumPhotosDto [album=" + album + ", photos=" + photos + "]";
    }

}
