package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import java.util.Collection;
import java.util.List;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.PhotoDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos.PhotoDaoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.SpringDataPhotoRepository;

class DatabasePhotoRepository implements PhotoRepository {

    private SpringDataPhotoRepository springPhotoRepository;
    private PhotoDaoFactory photoDaoFactory;
    private PhotoFactory photoFactory;

    protected DatabasePhotoRepository(SpringDataPhotoRepository springPhotoRepository, PhotoDaoFactory photoDaoFactory,
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