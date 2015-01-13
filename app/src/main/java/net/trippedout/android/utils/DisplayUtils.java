package net.trippedout.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * utilities for dealing with displays and pixel transfers
 */
public class DisplayUtils
{
    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static float pixelsToDips(Context context, float pixelValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float dip = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, metrics);
        return pixelValue / dip;
    }
}
