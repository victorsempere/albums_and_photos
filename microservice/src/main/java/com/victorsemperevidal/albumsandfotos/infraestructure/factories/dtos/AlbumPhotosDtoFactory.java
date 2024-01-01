package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos;

import java.util.Collection;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.AlbumPhotosDto;

public interface AlbumPhotosDtoFactory {

    public Collection<AlbumPhotosDto> getListFromAlbumPhotos(Collection<AlbumPhotos> albumPhotos);    
}
