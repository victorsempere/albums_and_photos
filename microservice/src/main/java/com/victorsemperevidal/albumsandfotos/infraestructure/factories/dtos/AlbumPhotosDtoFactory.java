package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos;

import java.util.List;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.AlbumPhotosDto;

public interface AlbumPhotosDtoFactory {

    public List<AlbumPhotosDto> getListFromAlbumPhotos(List<AlbumPhotos> albumPhotos);    
}
