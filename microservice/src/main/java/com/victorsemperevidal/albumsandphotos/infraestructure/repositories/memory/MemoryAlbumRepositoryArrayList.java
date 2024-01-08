package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
@Qualifier("memoryAlbumRepositoryArrayList")
public class MemoryAlbumRepositoryArrayList extends MemoryAlbumRepository {

    @Autowired
    public MemoryAlbumRepositoryArrayList(
            @Qualifier("albumAndPhotoProjectionFactoryHashMapAndArrayList") AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            @Qualifier("memoryPhotoRepositoryArrayList") PhotoRepository photoRepository,
            @Qualifier("arrayListCollectionService") CollectionService collectionService) {
        super(albumAndPhotoProjectionFactory, photoRepository, collectionService);
    }

}