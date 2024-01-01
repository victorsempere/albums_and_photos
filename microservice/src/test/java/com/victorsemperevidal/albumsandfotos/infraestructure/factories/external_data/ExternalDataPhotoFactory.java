package com.victorsemperevidal.albumsandfotos.infraestructure.factories.external_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.infraestructure.services.RandomTextService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;

@Service
public class ExternalDataPhotoFactory {

    private RandomTextService randomTextService;

    @Autowired
    public ExternalDataPhotoFactory(RandomTextService randomTextService) {
        super();
        this.randomTextService = randomTextService;

    }

    public Photo getInstance(Exception e) {
        return new Photo().id(-1l).albumId(-1l).title("No se han cargado los mocks: " + e.getMessage()).url("Sin url")
                .thumbnailUrl("Sin thumbnail url");
    }

    public Photo getInstance(int albumId, int photoId) {
        Photo photo = new Photo();
        photo.setId(Integer.valueOf(photoId).longValue());
        photo.setAlbumId(Integer.valueOf(albumId).longValue());
        photo.setTitle(randomTextService.getRandomText("title"));
        photo.setUrl(randomTextService.getRandomText("url"));
        photo.setThumbnailUrl(randomTextService.getRandomText("thumbnailUrl"));
        return photo;
    }

}
