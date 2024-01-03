package com.victorsemperevidal.albumsandfotos.infraestructure.services.impl;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.infraestructure.services.RandomTextService;

@Service
public class RandomTextServiceImpl implements RandomTextService {

    @Override
    public String getRandomText(String prefix) {
        return MessageFormat.format("{0} {1}", prefix, UUID.randomUUID().toString());
    }

}
