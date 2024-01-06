package com.victorsemperevidal.albumsandphotos.infraestructure.services.process_albums_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.AlbumPhotosFactory;

@Service
@Qualifier("processAlbumsServiceMemoryTreeSet")
public class ProcessAlbumsServiceMemoryTreeSet extends ProcessAlbumsServiceBase {

    @Autowired
    public ProcessAlbumsServiceMemoryTreeSet(
            @Qualifier("memoryAlbumRepositoryTreeSet") AlbumRepository albumRepository,
            @Qualifier("albumPhotosFactoryTreeSet") AlbumPhotosFactory albumPhotosFactory) {
        super(albumRepository, albumPhotosFactory);
    }

}
