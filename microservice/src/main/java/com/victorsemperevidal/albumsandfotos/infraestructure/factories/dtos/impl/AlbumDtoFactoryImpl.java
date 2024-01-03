package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.AlbumDto;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos.AlbumDtoFactory;

@Service
public class AlbumDtoFactoryImpl implements AlbumDtoFactory {
    @Override
    public AlbumDto getInstance(Album album) {
        return new AlbumDto(album.getUserId(), album.getId(), album.getTitle());
    }

}
