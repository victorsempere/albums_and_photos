package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

@Service
public class ExternalDataFactory {

    public ExternalData getInstance(Collection<Album> albums, Collection<Photo> photos) {
        return new ExternalData(albums, photos);
    }

}
