package com.mycompany.app;

import org.pcap4j.core.*;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import org.xerial.snappy.SnappyOutputStream;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class PcapCompressor {

    public static void compressEntirePcap(String inputPcap, String algorithm) throws IOException {
        String outputFileName = "compressed." + algorithm.toLowerCase();

        FileInputStream fis = new FileInputStream(inputPcap);
        OutputStream fos = new FileOutputStream(outputFileName);
        OutputStream compressedOut = getCompressedOutputStream(fos, algorithm);

        byte[] buffer = new byte[1024];
        int bytesRead;
        AtomicLong totalBytes = new AtomicLong();

        long startTime = System.currentTimeMillis();

        while ((bytesRead = fis.read(buffer)) != -1) {
            compressedOut.write(buffer, 0, bytesRead);
            totalBytes.addAndGet(bytesRead);
        }

        compressedOut.close();
        fos.close();
        fis.close();

        long endTime = System.currentTimeMillis();

        System.out.println("\nElapsed Time: " + (endTime - startTime) + " ms (" + algorithm + ")");
        System.out.println("Compressed file saved as: " + outputFileName);
        System.out.println("Total bytes processed: " + totalBytes.get() + " (" + algorithm + ")");
    }

    private static OutputStream getCompressedOutputStream(OutputStream out, String algorithm) throws IOException {
        switch (algorithm.toLowerCase()) {
            case "bzip2":
                return new BZip2CompressorOutputStream(out);
            case "gzip":
                return new GzipCompressorOutputStream(out);
            case "lz4":
                return new LZ4BlockOutputStream(out);
            case "snappy":
                return new SnappyOutputStream(out);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java -jar pcap-compressor.jar <pcap file path> <compression algorithm>");
            System.out.println("Supported algorithms: gzip, bzip2, lz4, snappy");
            return;
        }
        compressEntirePcap(args[0], args[1]);
    }
}