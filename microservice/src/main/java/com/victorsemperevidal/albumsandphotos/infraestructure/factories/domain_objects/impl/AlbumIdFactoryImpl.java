package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumIdFactory;

@Service
public class AlbumIdFactoryImpl implements AlbumIdFactory {
    public AlbumIdFactoryImpl() {
        super();
    }

    @Override
    public AlbumId getInstance(AlbumDao albumDao) {
        return new AlbumId(albumDao.getId());
    }

    @Override
    public AlbumId getInstance(Photo photo) {
        return new AlbumId(photo.getAlbumId());
    }

    @Override
    public AlbumId getInstance(Album album) {
        return new AlbumId(album.getId());
    }
}
