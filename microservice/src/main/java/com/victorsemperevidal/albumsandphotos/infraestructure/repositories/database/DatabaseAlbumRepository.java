package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import java.util.Collection;
import java.util.List;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos.AlbumDaoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.SpringDataAlbumRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.projections.AlbumAndPhotoProjectionDao;

class DatabaseAlbumRepository implements AlbumRepository {

    private SpringDataAlbumRepository springDataAlbumRepository;
    private AlbumDaoFactory albumDaoFactory;
    private AlbumFactory albumFactory;
    private AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory;

    protected DatabaseAlbumRepository(AlbumFactory albumFactory, AlbumDaoFactory albumDaoFactory,
            AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            SpringDataAlbumRepository springDataAlbumRepository) {
        super();
        this.albumFactory = albumFactory;
        this.albumDaoFactory = albumDaoFactory;
        this.albumAndPhotoProjectionFactory = albumAndPhotoProjectionFactory;
        this.springDataAlbumRepository = springDataAlbumRepository;
    }

    @Override
    public void saveAll(Collection<Album> albums) {
        Collection<AlbumDao> daos = albumDaoFactory.getInstancesFromDomainAlbums(albums);
        springDataAlbumRepository.saveAll(daos);
    }

    @Override
    public Collection<Album> findAll() {
        Collection<AlbumDao> albumsDao = springDataAlbumRepository.findAll();
        return albumFactory.getInstancesFromAlbumsDao(albumsDao);
    }

    @Override
    public Collection<AlbumAndPhotoProjection> getAlbumsAndPhotos() {
        List<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos = springDataAlbumRepository.getAlbumsAndPhotos();
        return albumAndPhotoProjectionFactory.getInstancesFromListOfDaos(albumsAndPhotosDaos);
    }

    @Override
    public void deleteAll() {
        springDataAlbumRepository.deleteAll();
    }
}
