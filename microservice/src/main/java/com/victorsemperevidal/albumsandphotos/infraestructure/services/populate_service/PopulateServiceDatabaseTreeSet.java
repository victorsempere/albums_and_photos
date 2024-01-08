package com.victorsemperevidal.albumsandphotos.infraestructure.services.populate_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;

@Service
@Qualifier("populateServiceDatabaseTreeSet")
public class PopulateServiceDatabaseTreeSet extends PopulateServiceBase {

    @Autowired
    public PopulateServiceDatabaseTreeSet(@Qualifier("databaseAlbumRepositoryTreeSet") AlbumRepository albumRepository,
            @Qualifier("databasePhotoRepositoryTreeSet") PhotoRepository photoRepository) {
        super(albumRepository, photoRepository);
    }

}
