package com.android.camera.ui;

import android.graphics.Rect;

import com.android.camera.ListPreference;

import javax.microedition.khronos.opengles.GL11;

public class GLOptionHeader extends GLView {
    private static final int FONT_COLOR = 0xFF979797;

    private final ListPreference mPreference;
    private final StringTexture mTitle;
    private NinePatchTexture mBackground;

    public GLOptionHeader(ListPreference preference) {
        mPreference = preference;
        mTitle = StringTexture.newInstance(
                preference.getTitle(), 16, FONT_COLOR);
    }

    public ListPreference getPreference() {
        return mPreference;
    }

    public void setBackground(NinePatchTexture background) {
        if (mBackground == background) return;
        mBackground = background;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        new MeasureHelper(this)
                .setPreferedContentSize(mTitle.getWidth(), mTitle.getHeight())
                .measure(widthSpec, heightSpec);
    }

    @Override
    protected void render(GLRootView root, GL11 gl) {
        if (mBackground != null) {
            mBackground.setSize(getWidth(), getHeight());
            if (mBackground.bind(root, gl)) mBackground.draw(root, 0, 0);
        }
        if (mTitle.bind(root, gl)) {
            Rect p = mPaddings;
            mTitle.draw(root, p.left, p.top);
        }
    }
}