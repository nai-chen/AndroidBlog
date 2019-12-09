package com.blog.demo.tool;

import java.io.File;

public class CommonExample {

    public boolean callArgumentInstance(File file) {
        return file.exists();
    }

    public boolean callArgumentInstance(String path) {
        File file = new File(path);
        return file.exists();
    }

}
