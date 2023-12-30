package com.victorsemperevidal.albumsandfotos.application.factories;

import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumDto;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;

public interface AlbumDtoFactory {

    public AlbumDto getInstance(Album album);
}
