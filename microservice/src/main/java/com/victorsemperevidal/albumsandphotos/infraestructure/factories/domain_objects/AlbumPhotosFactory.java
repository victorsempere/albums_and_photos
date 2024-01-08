package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;

public interface AlbumPhotosFactory {

    public Collection<AlbumPhotos> getListOfAlbumPhotosFromProjections(
            Collection<AlbumAndPhotoProjection> albumsAndPhotosProjections);

}
