package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("photoFactoryTreeSet")
public class PhotoFactoryTreeSet extends PhotoFactoryBase {
    @Autowired
    protected PhotoFactoryTreeSet(@Qualifier("treeSetCollectionService") CollectionService collectionService) {
        super(collectionService);
    }
}
