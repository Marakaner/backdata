package net.alexander.backdata.file;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class FileLoader {
    private File file;

    private HashMap<String, Object> storage;

    public FileLoader(File file) {
        this.file = file;
        this.storage = new HashMap<>();
        load();
    }

    public void load() {

        if(!this.storage.isEmpty()) {
            this.storage = new HashMap<>();
        }

        if(this.file.exists()) {
            FileReader fR = null;
            BufferedReader bR = null;
            try {

                fR = new FileReader(file.getPath());
                bR = new BufferedReader(fR);

                Iterator<String> lines = bR.lines().iterator();

                while (lines.hasNext()) {
                    String[] current = lines.next().split(",");
                    this.storage.put(current[0], current[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bR.close();
                    fR.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void save() {

        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fW = null;
        BufferedWriter bW = null;

        try {

            fW = new FileWriter(file.getPath());
            bW = new BufferedWriter(fW);

            for (String current : this.storage.keySet()) {
                bW.write(current + "," + this.storage.get(current));
                bW.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bW.close();
                fW.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void set(String key, Object value) {
        if (this.storage.containsKey(key)) {
            this.storage.replace(key, value);
        } else {
            this.storage.put(key, value);
        }
    }

    public Object get(String key) {
        return this.storage.containsKey(key) ? this.storage.get(key) : null;
    }

    public void delete(String key) {
        if(this.storage.containsKey(key)) {
            this.storage.remove(key);
        }
    }

    public HashMap<String, Object> getStorage() {
        return storage;
    }

}