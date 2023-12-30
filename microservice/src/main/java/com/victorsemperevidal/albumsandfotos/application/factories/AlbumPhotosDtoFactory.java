package com.victorsemperevidal.albumsandfotos.application.factories;

import java.util.List;

import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;

public interface AlbumPhotosDtoFactory {

    public List<AlbumPhotosDto> getListFromAlbumPhotos(List<AlbumPhotos> albumPhotos);    
}
