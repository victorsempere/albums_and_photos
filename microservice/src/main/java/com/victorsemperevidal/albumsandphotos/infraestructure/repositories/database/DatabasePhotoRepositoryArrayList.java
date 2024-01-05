package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos.PhotoDaoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.SpringDataPhotoRepository;

@Service
@Qualifier("databasePhotoRepositoryArrayList")
public class DatabasePhotoRepositoryArrayList extends DatabasePhotoRepository {

    @Autowired
    public DatabasePhotoRepositoryArrayList(SpringDataPhotoRepository springPhotoRepository,
            PhotoDaoFactory photoDaoFactory,
            @Qualifier("photoFactoryArrayList") PhotoFactory photoFactory) {
        super(springPhotoRepository, photoDaoFactory, photoFactory);
    }

}