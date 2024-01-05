package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("albumPhotosFactoryTreeSet")
public class AlbumPhotosFactoryTreeSet extends AlbumPhotosFactoryBase {

    @Autowired
    public AlbumPhotosFactoryTreeSet(@Qualifier("treeSetCollectionService") CollectionService collectionService,
            @Qualifier("albumFactoryTreeSet") AlbumFactory albumFactory,
            @Qualifier("photoFactoryTreeSet") PhotoFactory photoFactory) {
        super(collectionService, albumFactory, photoFactory);
    }

}
