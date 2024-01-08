package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("albumPhotosFactoryArrayList")
public class AlbumPhotosFactoryArrayList extends AlbumPhotosFactoryBase {

    @Autowired
    public AlbumPhotosFactoryArrayList(@Qualifier("arrayListCollectionService") CollectionService collectionService,
            @Qualifier("albumFactoryArrayList") AlbumFactory albumFactory,
            @Qualifier("photoFactoryArrayList") PhotoFactory photoFactory) {
        super(collectionService, albumFactory, photoFactory);
    }

}
