package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandfotos.domain.services.PopulateService;

@Service
@Qualifier("populateMemoryService")
public class PopulateMemoryServiceImpl implements PopulateService {

    private PhotoRepository photoRepository;
    private AlbumRepository albumRepository;

    @Autowired
    public PopulateMemoryServiceImpl(@Qualifier("memoryAlbumRepository") AlbumRepository albumRepository,
            @Qualifier("memoryPhotoRepository") PhotoRepository photoRepository) {
        super();
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public void populate(ExternalData externalData) {
        this.albumRepository.saveAll(externalData.getAlbums());
        this.photoRepository.saveAll(externalData.getPhotos());
    }

}
