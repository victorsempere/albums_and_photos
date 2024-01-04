package com.victorsemperevidal.albumsandfotos.domain.repos;

import java.util.Collection;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;

public interface AlbumRepository {
    public void deleteAll();

    public void saveAll(Collection<Album> entities);

    public Collection<Album> findAll();

    public Collection<AlbumAndPhotoProjection> getAlbumsAndPhotos();
}
