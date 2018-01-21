package ru.unn.agile.MetricsDistance.Infrastructure;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.List;
import java.util.ArrayList;

import ru.unn.agile.MetricsDistance.viewmodel.ILogger;

public class TxtLogger implements ILogger {

    public TxtLogger(final String filename) {
        this.filename = filename;

        BufferedWriter logWriter = null;

        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename can not be empty");
        }

        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void log(final String s, final String logTag) {
        try {
            String message = AbstractLogger.prepareMassageForLog(s, logTag);
            writer.write(message);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void log(final String s) {
        this.log(s, "");
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                log.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    private final BufferedWriter writer;
    private final String filename;
}
