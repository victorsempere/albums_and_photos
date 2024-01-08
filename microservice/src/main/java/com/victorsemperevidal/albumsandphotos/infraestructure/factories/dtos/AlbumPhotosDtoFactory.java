package com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.AlbumPhotosDto;

public interface AlbumPhotosDtoFactory {

    public Collection<AlbumPhotosDto> getListFromAlbumPhotos(Collection<AlbumPhotos> albumPhotos);    
}
