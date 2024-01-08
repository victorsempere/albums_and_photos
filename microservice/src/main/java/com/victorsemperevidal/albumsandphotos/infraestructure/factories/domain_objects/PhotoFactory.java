package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.PhotoDao;

public interface PhotoFactory {
    public Photo getInstance(
            com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo photo);

    public Collection<Photo> getInstancesFromPhotosApi(
            Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo> apiPhotos);

    public Collection<Photo> getInstancesFromPhotoDaos(Collection<PhotoDao> photoDaos);

    public Photo getInstance(AlbumAndPhotoProjection albumProjection);
}
