package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.memory;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

class MemoryPhotoRepository implements PhotoRepository {
    private Collection<Photo> listOfPhotos;
    private CollectionService collectionService;

    public MemoryPhotoRepository(CollectionService collectionService) {
        super();
        this.collectionService = collectionService;
    }

    @Override
    public void saveAll(Collection<Photo> entities) {
        listOfPhotos = entities == null ? collectionService.getInstance() : collectionService.copyOf(entities);
    }

    @Override
    public Collection<Photo> findAll() {
        return listOfPhotos;
    }

    @Override
    public void deleteAll() {
        this.listOfPhotos = collectionService.getInstance();
    }
}
