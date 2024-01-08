package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("photoFactoryArrayList")
public class PhotoFactoryArrayList extends PhotoFactoryBase {
    @Autowired
    public PhotoFactoryArrayList(@Qualifier("arrayListCollectionService") CollectionService collectionService) {
        super(collectionService);
    }
}
