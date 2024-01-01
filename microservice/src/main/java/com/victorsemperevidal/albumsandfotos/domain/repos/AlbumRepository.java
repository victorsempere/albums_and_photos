package com.victorsemperevidal.albumsandfotos.domain.repos;

import java.util.Collection;
import java.util.List;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;

public interface AlbumRepository {
    public void deleteAll();

    public void saveAll(List<Album> entities);

    public Collection<Album> findAll();

    public Collection<AlbumAndPhotoProjection> getAlbumsAndPhotos();
}
