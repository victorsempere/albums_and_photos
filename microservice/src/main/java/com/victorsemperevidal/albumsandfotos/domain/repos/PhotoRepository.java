package com.victorsemperevidal.albumsandfotos.domain.repos;

import java.util.Collection;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

public interface PhotoRepository {
    public void deleteAll();

    public void saveAll(Collection<Photo> entities);

    public Collection<Photo> findAll();
}
