package com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumIdFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.albums_map_service.AlbumsMapService;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("albumAndPhotoProjectionFactoryHashMapAndArrayList")
public class AlbumAndPhotoProjectionFactoryHashMapAndArrayList extends AlbumAndPhotoProjectionFactoryBase {

    @Autowired
    public AlbumAndPhotoProjectionFactoryHashMapAndArrayList(
            @Qualifier("hashMapAlbumsMapService") AlbumsMapService albumsMapService,
            @Qualifier("arrayListCollectionService") CollectionService collectionService, AlbumIdFactory albumIdFactory) {
        super(albumsMapService, collectionService, albumIdFactory);
    }
}
