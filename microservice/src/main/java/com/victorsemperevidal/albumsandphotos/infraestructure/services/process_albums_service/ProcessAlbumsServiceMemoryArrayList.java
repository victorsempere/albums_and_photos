package com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

@Service
@Qualifier("processAlbumsServiceMemoryArrayList")
public class ProcessAlbumsServiceMemoryArrayList extends ProcessAlbumsServiceBase {

    @Autowired
    public ProcessAlbumsServiceMemoryArrayList(
            @Qualifier("memoryAlbumRepositoryArrayList") AlbumRepository albumRepository,
            @Qualifier("albumPhotosFactoryArrayList") AlbumPhotosFactory albumPhotosFactory) {
        super(albumRepository, albumPhotosFactory);
    }

}
