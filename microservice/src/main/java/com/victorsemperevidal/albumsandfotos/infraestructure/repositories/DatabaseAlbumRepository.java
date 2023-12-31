package com.victorsemperevidal.albumsandfotos.infraestructure.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.daos.AlbumDaoFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.repositories.projections.AlbumAndPhotoProjectionDao;

@Service
@Qualifier("databaseAlbumRepository")
public class DatabaseAlbumRepository implements AlbumRepository {

    private SpringAlbumRepository springAlbumRepository;
    private AlbumDaoFactory albumDaoFactory;
    private AlbumFactory albumFactory;
    private AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory;

    @Autowired
    public DatabaseAlbumRepository(AlbumFactory albumFactory, AlbumDaoFactory albumDaoFactory,
            AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            SpringAlbumRepository springAlbumRepository) {
        super();
        this.albumFactory = albumFactory;
        this.albumDaoFactory = albumDaoFactory;
        this.albumAndPhotoProjectionFactory = albumAndPhotoProjectionFactory;
        this.springAlbumRepository = springAlbumRepository;
    }

    @Override
    public void saveAll(List<Album> albums) {
        List<AlbumDao> daos = albumDaoFactory.getInstancesFromDomainAlbums(albums);
        springAlbumRepository.saveAll(daos);
    }

    @Override
    public List<Album> findAll() {
        List<AlbumDao> albumsDao = springAlbumRepository.findAll();
        return albumFactory.getInstancesFromAlbumsDao(albumsDao);
    }

    @Override
    public List<AlbumAndPhotoProjection> getAlbumsAndPhotos() {
        List<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos = springAlbumRepository.getAlbumsAndPhotos();
        return albumAndPhotoProjectionFactory.getInstancesFromListOfDaos(albumsAndPhotosDaos);
    }

    @Override
    public void deleteAll() {
        springAlbumRepository.deleteAll();
    }
}

@Repository
@RepositoryRestResource(path = "db-albums")
interface SpringAlbumRepository extends ListCrudRepository<AlbumDao, Long> {

    @Query(value = "SELECT a.id AS albumId, a.userId AS userId, a.title AS albumTitle, p.id AS photoId, p.title AS photoTitle, p.url AS photoUrl, p.thumbnailUrl AS photoThumbnailUrl FROM AlbumDao a LEFT JOIN a.photos p ORDER BY a.id, p.id")
    @RestResource(exported = false)
    List<AlbumAndPhotoProjectionDao> getAlbumsAndPhotos();

    @Override
    @RestResource(exported = false)
    <S extends AlbumDao> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends AlbumDao> List<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(AlbumDao entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}