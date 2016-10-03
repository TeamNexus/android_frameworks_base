/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class BatteryMeterDrawableTest {

    private Context mContext;
    private Resources mResources;
    private BatteryMeterDrawable mBatteryMeter;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getTargetContext();
        mResources = mContext.getResources();
        mBatteryMeter = new BatteryMeterDrawable(mContext, 0);
    }

    @Test
    public void testGetIntrinsicSize() {
        assertEquals(
                mResources.getDimensionPixelSize(R.dimen.battery_width),
                mBatteryMeter.getIntrinsicWidth());
        assertEquals(
                mResources.getDimensionPixelSize(R.dimen.battery_height),
                mBatteryMeter.getIntrinsicHeight());
    }

    @Test
    public void testDrawNothingBeforeOnBatteryLevelChanged() {
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        verify(canvas, never()).drawPath(any(), any());
        verify(canvas, never()).drawText(anyString(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testDrawImageButNoTextIfPluggedIn() {
        mBatteryMeter.onBatteryLevelChanged(0, true, true);
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        verify(canvas, atLeastOnce()).drawPath(any(), any());
        verify(canvas, never()).drawText(anyString(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testDrawTextIfNotPluggedIn() {
        mBatteryMeter.onBatteryLevelChanged(0, false, false);
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        verify(canvas, times(1)).drawText(anyString(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testDrawNoTextIfPowerSaveEnabled() {
        mBatteryMeter.onBatteryLevelChanged(0, false, false);
        mBatteryMeter.onPowerSaveChanged(true);
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        verify(canvas, never()).drawText(anyString(), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testDrawTextWarningAtCriticalLevel() {
        int criticalLevel = mResources.getInteger(
                com.android.internal.R.integer.config_criticalBatteryWarningLevel);
        mBatteryMeter.onBatteryLevelChanged(criticalLevel, false, false);
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        String warningString = mResources.getString(R.string.battery_meter_very_low_overlay_symbol);
        verify(canvas, times(1)).drawText(eq(warningString), anyFloat(), anyFloat(), any());
    }

    @Test
    public void testDrawTextNoWarningAboveCriticalLevel() {
        int criticalLevel = mResources.getInteger(
                com.android.internal.R.integer.config_criticalBatteryWarningLevel);
        mBatteryMeter.onBatteryLevelChanged(criticalLevel + 1, false, false);
        final Canvas canvas = mock(Canvas.class);
        mBatteryMeter.draw(canvas);
        String warningString = mResources.getString(R.string.battery_meter_very_low_overlay_symbol);
        verify(canvas, never()).drawText(eq(warningString), anyFloat(), anyFloat(), any());
    }
}
