package com.victorsemperevidal.albumsandfotos.infraestructure.factories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.projections.AlbumAndPhotoProjection;
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

    public List<Album> getInstancesFromAlbumsApi(
            List<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album> apiAlbums) {
        List<Album> albums = new ArrayList<>();

        if (apiAlbums != null) {
            for (com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album apiAlbum : apiAlbums) {
                albums.add(getInstance(apiAlbum));
            }
        }

        return albums;
    }

    public List<Album> getInstancesFromAlbumsDao(List<AlbumDao> listOfAlbumsDaos) {
        List<Album> albums = new ArrayList<>();
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
