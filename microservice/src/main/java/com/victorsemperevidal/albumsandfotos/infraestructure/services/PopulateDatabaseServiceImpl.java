package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandfotos.domain.services.PopulateService;

@Service
@Qualifier("populateDatabaseService")
public class PopulateDatabaseServiceImpl implements PopulateService {

    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;

    @Autowired
    public PopulateDatabaseServiceImpl(@Qualifier("databaseAlbumRepository") AlbumRepository albumRepository,
            @Qualifier("databasePhotoRepository") PhotoRepository photoRepository) {
        super();
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public void populate(ExternalData externalData) {
        this.albumRepository.deleteAll();
        this.photoRepository.deleteAll();
        this.albumRepository.saveAll(externalData.getAlbums());
        this.photoRepository.saveAll(externalData.getPhotos());
    }

}
