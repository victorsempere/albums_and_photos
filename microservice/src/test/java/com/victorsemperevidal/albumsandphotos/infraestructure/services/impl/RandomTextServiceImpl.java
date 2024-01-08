package com.victorsemperevidal.albumsandphotos.infraestructure.services.impl;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.infraestructure.services.RandomTextService;

@Service
public class RandomTextServiceImpl implements RandomTextService {

    @Override
    public String getRandomText(String prefix) {
        return MessageFormat.format("{0} {1}", prefix, UUID.randomUUID().toString());
    }

}
