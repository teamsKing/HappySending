package com.team.happysending.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class StreamUtils  {
        /**
         * @param inputStream inputStream
         * @return 字符串转换之后的
         */
        public static String streamToString(InputStream inputStream) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }

                String result = out.toString();
                out.close();
                inputStream.close();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
}
