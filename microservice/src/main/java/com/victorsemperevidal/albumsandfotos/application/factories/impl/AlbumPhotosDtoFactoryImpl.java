package com.victorsemperevidal.albumsandfotos.application.factories.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumDto;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandfotos.application.factories.AlbumDtoFactory;
import com.victorsemperevidal.albumsandfotos.application.factories.AlbumPhotosDtoFactory;
import com.victorsemperevidal.albumsandfotos.application.factories.PhotoDtoFactory;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;

@Service
public class AlbumPhotosDtoFactoryImpl implements AlbumPhotosDtoFactory {

    private AlbumDtoFactory albumDtoFactory;
    private PhotoDtoFactory photoDtoFactory;

    @Autowired
    public AlbumPhotosDtoFactoryImpl(AlbumDtoFactory albumDtoFactory, PhotoDtoFactory photoDtoFactory) {
        super();
        this.albumDtoFactory = albumDtoFactory;
        this.photoDtoFactory = photoDtoFactory;
    }

    @Override
    public List<AlbumPhotosDto> getListFromAlbumPhotos(List<AlbumPhotos> albumsPhotos) {
        List<AlbumPhotosDto> dtos = new ArrayList<>();

        if (albumsPhotos != null) {
            for (AlbumPhotos album : albumsPhotos) {
                dtos.add(getInstance(album));
            }
        }
        return dtos;
    }

    private AlbumPhotosDto getInstance(AlbumPhotos album) {
        AlbumDto albumDto = albumDtoFactory.getInstance(album.getAlbum());

        AlbumPhotosDto dto = new AlbumPhotosDto(albumDto);
        if (album.getPhotos() != null) {
            for (Photo photo : album.getPhotos()) {
                dto.getPhotos().add(photoDtoFactory.getInstance(photo));
            }
        }
        return dto;
    }

}
