package com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumIdFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.projections.AlbumAndPhotoProjectionDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.albums_map_service.AlbumsMapService;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

public class AlbumAndPhotoProjectionFactoryBase implements AlbumAndPhotoProjectionFactory {
    private AlbumIdFactory albumIdFactory;
    private CollectionService collectionService;
    private AlbumsMapService albumsMapService;

    protected AlbumAndPhotoProjectionFactoryBase(AlbumsMapService albumsMapService, CollectionService collectionService, AlbumIdFactory albumIdFactory) {
        super();
        this.albumsMapService = albumsMapService;
        this.collectionService = collectionService;
        this.albumIdFactory = albumIdFactory;
    }

    @Override
    public Collection<AlbumAndPhotoProjection> getInstancesFromListOfDaos(
            Collection<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos) {
        Collection<AlbumAndPhotoProjection> albumsAndPhotos = collectionService.getInstance();
        if (albumsAndPhotosDaos != null) {
            for (AlbumAndPhotoProjectionDao dao : albumsAndPhotosDaos) {
                albumsAndPhotos.add(getInstanceFromDao(dao));
            }
        }
        return albumsAndPhotos;
    }

    private AlbumAndPhotoProjection getInstanceFromDao(AlbumAndPhotoProjectionDao albumAndPhotosDao) {
        return new AlbumAndPhotoProjection(albumAndPhotosDao.getAlbumId(),
                albumAndPhotosDao.getUserId(), albumAndPhotosDao.getAlbumTitle(), albumAndPhotosDao.getPhotoId(),
                albumAndPhotosDao.getPhotoTitle(),
                albumAndPhotosDao.getPhotoUrl(), albumAndPhotosDao.getPhotoThumbnailUrl());
    }

    @Override
    public Collection<AlbumAndPhotoProjection> getInstancesFromListOfAlbumsAndPhotos(Collection<Album> listOfAlbums,
            Collection<Photo> listOfPhotos) {
        Map<AlbumId, Collection<Photo>> photosPerAlbum = buildMapOfPhotosPerAlbum(listOfPhotos);
        return getInstancesFromListOfAlbumsAndPhotosPerAlbum(listOfAlbums, photosPerAlbum);
    }

    private Collection<AlbumAndPhotoProjection> getInstancesFromListOfAlbumsAndPhotosPerAlbum(
            Collection<Album> listOfAlbums,
            Map<AlbumId, Collection<Photo>> photosPerAlbum) {
        if (listOfAlbums == null) {
            return collectionService.getInstance();
        }
        Collection<AlbumAndPhotoProjection> projections = collectionService.getInstance();
        for (Album album : listOfAlbums) {
            AlbumId albumId = albumIdFactory.getInstance(album);
            Collection<AlbumAndPhotoProjection> albumPhotos = getInstancesFromAlbumAndPhotos(album,
                    photosPerAlbum == null ? null : photosPerAlbum.get(albumId));
            projections.addAll(albumPhotos);
        }
        return projections;
    }

    private Collection<AlbumAndPhotoProjection> getInstancesFromAlbumAndPhotos(Album album,
            Collection<Photo> listOfPhotos) {
        if (album == null) {
            return collectionService.getInstance();
        }

        Collection<AlbumAndPhotoProjection> albums = collectionService.getInstance();
        if (listOfPhotos == null) {
            albums.add(getInstance(album));

        } else {
            for (Photo photo : listOfPhotos) {
                albums.add(getInstance(album, photo));
            }
        }
        return albums;
    }

    private AlbumAndPhotoProjection getInstance(Album album, Photo photo) {
        return new AlbumAndPhotoProjection(album.getId(), album.getUserId(),
                album.getTitle(), photo == null ? null : photo.getId(), photo == null ? null : photo.getTitle(),
                photo == null ? null : photo.getUrl(), photo == null ? null : photo.getThumbnailUrl());
    }

    private AlbumAndPhotoProjection getInstance(Album album) {
        return getInstance(album, null);
    }

    private Map<AlbumId, Collection<Photo>> buildMapOfPhotosPerAlbum(Collection<Photo> listOfPhotos) {
        if (listOfPhotos == null) {
            return albumsMapService.getInstance();
        }
        Map<AlbumId, Collection<Photo>> mapOfPhotosPerAlbum = albumsMapService.getInstance();
        for (Photo photo : listOfPhotos) {
            AlbumId albumId = albumIdFactory.getInstance(photo);
            mapOfPhotosPerAlbum.computeIfAbsent(albumId, this::createAlbumListOfPhotos);
            mapOfPhotosPerAlbum.merge(albumId, Arrays.asList(photo), this::appendPhotoToAlbum);
        }
        return mapOfPhotosPerAlbum;
    }

    private Collection<Photo> appendPhotoToAlbum(Collection<Photo> photos, Collection<Photo> newPhotos) {
        photos.addAll(newPhotos);
        return photos;
    }

    private Collection<Photo> createAlbumListOfPhotos(AlbumId key) {
        return collectionService.getInstance();
    }
}
