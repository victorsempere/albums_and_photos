package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("memoryPhotoRepositoryTreeSet")
public class MemoryPhotoRepositoryTreeSet extends MemoryPhotoRepository {

    @Autowired
    public MemoryPhotoRepositoryTreeSet(@Qualifier("treeSetCollectionService") CollectionService collectionService) {
        super(collectionService);
    }

}