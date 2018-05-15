package com.rtmp_android;

import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.pedro.builder.RtmpBuilder;


import net.ossrs.rtmp.ConnectCheckerRtmp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONException;

public class RTMPModule extends ReactContextBaseJavaModule {
    private static RTMPSurfaceView surfaceView;
    private static RtmpBuilder rtmpBuilder;
    private static boolean isSurfaceCreated;

    private String url = "";

    private int width = 640;
    private int height = 480;
    private int fps = 30;
    private int bitRate = 300 * 1024; // in kbps
    private int rotation = 90;
    private int iFrameInterval = 4;
    private boolean hardwareRotation = false;

    public RTMPModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RTMPModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("width", height);
        constants.put("height", height);
        return constants;
    }

    @ReactMethod
    public void show(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void changeSettings(ReadableMap settings, Promise promise) {

        // for (settings.Entry<String, Integer> me : set) {
        //     System.out.print(me.getKey() + ": ");
        //     System.out.println(me.getValue());
        // }

        // Toast.makeText(getReactApplicationContext(), settings.getString("width"), Toast.LENGTH_SHORT).show();

        if (rtmpBuilder != null && rtmpBuilder.isStreaming() && settings != null) {
            rtmpBuilder.stopStream();
            this.startStreamWithParams(url, promise, settings);
        } else if (rtmpBuilder != null && !rtmpBuilder.isStreaming() && settings != null ){
            // rtmpBuilder.stopStream();
            this.startStreamWithParams(url, promise, settings);
        }
        // promise.resolve(rtmpBuilder.isStreaming());
    }

    public void startStreamWithParams(String rtmpUrl, Promise promise, ReadableMap params) {
        // Toast.makeText(getReactApplicationContext(), params.get("width"), Toast.LENGTH_SHORT).show();
        
        // for (params.Entry<String, Integer> me : set) {
        //     System.out.print(me.getKey() + ": ");
        //     System.out.println(me.getValue());
        // }
        if (isSurfaceCreated) {
            
            if (rtmpBuilder != null && !rtmpBuilder.isStreaming() && rtmpBuilder.prepareAudio()
                    && rtmpBuilder.prepareVideo(
                        params.hasKey("width") ? params.getInt("width") : width,
                        params.hasKey("height") ? params.getInt("height") : height,
                        params.hasKey("fps") ? params.getInt("fps") : fps,
                        params.hasKey("bitRate") ? params.getInt("bitRate") : bitRate,
                        hardwareRotation, rotation)) {
                rtmpBuilder.startStream(rtmpUrl);
            } else {
                Toast.makeText(getReactApplicationContext(), "Failed to preparing RTMP builder.", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(getReactApplicationContext(), "Surface view is not ready.", Toast.LENGTH_SHORT).show();
        }

        promise.resolve(rtmpBuilder.isStreaming());
    }


    @ReactMethod
    public void startStream(String rtmpUrl, Promise promise ) {
        if (isSurfaceCreated) {
            url = rtmpUrl;
            if (rtmpBuilder != null && !rtmpBuilder.isStreaming() && rtmpBuilder.prepareAudio() && rtmpBuilder.prepareVideo(width, height, fps, bitRate, hardwareRotation, rotation)) {
                rtmpBuilder.startStream(rtmpUrl);
            } else {
                Toast.makeText(getReactApplicationContext(), "Failed to preparing RTMP builder.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getReactApplicationContext(), "Surface view is not ready.", Toast.LENGTH_SHORT).show();
        }

        promise.resolve(rtmpBuilder.isStreaming());
    }

    @ReactMethod
    public void stopStream(Promise promise) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
        }

        promise.resolve(!rtmpBuilder.isStreaming());
    }

    @ReactMethod
    public void switchCamera(Promise promise) {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.switchCamera();
        }

        promise.resolve(rtmpBuilder.isStreaming());
    }

    public static void setSurfaceView(RTMPSurfaceView surface) {
        surfaceView = surface;
        rtmpBuilder = new RtmpBuilder(surfaceView, new ConnectCheckerRtmp() {
            @Override
            public void onConnectionSuccessRtmp() {

            }

            @Override
            public void onConnectionFailedRtmp() {

            }

            @Override
            public void onDisconnectRtmp() {

            }

            @Override
            public void onAuthErrorRtmp() {

            }

            @Override
            public void onAuthSuccessRtmp() {

            }
        });

        isSurfaceCreated = true;
    }

    public static void destroySurfaceView() {
        if (rtmpBuilder != null && rtmpBuilder.isStreaming()) {
            rtmpBuilder.stopStream();
            rtmpBuilder = null;
        }

        isSurfaceCreated = false;
    }
}
