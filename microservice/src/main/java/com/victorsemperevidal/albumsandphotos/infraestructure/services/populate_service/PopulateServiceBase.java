package com.victorsemperevidal.albumsandphotos.infraestructure.services.populate_service;

import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;

class PopulateServiceBase implements PopulateService {

    private PhotoRepository photoRepository;
    private AlbumRepository albumRepository;

    protected PopulateServiceBase(AlbumRepository albumRepository,
            PhotoRepository photoRepository) {
        super();
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    /**
     * Sincronizamos el punto de entrada para guardar los datos en la base de datos
     */
    @Override
    public synchronized void populate(ExternalData externalData) {
        // Borramos primero el repositorio de las fotos para después evitar borrados en
        // cascada
        this.photoRepository.deleteAll();
        this.albumRepository.deleteAll();

        // Insertamos los álbumes sin fotos y después insertamos las fotos,
        // relacionándolas con el álbum para no tener que construir el objeto completo
        // del album con sus fotos
        this.albumRepository.saveAll(externalData.getAlbums());
        this.photoRepository.saveAll(externalData.getPhotos());
    }

}
