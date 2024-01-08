package com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.domain.services.ProcessAlbumsService;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

class ProcessAlbumsServiceBase implements ProcessAlbumsService {

    private AlbumRepository albumRepository;
    private AlbumPhotosFactory albumPhotosFactory;

    protected ProcessAlbumsServiceBase(AlbumRepository albumRepository,
            AlbumPhotosFactory albumPhotosFactory) {
        super();
        this.albumRepository = albumRepository;
        this.albumPhotosFactory = albumPhotosFactory;
    }

    @Override
    public Collection<AlbumPhotos> processAlbumsAndPhotos() {
        Collection<AlbumAndPhotoProjection> albumsAndPhotosProjections = albumRepository.getAlbumsAndPhotos();
        return albumPhotosFactory.getListOfAlbumPhotosFromProjections(albumsAndPhotosProjections);
    }

}
