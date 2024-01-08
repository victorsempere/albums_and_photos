package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.PhotoDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

class PhotoFactoryBase implements PhotoFactory {
    private CollectionService collectionService;

    protected PhotoFactoryBase(CollectionService collectionService) {
        super();
        this.collectionService = collectionService;
    }

    public Photo getInstance(
            com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo photo) {
        return new Photo(photo.getAlbumId(), photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

    @Override
    public Collection<Photo> getInstancesFromPhotosApi(
            Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo> apiPhotos) {
        Collection<Photo> photos = collectionService.getInstance();

        if (apiPhotos != null) {
            for (com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo apiAlbum : apiPhotos) {
                photos.add(getInstance(apiAlbum));
            }
        }

        return photos;
    }

    @Override
    public Collection<Photo> getInstancesFromPhotoDaos(Collection<PhotoDao> photoDaos) {
        if (photoDaos == null) {
            return List.of();
        }
        Collection<Photo> photos = new ArrayList<>();
        for (PhotoDao dao : photoDaos) {
            photos.add(getInstance(dao));
        }

        return photos;
    }

    private Photo getInstance(PhotoDao photoDao) {
        return new Photo(photoDao.getAlbumId(), photoDao.getId(), photoDao.getTitle(), photoDao.getUrl(),
                photoDao.getThumbnailUrl());
    }

    @Override
    public Photo getInstance(AlbumAndPhotoProjection albumProjection) {
        if (albumProjection.getPhotoId() == null) {
            return null;

        } else {
            return new Photo(albumProjection.getAlbumId(), albumProjection.getPhotoId(),
                    albumProjection.getPhotoTitle(),
                    albumProjection.getPhotoUrl(),
                    albumProjection.getPhotoThumbnailUrl());
        }
    }

}
