package com.victorsemperevidal.albumsandfotos.application.dtos;

import java.util.ArrayList;
import java.util.List;

public class AlbumPhotosDto {
    private final AlbumDto album;
    private final List<PhotoDto> photos;

    public AlbumPhotosDto(AlbumDto album) {
        super();
        this.album = album;
        this.photos = new ArrayList<>();
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
}
