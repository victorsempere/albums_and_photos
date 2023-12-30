package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
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
        List<AlbumPhotos> albumsPhotos = new ArrayList<>();
        for (AlbumAndPhotoProjection albumProjection : albumsAndPhotosProjections) {
            if (isDifferentAlbum(lastAlbum, albumProjection)) {
                lastAlbum = getInstance(albumProjection);
                albumsPhotos.add(lastAlbum);
            }
            lastAlbum.getPhotos().add(photoFactory.getInstance(albumProjection));
        }
        return albumsPhotos;
    }

    private boolean isDifferentAlbum(AlbumPhotos lastAlbum, AlbumAndPhotoProjection albumProjection) {
        return lastAlbum == null || !lastAlbum.getAlbum().getId().equals(albumProjection.getAlbumId());
    }

    private AlbumPhotos getInstance(AlbumAndPhotoProjection albumProjection) {
        return new AlbumPhotos(albumFactory.getInstance(albumProjection), new ArrayList<>());
    }

}
