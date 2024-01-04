package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.PhotoDao;

@Service
public class PhotoFactory {
    public PhotoFactory() {
        super();
    }

    public Photo getInstance(
            com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo photo) {
        return new Photo(photo.getAlbumId(), photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

    public Collection<Photo> getInstancesFromPhotosApi(
            Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo> apiPhotos) {
        Collection<Photo> photos = new ArrayList<>();

        if (apiPhotos != null) {
            for (com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo apiAlbum : apiPhotos) {
                photos.add(getInstance(apiAlbum));
            }
        }

        return photos;
    }

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
