package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("memoryPhotoRepositoryArrayList")
public class MemoryPhotoRepositoryArrayList extends MemoryPhotoRepository {

    @Autowired
    public MemoryPhotoRepositoryArrayList(
            @Qualifier("arrayListCollectionService") CollectionService collectionService) {
        super(collectionService);
    }
}
