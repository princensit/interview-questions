package com.prince.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Provides utilities related to files or directories
 */
public class FileUtils {

    private static final int BUFFER_SIZE = 8192;

    private FileUtils() {}

    public static boolean checkExistenceAndCreateDirectory(String filePath) {
        File file = new File(filePath);
        if (filePath.contains(".")) {
            // This appears to be a file and not a path, so use the parent directory
            file = file.getParentFile();
        }
        return file.exists() || file.mkdirs();
    }

    /**
     * Get a BufferedInputStream for the specified filePath. The input file must be UTF-8 encoded.
     *
     * @param filePath file path
     * @return input stream
     * @throws java.io.IOException IO exception
     */
    public static BufferedInputStream getBufferedInputStream(String filePath) throws IOException {
        File inputFile = new File(filePath);
        return new BufferedInputStream(getInputStream(inputFile));
    }

    /**
     * Get a BufferedReader for the specified File. The input file must be UTF-8 encoded.
     *
     * @param filePath path of the input file.
     * @return reader
     * @throws IOException IO exception
     */
    public static BufferedReader getBufferedReader(String filePath) throws IOException {
        File file = new File(filePath);
        return getBufferedReader(file);
    }

    /**
     * Get a BufferedReader for the specified dirName/fileName. The input file must be UTF-8
     * encoded.
     *
     * @param dirName directory of the input file.
     * @param fileName name of the input file.
     * @return reader
     * @throws IOException IO exception
     */
    public static BufferedReader getBufferedReader(String dirName, String fileName)
            throws IOException {
        File file = new File(dirName, fileName);
        return getBufferedReader(file);
    }

    /**
     * Get a BufferedReader for the specified File. The input file must be UTF-8 encoded.
     *
     * @param file the input file
     * @return reader
     * @throws IOException IO exception
     */
    public static BufferedReader getBufferedReader(File file) throws IOException {
        InputStream inputStream = getInputStream(file);
        return getBufferedReader(inputStream);
    }

    /**
     * Gets a BufferedReader for the given inputStream. The input stream must be UTF-8 encoded.
     *
     * @param inputStream file path
     * @return reader
     * @throws java.io.UnsupportedEncodingException exception
     */
    public static BufferedReader getBufferedReader(InputStream inputStream)
            throws UnsupportedEncodingException {
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(inputStreamReader);
    }

    public static BufferedOutputStream getBufferedOutputStream(File file) throws IOException {
        OutputStream outputStream = getOutputStream(file);
        return new BufferedOutputStream(outputStream);
    }

    /**
     * Get a BufferedWriter. The output file is UTF-8 encoded.
     *
     * @param filePath path of the output file.
     * @return writer
     * @throws IOException exception
     */
    public static BufferedWriter getBufferedWriter(String filePath) throws IOException {
        File file = new File(filePath);
        return getBufferedWriter(file);
    }

    /**
     * Get a BufferedWriter. The output file is UTF-8 encoded.
     *
     * @param dirName directory of the output file.
     * @param fileName name of the output file.
     * @return writer
     * @throws IOException exception
     */
    public static BufferedWriter getBufferedWriter(String dirName, String fileName)
            throws IOException {
        File file = new File(dirName, fileName);
        return getBufferedWriter(file);
    }

    /**
     * Get a BufferedWriter. The output file is UTF-8 encoded.
     *
     * @param file the output file
     * @return writer
     * @throws IOException exception
     */
    public static BufferedWriter getBufferedWriter(File file) throws IOException {
        OutputStream outputStream = getOutputStream(file);
        return getBufferedWriter(outputStream);
    }

    /**
     * Gets a BufferedWriter for the given outputStream. The input stream must be UTF-8 encoded.
     *
     * @param outputStream output stream
     * @return writer
     * @throws UnsupportedEncodingException exception
     */
    public static BufferedWriter getBufferedWriter(OutputStream outputStream)
            throws UnsupportedEncodingException {
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        return new BufferedWriter(outputStreamWriter);
    }

    /**
     * Get a PrintWriter with autoFlush set to false for the specified File. The output file is
     * UTF-8 encoded.
     *
     * @param file the output file
     * @return writer
     * @throws IOException exception
     */
    public static PrintWriter getPrintWriter(File file) throws IOException {
        BufferedWriter bufferedWriter = getBufferedWriter(file);
        return new PrintWriter(bufferedWriter, false);
    }

    private static InputStream getInputStream(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String fileName = file.getName();
        if (fileName.endsWith(".gz")) {
            inputStream = new GZIPInputStream(inputStream, BUFFER_SIZE);
        } else if (fileName.endsWith(".zip")) {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            zipInputStream.getNextEntry();
            inputStream = zipInputStream;
        }

        return inputStream;
    }

    private static OutputStream getOutputStream(File file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        String fileName = file.getName();
        if (fileName.endsWith(".gz")) {
            outputStream = new GZIPOutputStream(outputStream, BUFFER_SIZE);
        } else if (fileName.endsWith(".zip")) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            fileName = fileName.replace(".zip", ".csv");
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);

            outputStream = zipOutputStream;
        }

        return outputStream;
    }
}
