package com.victorsemperevidal.albumsandfotos.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandfotos.application.factories.AlbumPhotosDtoFactory;
import com.victorsemperevidal.albumsandfotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandfotos.domain.services.ProcessAlbumsService;

@Service
@Qualifier("albumsServiceInMemory")
public class AlbumsAndPhotosServiceMemoryImpl implements AlbumsAndPhotosService {

    private PopulateService populateService;
    private ProcessAlbumsService processAlbumsService;
    private AlbumPhotosDtoFactory albumPhotosDtoFactory;

    @Autowired
    public AlbumsAndPhotosServiceMemoryImpl(@Qualifier("populateMemoryService") PopulateService populateService,
            @Qualifier("processAlbumsServiceFromMemory") ProcessAlbumsService processAlbumsService,
            AlbumPhotosDtoFactory albumPhotosDtoFactory) {
        super();
        this.populateService = populateService;
        this.processAlbumsService = processAlbumsService;
        this.albumPhotosDtoFactory = albumPhotosDtoFactory;
    }

    @Override
    public List<AlbumPhotosDto> processAlbumsAndPhotos() {
        this.populateService.populate();
        return albumPhotosDtoFactory.getListFromAlbumPhotos(this.processAlbumsService.processAlbumsAndPhotos());
    }
}
