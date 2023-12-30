package com.victorsemperevidal.albumsandfotos.application.factories.impl;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.dtos.PhotoDto;
import com.victorsemperevidal.albumsandfotos.application.factories.PhotoDtoFactory;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

@Service
public class PhotoDtoFactoryImpl implements PhotoDtoFactory {

    @Override
    public PhotoDto getInstance(Photo photo) {
        return new PhotoDto(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

}
