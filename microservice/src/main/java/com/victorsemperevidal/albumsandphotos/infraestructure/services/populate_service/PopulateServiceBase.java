package com.victorsemperevidal.albumsandphotos.infraestructure.services.populate_service;

import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;

import jakarta.transaction.Transactional;

class PopulateServiceBase implements PopulateService {

    private PhotoRepository photoRepository;
    private AlbumRepository albumRepository;

    protected PopulateServiceBase(AlbumRepository albumRepository,
           PhotoRepository photoRepository) {
        super();
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    @Transactional
    public void populate(ExternalData externalData) {
        this.photoRepository.deleteAll();
        this.albumRepository.deleteAll();
        this.albumRepository.saveAll(externalData.getAlbums());
        this.photoRepository.saveAll(externalData.getPhotos());
    }

}
