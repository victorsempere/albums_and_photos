package com.victorsemperevidal.albumsandfotos.application.factories;

import com.victorsemperevidal.albumsandfotos.application.dtos.PhotoDto;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

public interface PhotoDtoFactory {
    public PhotoDto getInstance(Photo photo);
}
