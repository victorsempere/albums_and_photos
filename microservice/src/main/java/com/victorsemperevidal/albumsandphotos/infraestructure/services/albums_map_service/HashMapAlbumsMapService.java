package com.victorsemperevidal.albumsandphotos.infraestructure.services.albums_map_service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;

@Service
@Qualifier("hashMapAlbumsMapService")
public class HashMapAlbumsMapService implements AlbumsMapService {

    @Override
    public Map<AlbumId, Collection<Photo>> getInstance() {
        return new HashMap<>();
    }

}
