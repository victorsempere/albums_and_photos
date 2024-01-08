package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;

public interface ExternalDataFactory {

    public ExternalData getInstance(Collection<Album> albums, Collection<Photo> photos);

}
