package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

public class AlbumFactoryBase implements AlbumFactory {
    private CollectionService collectionService;

    protected AlbumFactoryBase(CollectionService collectionService) {
        super();
        this.collectionService = collectionService;
    }

    @Override
    public Album getInstance(
            com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album album) {
        return new Album(album.getUserId(), album.getId(), album.getTitle());
    }

    @Override
    public Collection<Album> getInstancesFromAlbumsApi(
            Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album> apiAlbums) {
        Collection<Album> albums = collectionService.getInstance();

        if (apiAlbums != null) {
            for (com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album apiAlbum : apiAlbums) {
                albums.add(getInstance(apiAlbum));
            }
        }

        return albums;
    }

    @Override
    public Collection<Album> getInstancesFromAlbumsDao(Collection<AlbumDao> listOfAlbumsDaos) {
        Collection<Album> albums = collectionService.getInstance();
        if (listOfAlbumsDaos != null) {
            for (AlbumDao albumDao : listOfAlbumsDaos) {
                albums.add(getInstance(albumDao));
            }
        }
        return albums;
    }

    private Album getInstance(AlbumDao albumDao) {
        return new Album(albumDao.getUserId(), albumDao.getId(), albumDao.getTitle());
    }

    @Override
    public Album getInstance(AlbumAndPhotoProjection albumProjection) {
        return new Album(albumProjection.getUserId(), albumProjection.getAlbumId(), albumProjection.getAlbumTitle());
    }

}
