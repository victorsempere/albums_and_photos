package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.ExternalDataFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.PhotoFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.exceptions.ExternalClientExceptionFactory;

@Service
@Qualifier("externalDataServiceArrayList")
public class ExternalDataServiceArrayList extends ExternalDataServiceBase {

    @Autowired
    public ExternalDataServiceArrayList(AlbumsApi albumsApi, PhotosApi photosApi,
            ExternalDataFactory externalDataFactory, ExternalClientExceptionFactory externalClientExceptionFactory,
            @Qualifier("albumFactoryArrayList") AlbumFactory albumFactory,
            @Qualifier("photoFactoryArrayList") PhotoFactory photoFactory) {
        super(albumsApi, photosApi, externalDataFactory, externalClientExceptionFactory, albumFactory, photoFactory);
    }

}
