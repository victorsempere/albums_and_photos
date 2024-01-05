package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

@Service
@Qualifier("processAlbumsServiceDatabaseTreeSet")
public class ProcessAlbumsServiceDatabaseTreeSet extends ProcessAlbumsServiceBase {

    @Autowired
    public ProcessAlbumsServiceDatabaseTreeSet(
            @Qualifier("databaseAlbumRepositoryTreeSet") AlbumRepository albumRepository,
            @Qualifier("albumPhotosFactoryTreeSet") AlbumPhotosFactory albumPhotosFactory) {
        super(albumRepository, albumPhotosFactory);
    }

}
