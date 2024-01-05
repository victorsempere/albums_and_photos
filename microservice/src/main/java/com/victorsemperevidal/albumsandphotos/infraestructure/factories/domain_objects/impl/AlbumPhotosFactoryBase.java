package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

class AlbumPhotosFactoryBase implements AlbumPhotosFactory {

    private AlbumFactory albumFactory;
    private PhotoFactory photoFactory;
    private CollectionService collectionService;

    protected AlbumPhotosFactoryBase(CollectionService collectionService, AlbumFactory albumFactory,
            PhotoFactory photoFactory) {
        super();
        this.collectionService = collectionService;
        this.albumFactory = albumFactory;
        this.photoFactory = photoFactory;
    }

    @Override
    public Collection<AlbumPhotos> getListOfAlbumPhotosFromProjections(
            Collection<AlbumAndPhotoProjection> albumsAndPhotosProjections) {
        if (albumsAndPhotosProjections == null) {
            return collectionService.getInstance();
        }

        AlbumPhotos lastAlbum = null;
        Collection<AlbumPhotos> listOfAlbumsWithPhotos = collectionService.getInstance();
        for (AlbumAndPhotoProjection albumProjection : albumsAndPhotosProjections) {
            lastAlbum = appendProjectionToAlbumPhotos(albumProjection, listOfAlbumsWithPhotos, lastAlbum);
        }
        return listOfAlbumsWithPhotos;
    }

    private AlbumPhotos appendProjectionToAlbumPhotos(AlbumAndPhotoProjection albumProjection,
            Collection<AlbumPhotos> albumsPhotos, AlbumPhotos lastAlbum) {
        if (isDifferentAlbum(lastAlbum, albumProjection)) {
            lastAlbum = getInstance(albumProjection);
            albumsPhotos.add(lastAlbum);
        }

        Photo photo = photoFactory.getInstance(albumProjection);
        if (photo != null) {
            lastAlbum.getPhotos().add(photo);
        }

        return lastAlbum;
    }

    private boolean isDifferentAlbum(AlbumPhotos lastAlbum, AlbumAndPhotoProjection albumProjection) {
        return lastAlbum == null || !lastAlbum.getAlbum().getId().equals(albumProjection.getAlbumId());
    }

    private AlbumPhotos getInstance(AlbumAndPhotoProjection albumProjection) {
        return new AlbumPhotos(albumFactory.getInstance(albumProjection), new ArrayList<>());
    }

}
