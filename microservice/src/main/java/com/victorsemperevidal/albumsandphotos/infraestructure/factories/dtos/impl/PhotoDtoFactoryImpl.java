package com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.PhotoDto;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos.PhotoDtoFactory;

@Service
public class PhotoDtoFactoryImpl implements PhotoDtoFactory {

    @Override
    public PhotoDto getInstance(Photo photo) {
        return new PhotoDto(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

}
