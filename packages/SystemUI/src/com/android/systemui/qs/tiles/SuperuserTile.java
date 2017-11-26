/*
 * Copyright (c) 2016, The Android Open Source Project
 * Contributed by the Paranoid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.widget.Switch;

import com.android.systemui.R;
import com.android.systemui.qs.QSHost;
import com.android.systemui.plugins.qs.QSTile.BooleanState;
import com.android.systemui.qs.tileimpl.QSTileImpl;

import nexus.os.SuManager;

/** Quick settings tile: Enable/Disable Global Superuser **/
public class SuperuserTile extends QSTileImpl<BooleanState> {

    private boolean mListening;

    public SuperuserTile(QSHost host) {
        super(host);
    }

    @Override
    public BooleanState newTileState() {
        return new BooleanState();
    }

    @Override
    public boolean isAvailable() {
        return SuManager.isRootAvailable();
    }

    @Override
    public void setListening(boolean listening) {
    }

    @Override
    protected void handleUserSwitch(int newUserId) {
    }

    @Override
    public Intent getLongClickIntent() {
        return new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
    }

    @Override
    protected void handleClick() {
        if (SuManager.isRootEnabled()) {
            SuManager.setRootEnabled(false);
        } else {
            SuManager.setRootEnabled(true);
        }
        refreshState();
    }

    @Override
    protected void handleSecondaryClick() {
        handleClick();
    }

    @Override
    public CharSequence getTileLabel() {
        return mContext.getString(R.string.quick_settings_superuser_title);
    }

    @Override
    protected void handleUpdateState(BooleanState state, Object arg) {
        final Drawable mEnable = mContext.getDrawable(R.drawable.stat_sys_su);
        final Drawable mDisable = mContext.getDrawable(R.drawable.stat_sys_su_disabled);
        state.value = SuManager.isRootEnabled();
        state.label = mContext.getString(R.string.quick_settings_superuser_title);
        state.icon = new DrawableIcon(state.value ? mEnable : mDisable);
        state.expandedAccessibilityClassName = Switch.class.getName();
        state.contentDescription = state.label;
    }

    @Override
    public int getMetricsCategory() {
        return -1;
    }

}
