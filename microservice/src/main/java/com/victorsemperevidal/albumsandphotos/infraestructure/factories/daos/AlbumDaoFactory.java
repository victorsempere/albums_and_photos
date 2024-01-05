package com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service.CollectionService;

@Service
public class AlbumDaoFactory {
    private CollectionService collectionService;

    @Autowired
    public AlbumDaoFactory(@Qualifier("arrayListCollectionService") CollectionService collectionService) {
        super();
        this.collectionService = collectionService;
    }

    public AlbumDao getInstance(Album album) {
        AlbumDao dao = new AlbumDao();
        dao.setId(album.getId());
        dao.setTitle(album.getTitle());
        dao.setUserId(album.getUserId());
        return dao;
    }

    public Collection<AlbumDao> getInstancesFromDomainAlbums(Collection<Album> listOfAlbums) {
        Collection<AlbumDao> daos = collectionService.getInstance();
        if (listOfAlbums != null) {
            for (Album album : listOfAlbums) {
                daos.add(getInstance(album));
            }
        }
        return daos;
    }
}
