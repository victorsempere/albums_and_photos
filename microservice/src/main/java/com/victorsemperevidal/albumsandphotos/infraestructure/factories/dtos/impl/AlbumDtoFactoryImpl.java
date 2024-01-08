package com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.AlbumDto;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos.AlbumDtoFactory;

@Service
public class AlbumDtoFactoryImpl implements AlbumDtoFactory {
    @Override
    public AlbumDto getInstance(Album album) {
        return new AlbumDto(album.getUserId(), album.getId(), album.getTitle());
    }

}
