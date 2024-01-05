package com.victorsemperevidal.albumsandphotos.domain.repos;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;

public interface PhotoRepository {
    public void deleteAll();

    public void saveAll(Collection<Photo> entities);

    public Collection<Photo> findAll();
}
