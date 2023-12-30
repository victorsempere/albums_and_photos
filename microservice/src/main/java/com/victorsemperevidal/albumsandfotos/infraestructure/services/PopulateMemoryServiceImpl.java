package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandfotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.AlbumFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.PhotoFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.exceptions.ExternalClientExceptionFactory;

@Service
@Qualifier("populateMemoryService")
public class PopulateMemoryServiceImpl implements PopulateService {

    private PhotoRepository photoRepository;
    private AlbumRepository albumRepository;
    private AlbumFactory albumFactory;
    private PhotoFactory photoFactory;
    private AlbumsApi albumsApi;
    private PhotosApi photosApi;
    private ExternalClientExceptionFactory externalClientExceptionFactory;

    @Autowired
    public PopulateMemoryServiceImpl(@Qualifier("memoryAlbumRepository") AlbumRepository albumRepository,
            @Qualifier("memoryPhotoRepository") PhotoRepository photoRepository,
            AlbumFactory albumFactory, PhotoFactory photoFactory,
            AlbumsApi albumsApi, PhotosApi photosApi, ExternalClientExceptionFactory externalClientExceptionFactory) {
        super();
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
        this.albumFactory = albumFactory;
        this.photoFactory = photoFactory;
        this.albumsApi = albumsApi;
        this.photosApi = photosApi;
        this.externalClientExceptionFactory = externalClientExceptionFactory;
    }

    @Override
    public void populate() {
        populateAlbums();
        populatePhotos();
    }

    private void populateAlbums() {
        try {
            List<Album> albums = albumFactory.getInstancesFromAlbumsApi(this.albumsApi.getAlbums());
            this.albumRepository.saveAll(albums);

        } catch (ApiException e) {
            throw externalClientExceptionFactory.getInstance(e);
        }
    }

    private void populatePhotos() {
        try {
            List<Photo> photos = photoFactory.getInstancesFromPhotosApi(this.photosApi.getPhotos());
            this.photoRepository.saveAll(photos);

        } catch (ApiException e) {
            throw externalClientExceptionFactory.getInstance(e);
        }
    }

}
