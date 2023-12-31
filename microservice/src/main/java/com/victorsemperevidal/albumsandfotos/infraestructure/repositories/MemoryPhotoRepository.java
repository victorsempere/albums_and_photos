package com.victorsemperevidal.albumsandfotos.infraestructure.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;

@Service
@Qualifier("memoryPhotoRepository")
public class MemoryPhotoRepository implements PhotoRepository {
    private List<Photo> listOfPhotos;

    @Autowired
    public MemoryPhotoRepository() {
        super();
    }

    @Override
    public void saveAll(List<Photo> entities) {
        listOfPhotos = entities == null ? null : List.copyOf(entities);
    }

    @Override
    public List<Photo> findAll() {
        return listOfPhotos;
    }

    @Override
    public void deleteAll() {
        this.listOfPhotos = null;
    }
}
