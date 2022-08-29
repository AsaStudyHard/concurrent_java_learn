package com.zjf.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j(topic = "c.FileReader")
public class FileReader {
    public static void read(String filename) {

        try (FileInputStream in = new FileInputStream(filename)) {
            long start = System.currentTimeMillis();
            log.debug("read [file] start ...");
            byte[] buf = new byte[512];
            int n = -1;
            do {
                n = in.read(buf);
            } while (n != -1);
            long end = System.currentTimeMillis();
            log.debug("read [file] end ... cost: {} ms", end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}