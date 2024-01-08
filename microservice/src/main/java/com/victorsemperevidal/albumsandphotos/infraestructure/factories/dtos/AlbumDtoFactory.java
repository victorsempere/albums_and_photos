package com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.AlbumDto;

public interface AlbumDtoFactory {

    public AlbumDto getInstance(Album album);
}
