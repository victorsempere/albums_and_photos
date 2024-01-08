package com.victorsemperevidal.albumsandphotos.domain.repos;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;

public interface AlbumRepository {
    public void deleteAll();

    public void saveAll(Collection<Album> entities);

    public Collection<Album> findAll();

    public Collection<AlbumAndPhotoProjection> getAlbumsAndPhotos();
}
