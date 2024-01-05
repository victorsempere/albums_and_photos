package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;

@Service
@Qualifier("populateServiceDatabaseArrayList")
public class PopulateServiceDatabaseArrayList extends PopulateServiceBase {

    @Autowired
    public PopulateServiceDatabaseArrayList(
            @Qualifier("databaseAlbumRepositoryArrayList") AlbumRepository albumRepository,
            @Qualifier("databasePhotoRepositoryArrayList") PhotoRepository photoRepository) {
        super(albumRepository, photoRepository);
    }

}
