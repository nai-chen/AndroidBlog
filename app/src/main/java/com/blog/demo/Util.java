package com.blog.demo;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * Created by cn on 2017/3/30.
 */

public class Util {
    private static NumberFormat format;
    static {
        format = new DecimalFormat(".00");
    }

    public static String copyFromAsset(Context context, String fileName) {
        File dir = Environment.getExternalStorageDirectory();
        String newFilePath = dir.getAbsolutePath() + File.separator + fileName;

        if (new File(newFilePath).exists()) {
            return newFilePath;
        } else {
            InputStream input = null;
            OutputStream output = null;

            try {
                input = context.getAssets().open(fileName);
                output = new FileOutputStream(new File(newFilePath));

                int length = 1024;
                byte[] buffer = new byte[length];

                while ((length = input.read(buffer)) >= 0) {
                    output.write(buffer, 0, length);
                }
                return newFilePath;
            } catch (Exception e) {
                return null;
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                    }
                }
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static String getDecimalValue(double value) {
        return format.format(value);
    }

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
