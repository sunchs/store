package com.sunchs.store.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil
{
    public static String getInputStream(InputStream inputStream) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader reader = new BufferedReader(streamReader);
            String buff = "";
            while((buff = reader.readLine()) != null) {
                buffer.append(buff);
            }
            reader.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (streamReader != null) {
                streamReader.close();
            }
        }
        return buffer.toString();
    }
}
