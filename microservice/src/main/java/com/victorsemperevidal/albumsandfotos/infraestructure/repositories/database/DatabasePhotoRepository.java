package com.victorsemperevidal.albumsandfotos.infraestructure.repositories.database;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.PhotoDao;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.daos.PhotoDaoFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.PhotoFactory;

@Service
@Qualifier("databasePhotoRepository")
public class DatabasePhotoRepository implements PhotoRepository {

    private SpringPhotoRepository springPhotoRepository;
    private PhotoDaoFactory photoDaoFactory;
    private PhotoFactory photoFactory;

    @Autowired
    public DatabasePhotoRepository(SpringPhotoRepository springPhotoRepository, PhotoDaoFactory photoDaoFactory,
            PhotoFactory photoFactory) {
        super();
        this.springPhotoRepository = springPhotoRepository;
        this.photoDaoFactory = photoDaoFactory;
        this.photoFactory = photoFactory;
    }

    @Override
    public void saveAll(Collection<Photo> photos) {
        Collection<PhotoDao> photoDaos = photoDaoFactory.getInstancesFromDomainPhotos(photos);
        this.springPhotoRepository.saveAll(photoDaos);
    }

    @Override
    public Collection<Photo> findAll() {
        List<PhotoDao> photoDaos = this.springPhotoRepository.findAll();
        return photoFactory.getInstancesFromPhotoDaos(photoDaos);
    }

    @Override
    public void deleteAll() {
        this.springPhotoRepository.deleteAll();
    }
}

@Repository
@RepositoryRestResource(path = "db-photos")
interface SpringPhotoRepository extends ListCrudRepository<PhotoDao, Long> {

    @Override
    @RestResource(exported = false)
    <S extends PhotoDao> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends PhotoDao> List<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(PhotoDao entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}