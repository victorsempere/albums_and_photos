package com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.PhotoDto;

public interface PhotoDtoFactory {
    public PhotoDto getInstance(Photo photo);
}
