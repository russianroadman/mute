package ru.russianroadman.mute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.SpringVersion;

public class TestCases {

    @Test
    public void springVersion(){
        Object object = new Object();
        System.out.println(object.hashCode());
        String version = SpringVersion.getVersion();
        System.out.println(version);
        System.out.println(
            "16 & [Foo] hash: " + (16 & "Foo".hashCode())
        );
        System.out.println(
            "16 & [Bar] hash: " + (16 & "Bar".hashCode())
        );
        System.out.println(
            "16 & [FooBar] hash: " + (16 & "FooBar".hashCode())
        );
        Assertions.assertNotNull(version);
    }

}
