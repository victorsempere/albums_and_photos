package com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumIdFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.albums_map_service.AlbumsMapService;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("albumAndPhotoProjectionFactoryHashMapAndTreeSet")
public class AlbumAndPhotoProjectionFactoryHashMapAndTreeSet extends AlbumAndPhotoProjectionFactoryBase {

    @Autowired
    public AlbumAndPhotoProjectionFactoryHashMapAndTreeSet(
            @Qualifier("hashMapAlbumsMapService") AlbumsMapService albumsMapService,
            @Qualifier("treeSetCollectionService") CollectionService collectionService, AlbumIdFactory albumIdFactory) {
        super(albumsMapService, collectionService, albumIdFactory);
    }
}
