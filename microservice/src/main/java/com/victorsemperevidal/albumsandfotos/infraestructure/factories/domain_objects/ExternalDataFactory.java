package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

@Service
public class ExternalDataFactory {

    public ExternalData getInstance(List<Album> albums, List<Photo> photos) {
        return new ExternalData(albums, photos);
    }

}
