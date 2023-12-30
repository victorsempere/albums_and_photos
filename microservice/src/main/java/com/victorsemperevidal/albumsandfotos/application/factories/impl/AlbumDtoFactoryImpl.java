package com.victorsemperevidal.albumsandfotos.application.factories.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumDto;
import com.victorsemperevidal.albumsandfotos.application.factories.AlbumDtoFactory;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;

@Service
public class AlbumDtoFactoryImpl implements AlbumDtoFactory {
    @Override
    public AlbumDto getInstance(Album album) {
        return new AlbumDto(album.getUserId(), album.getId(), album.getTitle());
    }

}
