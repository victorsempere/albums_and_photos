package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos.AlbumDaoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.SpringDataAlbumRepository;

@Service
@Qualifier("databaseAlbumRepositoryTreeSet")
public class DatabaseAlbumRepositoryTreeSet extends DatabaseAlbumRepository {

    @Autowired
    public DatabaseAlbumRepositoryTreeSet(
            @Qualifier("albumFactoryTreeSet") AlbumFactory albumFactory,
            @Qualifier("albumAndPhotoProjectionFactoryHashMapAndTreeSet") AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            AlbumDaoFactory albumDaoFactory,
            SpringDataAlbumRepository springDataAlbumRepository) {
        super(albumFactory, albumDaoFactory, albumAndPhotoProjectionFactory, springDataAlbumRepository);
    }
}
