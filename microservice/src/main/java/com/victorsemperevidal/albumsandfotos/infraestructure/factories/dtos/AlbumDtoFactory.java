package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.AlbumDto;

public interface AlbumDtoFactory {

    public AlbumDto getInstance(Album album);
}
