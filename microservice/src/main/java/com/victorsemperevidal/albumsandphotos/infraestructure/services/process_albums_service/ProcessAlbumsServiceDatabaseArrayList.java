package com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

@Service
@Qualifier("processAlbumsServiceDatabaseArrayList")
public class ProcessAlbumsServiceDatabaseArrayList extends ProcessAlbumsServiceBase {

    @Autowired
    public ProcessAlbumsServiceDatabaseArrayList(
            @Qualifier("databaseAlbumRepositoryArrayList") AlbumRepository albumRepository,
            @Qualifier("albumPhotosFactoryArrayList") AlbumPhotosFactory albumPhotosFactory) {
        super(albumRepository, albumPhotosFactory);
    }

}
