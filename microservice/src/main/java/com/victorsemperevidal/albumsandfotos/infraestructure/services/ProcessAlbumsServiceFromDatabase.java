package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.services.ProcessAlbumsService;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

@Service
@Qualifier("processAlbumsServiceFromDatabase")
public class ProcessAlbumsServiceFromDatabase implements ProcessAlbumsService {

    private AlbumRepository albumRepository;
    private AlbumPhotosFactory albumPhotosFactory;

    @Autowired
    public ProcessAlbumsServiceFromDatabase(@Qualifier("databaseAlbumRepository") AlbumRepository albumRepository,
            AlbumPhotosFactory albumPhotosFactory) {
        super();
        this.albumRepository = albumRepository;
        this.albumPhotosFactory = albumPhotosFactory;
    }

    @Override
    public List<AlbumPhotos> processAlbumsAndPhotos() {
        List<AlbumAndPhotoProjection> albumsAndPhotosProjections = albumRepository.getAlbumsAndPhotos();
        return albumPhotosFactory.getListOfAlbumPhotosFromProjections(albumsAndPhotosProjections);
    }

}
