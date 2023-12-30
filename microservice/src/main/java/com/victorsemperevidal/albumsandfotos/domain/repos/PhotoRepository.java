package com.victorsemperevidal.albumsandfotos.domain.repos;

import java.util.List;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

public interface PhotoRepository {
    public void saveAll(List<Photo> entities);
    public List<Photo> findAll();
}
