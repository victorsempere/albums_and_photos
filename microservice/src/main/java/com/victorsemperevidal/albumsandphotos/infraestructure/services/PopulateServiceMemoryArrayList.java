package com.victorsemperevidal.albumsandphotos.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;

@Service
@Qualifier("populateServiceBaseMemoryArrayList")
public class PopulateServiceMemoryArrayList extends PopulateServiceBase {

    @Autowired
    public PopulateServiceMemoryArrayList(
            @Qualifier("memoryAlbumRepositoryArrayList") AlbumRepository albumRepository,
            @Qualifier("memoryPhotoRepositoryArrayList") PhotoRepository photoRepository) {
        super(albumRepository, photoRepository);
    }

}
