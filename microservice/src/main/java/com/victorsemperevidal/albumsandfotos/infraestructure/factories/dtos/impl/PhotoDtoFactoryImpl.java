package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.PhotoDto;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos.PhotoDtoFactory;

@Service
public class PhotoDtoFactoryImpl implements PhotoDtoFactory {

    @Override
    public PhotoDto getInstance(Photo photo) {
        return new PhotoDto(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

}
