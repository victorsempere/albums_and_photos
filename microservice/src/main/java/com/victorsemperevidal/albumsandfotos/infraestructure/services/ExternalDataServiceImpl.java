package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.AlbumFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.PhotoFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.ExternalDataFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.exceptions.ExternalClientExceptionFactory;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;

/**
 * Servicio que nos dará la información obtenida de la fuente de datos externa
 * en objetos del dominio
 */
@Service
public class ExternalDataServiceImpl implements ExternalDataService {

    private AlbumsApi albumsApi;
    private PhotosApi photosApi;
    private AlbumFactory albumFactory;
    private ExternalClientExceptionFactory externalClientExceptionFactory;
    private ExternalDataFactory externalDataFactory;
    private PhotoFactory photoFactory;

    @Autowired
    public ExternalDataServiceImpl(AlbumsApi albumsApi, PhotosApi photosApi,
            ExternalDataFactory externalDataFactory, ExternalClientExceptionFactory externalClientExceptionFactory,
            AlbumFactory albumFactory, PhotoFactory photoFactory) {
        super();
        this.albumsApi = albumsApi;
        this.photosApi = photosApi;
        this.externalDataFactory = externalDataFactory;
        this.externalClientExceptionFactory = externalClientExceptionFactory;
        this.albumFactory = albumFactory;
        this.photoFactory = photoFactory;
    }

    @Override
    public ExternalData fetchExternalData() throws ExternalClientException {
        try {
            List<Album> albums = albumFactory.getInstancesFromAlbumsApi(this.albumsApi.getAlbums());
            List<Photo> photos = photoFactory.getInstancesFromPhotosApi(this.photosApi.getPhotos());

            return externalDataFactory.getInstance(albums, photos);

        } catch (ApiException e) {
            throw externalClientExceptionFactory.getInstance(e);
        }
    }
}
