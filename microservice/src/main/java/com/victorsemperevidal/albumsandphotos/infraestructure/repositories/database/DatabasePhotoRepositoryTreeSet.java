package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos.PhotoDaoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.SpringDataPhotoRepository;

@Service
@Qualifier("databasePhotoRepositoryTreeSet")
public class DatabasePhotoRepositoryTreeSet extends DatabasePhotoRepository {

    @Autowired
    public DatabasePhotoRepositoryTreeSet(SpringDataPhotoRepository springPhotoRepository,
            PhotoDaoFactory photoDaoFactory,
            @Qualifier("photoFactoryTreeSet") PhotoFactory photoFactory) {
        super(springPhotoRepository, photoDaoFactory, photoFactory);
    }

}