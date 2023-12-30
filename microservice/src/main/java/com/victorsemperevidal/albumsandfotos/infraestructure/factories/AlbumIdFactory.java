package com.victorsemperevidal.albumsandfotos.infraestructure.factories;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.AlbumDao;

@Service
public class AlbumIdFactory {
    public AlbumIdFactory() {
        super();
    }

    public AlbumId getInstance(AlbumDao albumDao) {
        return new AlbumId(albumDao.getId());
    }

    public AlbumId getInstance(Photo photo) {
        return new AlbumId(photo.getAlbumId());
    }

    public AlbumId getInstance(Album album) {
        return new AlbumId(album.getId());
    }
}
