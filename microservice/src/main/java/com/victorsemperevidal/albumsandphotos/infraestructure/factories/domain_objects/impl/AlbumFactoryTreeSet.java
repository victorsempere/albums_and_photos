package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("albumFactoryTreeSet")
public class AlbumFactoryTreeSet extends AlbumFactoryBase {
    @Autowired
    public AlbumFactoryTreeSet(@Qualifier("treeSetCollectionService") CollectionService collectionService) {
        super(collectionService);
    }
}
