package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.ExternalDataFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.exceptions.ExternalClientExceptionFactory;

/**
 * Servicio que nos dará la información obtenida de la fuente de datos externa
 * en objetos del dominio
 */
class ExternalDataServiceBase implements ExternalDataService {

    private AlbumsApi albumsApi;
    private PhotosApi photosApi;
    private AlbumFactory albumFactory;
    private ExternalClientExceptionFactory externalClientExceptionFactory;
    private ExternalDataFactory externalDataFactory;
    private PhotoFactory photoFactory;

    public ExternalDataServiceBase(AlbumsApi albumsApi, PhotosApi photosApi,
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
            Collection<Album> albums = albumFactory.getInstancesFromAlbumsApi(this.albumsApi.getAlbums());
            Collection<Photo> photos = photoFactory.getInstancesFromPhotosApi(this.photosApi.getPhotos());

            return externalDataFactory.getInstance(albums, photos);

        } catch (ApiException e) {
            throw externalClientExceptionFactory.getInstance(e);
        }
    }
}
