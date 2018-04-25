package com.prince.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Prince Raj
 */
public class FileSystem {

    private static final int ROWS = 100;

    private static final int COLUMNS = 100;

    private static FileSystem instance;

    private FileSystem() {
    }

    public static FileSystem getInstance() {
        if (instance == null) {
            synchronized (FileSystem.class) {
                if (instance == null) {
                    instance = new FileSystem();
                }
            }
        }
        return instance;
    }

    public void createDirectory(Directory directory) {
        // TODO Implement me!
    }

    public void addFile(File file) {
        // TODO Implement me!
    }
}

abstract class Entry {

    private Directory parent;

    private String name;

    private Date createdOn;

    private Date lastUpdated;

    private Date lastAccessed;

    public Entry(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        this.createdOn = new Date();
        this.lastUpdated = new Date();
        this.lastAccessed = new Date();
    }

    public boolean delete() {
        if (parent == null) {
            return false;
        } else {
            return parent.deleteEntry(this);
        }
    }

    public abstract int getSize();

    public String getFullPath() {
        if (parent == null) {
            return name;
        } else {
            return parent.getFullPath() + "/" + name;
        }
    }
}

class File extends Entry {

    private String content;

    private int size;

    public File(Directory parent, String name, int size) {
        super(parent, name);
        this.content = content;
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getSize() {
        return size;
    }
}

class Directory extends Entry {

    private List<Entry> contents;

    public Directory(Directory parent, String name) {
        super(parent, name);
        this.contents = new ArrayList<>();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry e : contents) {
            size += e.getSize();
        }

        return size;
    }

    public int getNumberOfFiles() {
        int count = 0;
        for (Entry e : contents) {
            if (e instanceof Directory) {
                // count directory as file
                count++;

                count += ((Directory)e).getNumberOfFiles();
            } else {
                count++;
            }
        }

        return count;
    }

    public boolean deleteEntry(Entry entry) {
        return contents.remove(entry);
    }

    public void addEntry(Entry entry) {
        contents.add(entry);
    }

    public List<Entry> getContents() {
        return contents;
    }
}
