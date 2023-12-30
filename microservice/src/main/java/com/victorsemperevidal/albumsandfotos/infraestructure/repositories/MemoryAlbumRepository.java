package com.victorsemperevidal.albumsandfotos.infraestructure.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandfotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.projections.AlbumAndPhotoProjectionFactory;

@Service
@Qualifier("memoryAlbumRepository")
public class MemoryAlbumRepository implements AlbumRepository {
    private List<Album> albums;
    private PhotoRepository photoRepository;
    private AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory;

    @Autowired
    public MemoryAlbumRepository(AlbumAndPhotoProjectionFactory albumAndPhotoProjectionFactory,
            @Qualifier("memoryPhotoRepository") PhotoRepository photoRepository) {
        super();
        this.albumAndPhotoProjectionFactory = albumAndPhotoProjectionFactory;
        this.photoRepository = photoRepository;
    }

    @Override
    public void saveAll(List<Album> listOfAlbums) {
        albums = listOfAlbums == null ? null : List.copyOf(listOfAlbums);
    }

    @Override
    public List<Album> findAll() {
        return albums;
    }

    @Override
    public List<AlbumAndPhotoProjection> getAlbumsAndPhotos() {
        return albumAndPhotoProjectionFactory.getInstancesFrom(this.findAll(), this.photoRepository.findAll());
    }

}