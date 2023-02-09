package ru.russianroadman.mute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.SpringVersion;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCases {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    @Test
    public void testik(){
        var shit = Stream.of("1", "2", "3").map(it -> mapping(it)).collect(Collectors.joining());
        log.info(shit);
    }

    private String mapping(String it) {
        return "(" + it + ")";
    }

}
