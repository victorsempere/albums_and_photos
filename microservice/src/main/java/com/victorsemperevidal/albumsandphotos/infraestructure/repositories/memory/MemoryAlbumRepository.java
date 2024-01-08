package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.memory;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections.AlbumAndPhotoProjectionFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

class MemoryAlbumRepository implements AlbumRepository {
    private Collection<Album> albums;
    private PhotoRepository photoRepository;
    private AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory;
    private CollectionService collectionService;

    protected MemoryAlbumRepository(AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            PhotoRepository photoRepository, CollectionService collectionService) {
        super();
        this.albumAndPhotoProjectionFactory = albumAndPhotoProjectionFactory;
        this.photoRepository = photoRepository;
        this.collectionService = collectionService;
    }

    @Override
    public void saveAll(Collection<Album> listOfAlbums) {
        albums = listOfAlbums == null ? collectionService.getInstance() : collectionService.copyOf(listOfAlbums);
    }

    @Override
    public Collection<Album> findAll() {
        return albums;
    }

    @Override
    public Collection<AlbumAndPhotoProjection> getAlbumsAndPhotos() {
        Collection<Photo> all = this.photoRepository.findAll();
        return albumAndPhotoProjectionFactory.getInstancesFromListOfAlbumsAndPhotos(albums,
                all);
    }

    @Override
    public void deleteAll() {
        this.photoRepository.deleteAll();
        this.albums = collectionService.getInstance();
    }

}