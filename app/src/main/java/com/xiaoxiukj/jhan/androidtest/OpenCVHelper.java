package com.xiaoxiukj.jhan.androidtest;

/**
 * Created by jhan on 10/13/17.
 */

public class OpenCVHelper {
    static {
        System.loadLibrary("OpenCV");
    }
    public static native int[] gray(int[] buf, int w, int h);
}
