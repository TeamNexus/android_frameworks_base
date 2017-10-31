/*
 * Copyright (C) 2006 The Android Open Source Project
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

package nexus.provider;

import android.content.Context;

import nexus.provider.NexusSettings;

/**
 * @hide
 */
public final class DefaultSettingsApplier {

    /**
     * @hide
     */
    public static final void tryApply(final Context context, final int user) {
        boolean defaultSettingsApplied = NexusSettings.getBoolForUser(context, NexusSettings.DEFAULT_SETTINGS_APPLIED, false, user);
        if (!defaultSettingsApplied) {
            NexusSettings.putBoolForUser(context, NexusSettings.DEFAULT_SETTINGS_APPLIED, true, user);
            applyDefaultSettings(context, user);
        }
    }

    /**
     * @hide
     */
    private static final void applyDefaultSettings(final Context context, final int user) {
        NexusSettings.putIntForUser(context, NexusSettings.CRITICAL_DREAMING_BATTERY_PERCENTAGE, 25, user);
        NexusSettings.putBoolForUser(context, NexusSettings.FINGERPRINT_UNLOCK_AFTER_REBOOT, false, user);
        NexusSettings.putIntForUser(context, NexusSettings.KEYGUARD_CLOCK_SHOW_SECONDS, 3, user);
        NexusSettings.putIntForUser(context, NexusSettings.MDNIE_MODE, 4, user);
        NexusSettings.putIntForUser(context, NexusSettings.MDNIE_SCENARIO, 0, user);
        NexusSettings.putIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_RED, 255, user);
        NexusSettings.putIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_GREEN, 255, user);
        NexusSettings.putIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_BLUE, 255, user);
    }

}