package com.victorsemperevidal.albumsandphotos.infraestructure.services.albums_map_service;

import java.util.Collection;
import java.util.Map;

import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;

public interface AlbumsMapService {

    public Map<AlbumId, Collection<Photo>> getInstance();
}
