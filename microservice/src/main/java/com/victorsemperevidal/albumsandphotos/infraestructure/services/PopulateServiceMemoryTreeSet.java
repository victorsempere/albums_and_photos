package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;

@Service
@Qualifier("populateServiceBaseMemoryTreeSet")
public class PopulateServiceMemoryTreeSet extends PopulateServiceBase {

    @Autowired
    public PopulateServiceMemoryTreeSet(@Qualifier("memoryAlbumRepositoryTreeSet") AlbumRepository albumRepository,
            @Qualifier("memoryPhotoRepositoryTreeSet") PhotoRepository photoRepository) {
        super(albumRepository, photoRepository);
    }

}
