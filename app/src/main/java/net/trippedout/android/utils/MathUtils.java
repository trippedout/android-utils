package net.trippedout.android.utils;

import net.trippedout.android.graphics.PointD;
import android.view.animation.Interpolator;

/**
 * Maths are fun
 */
public class MathUtils {

    public static final float SQRT_2 = (float) (Math.sqrt(2));
    public static final float TWO_PI = (float) (Math.PI * 2);
    public static final float PI = (float) (Math.PI);
    public static final float PI_OVER_2 = (float) (Math.PI/2.0);
    public static final float PI_OVER_4 = (float) (Math.PI/4.0);

    public static final float ROOT_PI_OVER_TWO = (float) (Math.sqrt(Math.PI / 2));

    public static float clamp(float x, float min, float max) {
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        return x;
    }

    public static float degreesToRadians(float degrees) {
        return degrees * PI / 180;
    }

    public static float radiansToDegrees(float degrees) {
        return 180 * degrees / PI;
    }

    public static float map(float x, float a, float b, float u, float v) {
        float p = (x - a) / (b - a);
        return u + p * (v - u);
    }

    public static int mapInt(float x, float a, float b, int u, int v) {
        float p = (x - a) / (b - a);
        return (int)(u + p * (v - u));
    }

    public static int mapInt(float x, float a, float b, int u, int v, Interpolator interpolator) {
        float p = interpolator.getInterpolation((x - a) / (b - a));
        return (int)(u + p * (v - u));
    }

    public static int clampedMapInt(float x, float a, float b, int u, int v) {
        if (x < a) {
            return u;
        } else if (x > b) {
            return v;
        }
        float p = (x - a) / (b - a);
        return (int) (u + p * (v - u));
    }

    public static float clampedMap(float x, float a, float b, float u, float v) {
        if (x <= a) {
            return u;
        } else if (x >= b) {
            return v;
        }
        float p = (x - a) / (b - a);
        return u + p * (v - u);
    }

    public static float clampedMap(float x, float a, float b, float u, float v, Interpolator interpolator) {
        if (x <= a) {
            return u;
        } else if (x >= b) {
            return v;
        }
        float p = interpolator.getInterpolation((x - a) / (b - a));
        return u + p * (v - u);
    }

    public static float map(float x, float a, float b, float u, float v, Interpolator interpolator) {
        float p = interpolator.getInterpolation((x - a) / (b - a));
        return u + p * (v - u);
    }

    public static float mapLoop(float x, float a, float b, float u, float v, Interpolator interpolator) {
        float p = (x - a) / (b - a);
        if (p < 0.5f) {
            p *= 2;
        } else {
            p = 2 * (1 - p);
        }
        p = interpolator.getInterpolation(p);
        return u + p * (v - u);
    }

    public static float cycle(long time, float period) {
        float v = time % period;
        return map(v, 0, period, 0, 1);
    }

    public static float cycle(long time, float period, float phaseShift) {
        float v = (time + phaseShift * period) % period;
        if (v < 0) {
            v += period;
        }
        return map(v, 0, period, 0, 1);
    }

    public static float hang(long time, float period) {
        float v = (time % period) * 2 * ROOT_PI_OVER_TWO / period - ROOT_PI_OVER_TWO;
        return (float) Math.cos(v * v);
    }

    public static float cycleBackwards(long time, float period) {
        return 1 - cycle(time, period);
    }

    public static float oscillate(long time, float period) {
        return (float) Math.sin((double)time * TWO_PI / (double)period);
    }

    // Phase shift is 1-based
    public static float oscillate(long time, float period, float phaseShift) {
        return (float) Math.sin(((time / (double)period) + phaseShift) * TWO_PI);
    }

    // Do a smooth pulse at start of a longer period
    public static float pulse(long time, float period, float pulseWidth, float phaseShift) {
        float t = cycle(time, period, phaseShift) * period / pulseWidth;
        if (t >= 1) {
            return 0;
        } else {
            return MathUtils.map((float) Math.cos(t * TWO_PI), -1, 1, 1, 0);
        }
    }

    public static float fractionalPart(float v) {
        return (float) (v - Math.floor(v));
    }

    public static int quadrant(long time, float period) {
        float v = time * TWO_PI / period;
        float x = (float) Math.cos(v);
        float y = (float) Math.sin(v);
        if (x > 0 && y > 0) {
            return 0;
        } else if (x < 0 && y > 0) {
            return 1;
        } else if (x < 0 && y < 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public static PointD rotate(double x, double y, double angle) {
        PointD point = new PointD();
        return rotate(x, y, angle, point);
    }

    public static PointD rotate(double x, double y, double angle, PointD point) {
        point.x = (float) (Math.cos(angle) * x - Math.sin(angle) * y);
        point.y = (float) (Math.sin(angle) * x + Math.cos(angle) * y);
        return point;
    }

    public static int phase(long time, float period, int n) {
        float v = cycle(time, period);
        return (int)(v * n);
    }

}
