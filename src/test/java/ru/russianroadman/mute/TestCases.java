package ru.russianroadman.mute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.SpringVersion;

public class TestCases {

    @Test
    public void springVersion(){
        String version = SpringVersion.getVersion();
        System.out.println(version);
        Assertions.assertNotNull(version);
    }

}
