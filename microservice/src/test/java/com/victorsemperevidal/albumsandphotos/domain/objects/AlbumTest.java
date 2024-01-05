package com.victorsemperevidal.albumsandphotos.domain.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumTest {
    public AlbumTest() {
        super();
    }

    @Test
    void givenAlbumInstanceWhenCallingGetsThenReturnConstructionValues() {
        //
        // given
        //
        Long expectedUserId = 1l;
        Long expectedId = 2l;
        String expectedTitle = "Expected title";
        Album a = new Album(expectedUserId, expectedId, expectedTitle);

        //
        // when
        //
        Long userId = a.getUserId();
        Long id = a.getId();
        String title = a.getTitle();

        //
        // then
        //
        assertEquals(expectedUserId, userId);
        assertEquals(expectedId, id);
        assertEquals(expectedTitle, title);
    }

    @Test
    void givenAlbumsWithDifferentIdsWhenEqualsThenReturnFalse() {
        //
        // given
        //
        Album a1 = new Album(null, -1l, null);
        Album a2 = new Album(null, -2l, null);

        //
        // when
        //
        boolean a1Equalsa2 = a1.equals(a2);
        boolean a2Equalsa1 = a2.equals(a1);

        //
        // then
        //
        assertFalse(a1Equalsa2);
        assertFalse(a2Equalsa1);
    }

    @Test
    void givenEqualAlbumsWhenCompareToThenReturnZero() {
        //
        // given
        //
        Album a1 = new Album(null, -1l, null);
        Album a2 = new Album(null, -1l, null);

        //
        // when
        //
        int a1CompareToA2 = a1.compareTo(a2);
        int a2CompareToA1 = a1.compareTo(a2);

        //
        // then
        //
        assertEquals(0, a1CompareToA2);
        assertEquals(0, a2CompareToA1);
    }

    @Test
    void givenNotEqualAlbumsWhenCompareToThenReturnNonZero() {
        //
        // given
        //
        Album a1 = new Album(null, -2l, null);
        Album a2 = new Album(null, -1l, null);

        //
        // when
        //
        int a1CompareToa2 = a1.compareTo(a2);
        int a2CompareToa1 = a2.compareTo(a1);

        //
        // then
        //
        assertEquals(-1, a1CompareToa2);
        assertEquals(1, a2CompareToa1);
    }

}
