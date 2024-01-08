package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;

public interface AlbumIdFactory {

    public AlbumId getInstance(AlbumDao albumDao);

    public AlbumId getInstance(Photo photo);

    public AlbumId getInstance(Album album);
}
