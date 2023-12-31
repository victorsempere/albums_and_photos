package com.victorsemperevidal.albumsandfotos.infraestructure.factories.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.AlbumDao;

@Service
public class AlbumDaoFactory {
    public AlbumDaoFactory() {
        super();
    }

    public AlbumDao getInstance(Album album) {
        AlbumDao dao = new AlbumDao();
        dao.setId(album.getId());
        dao.setTitle(album.getTitle());
        dao.setUserId(album.getUserId());
        return dao;
    }

    public List<AlbumDao> getInstancesFromDomainAlbums(List<Album> listOfAlbums) {
        List<AlbumDao> daos = new ArrayList<>();
        if (listOfAlbums != null) {
            for (Album album : listOfAlbums) {
                daos.add(getInstance(album));
            }
        }
        return daos;
    }
}
