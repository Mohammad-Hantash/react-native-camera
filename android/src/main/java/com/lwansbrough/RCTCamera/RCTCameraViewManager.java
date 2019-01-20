package com.lwansbrough.RCTCamera;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.*;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.List;
import java.util.ArrayList;

public class RCTCameraViewManager extends ViewGroupManager<FrameLayout> {
    private static final String REACT_CLASS = "RCTCamera";
    RCTCameraView cameraView;
    View previewView;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public FrameLayout createViewInstance(ThemedReactContext context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        cameraView = new RCTCameraView(context);

        if(cameraView.isColorExtractionEnabled()){
            previewView = new TextView(context);
            FrameLayout.LayoutParams previewParams= new FrameLayout.LayoutParams(200,200);
            previewParams.gravity = Gravity.CENTER;
            previewParams.setMargins(0,0,380,0);
            previewView.setLayoutParams(previewParams);


            final GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.OVAL);
            gd.setStroke(2, Color.WHITE);
            gd.setSize(50,50);
            gd.setGradientRadius(50);
            previewView.setBackground(gd);
        }

        cameraView.setOnColorChangeListener(new OnColorChangeListener() {
            @Override
            public void onColorChangeListener(String hexColor) {
                int fillColor = Color.parseColor(hexColor);
                gd.setColor(fillColor);

            }
        });

        frameLayout.addView(cameraView);
        if(cameraView.isColorExtractionEnabled()) {
            frameLayout.addView(previewView);
        }

        return frameLayout;
    }

    @ReactProp(name = "aspect")
    public void setAspect(FrameLayout view, int aspect) {
        cameraView.setAspect(aspect);
    }

    @ReactProp(name = "captureMode")
    public void setCaptureMode(FrameLayout view, final int captureMode) {
        // Note that this in practice only performs any additional setup necessary for each mode;
        // the actual indication to capture a still or record a video when capture() is called is
        // still ultimately decided upon by what it in the options sent to capture().
        cameraView.setCaptureMode(captureMode);
    }

    @ReactProp(name = "captureTarget")
    public void setCaptureTarget(FrameLayout view, int captureTarget) {
        // No reason to handle this props value here since it's passed again to the RCTCameraModule capture method
    }

    @ReactProp(name = "type")
    public void setType(FrameLayout view, int type) {
        cameraView.setCameraType(type);
    }

    @ReactProp(name = "captureQuality")
    public void setCaptureQuality(FrameLayout view, String captureQuality) {
        cameraView.setCaptureQuality(captureQuality);
    }

    @ReactProp(name = "torchMode")
    public void setTorchMode(FrameLayout view, int torchMode) {
        cameraView.setTorchMode(torchMode);
    }

    @ReactProp(name = "flashMode")
    public void setFlashMode(FrameLayout view, int flashMode) {
        cameraView.setFlashMode(flashMode);
    }

    @ReactProp(name = "orientation")
    public void setOrientation(FrameLayout view, int orientation) {
        cameraView.setOrientation(orientation);
    }

    @ReactProp(name = "captureAudio")
    public void setCaptureAudio(FrameLayout view, boolean captureAudio) {
        // TODO - implement video mode
    }

    @ReactProp(name = "barcodeScannerEnabled")
    public void setBarcodeScannerEnabled(FrameLayout view, boolean barcodeScannerEnabled) {
        cameraView.setBarcodeScannerEnabled(barcodeScannerEnabled);
    }

    @ReactProp(name = "enableColorExtraction")
    public void setColorExtractionEnabled(FrameLayout view, boolean enableColorExtraction) {
        cameraView.setColorExtractionEnabled(enableColorExtraction);
    }



    @ReactProp(name = "barCodeTypes")
    public void setBarCodeTypes(FrameLayout view, ReadableArray barCodeTypes) {
        if (barCodeTypes == null) {
            return;
        }
        List<String> result = new ArrayList<String>(barCodeTypes.size());
        for (int i = 0; i < barCodeTypes.size(); i++) {
            result.add(barCodeTypes.getString(i));
        }
        cameraView.setBarCodeTypes(result);
    }
}
