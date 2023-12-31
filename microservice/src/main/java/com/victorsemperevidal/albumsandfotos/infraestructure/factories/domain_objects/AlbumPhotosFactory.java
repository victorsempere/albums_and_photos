package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.AlbumFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.PhotoFactory;

@Service
public class AlbumPhotosFactory {

    private AlbumFactory albumFactory;
    private PhotoFactory photoFactory;

    @Autowired
    public AlbumPhotosFactory(AlbumFactory albumFactory, PhotoFactory photoFactory) {
        super();
        this.albumFactory = albumFactory;
        this.photoFactory = photoFactory;
    }

    public List<AlbumPhotos> getListOfAlbumPhotosFromProjections(
            List<AlbumAndPhotoProjection> albumsAndPhotosProjections) {
        if (albumsAndPhotosProjections == null) {
            return List.of();
        }

        AlbumPhotos lastAlbum = null;
        List<AlbumPhotos> listOfAlbumsWithPhotos = new ArrayList<>();
        for (AlbumAndPhotoProjection albumProjection : albumsAndPhotosProjections) {
            lastAlbum = appendProjectionToAlbumPhotos(albumProjection, listOfAlbumsWithPhotos, lastAlbum);
        }
        return listOfAlbumsWithPhotos;
    }

    private AlbumPhotos appendProjectionToAlbumPhotos(AlbumAndPhotoProjection albumProjection,
            List<AlbumPhotos> albumsPhotos, AlbumPhotos lastAlbum) {
        if (isDifferentAlbum(lastAlbum, albumProjection)) {
            lastAlbum = getInstance(albumProjection);
            albumsPhotos.add(lastAlbum);
        }

        Photo photo = photoFactory.getInstance(albumProjection);
        if (photo != null) {
            lastAlbum.getPhotos().add(photo);
        }

        return lastAlbum;
    }

    private boolean isDifferentAlbum(AlbumPhotos lastAlbum, AlbumAndPhotoProjection albumProjection) {
        return lastAlbum == null || !lastAlbum.getAlbum().getId().equals(albumProjection.getAlbumId());
    }

    private AlbumPhotos getInstance(AlbumAndPhotoProjection albumProjection) {
        return new AlbumPhotos(albumFactory.getInstance(albumProjection), new ArrayList<>());
    }

}
