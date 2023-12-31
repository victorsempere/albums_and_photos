package com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.PhotoDto;

public interface PhotoDtoFactory {
    public PhotoDto getInstance(Photo photo);
}
