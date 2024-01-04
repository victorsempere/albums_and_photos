package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.AlbumDao;

@Service
public class AlbumFactory {
    public AlbumFactory() {
        super();
    }

    public Album getInstance(
            com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album album) {
        return new Album(album.getUserId(), album.getId(), album.getTitle());
    }

    public Collection<Album> getInstancesFromAlbumsApi(
            Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album> apiAlbums) {
        Collection<Album> albums = new ArrayList<>();

        if (apiAlbums != null) {
            for (com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album apiAlbum : apiAlbums) {
                albums.add(getInstance(apiAlbum));
            }
        }

        return albums;
    }

    public Collection<Album> getInstancesFromAlbumsDao(Collection<AlbumDao> listOfAlbumsDaos) {
        Collection<Album> albums = new ArrayList<>();
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

    public Album getInstance(AlbumAndPhotoProjection albumProjection) {
        return new Album(albumProjection.getUserId(), albumProjection.getAlbumId(), albumProjection.getAlbumTitle());
    }
}
