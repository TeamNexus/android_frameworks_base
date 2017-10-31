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
        putDefaultIntForUser(context, NexusSettings.CRITICAL_DREAMING_BATTERY_PERCENTAGE, 25, user);
        putDefaultBoolForUser(context, NexusSettings.FINGERPRINT_UNLOCK_AFTER_REBOOT, false, user);
        putDefaultIntForUser(context, NexusSettings.KEYGUARD_CLOCK_SHOW_SECONDS, 3, user);
        putDefaultIntForUser(context, NexusSettings.MDNIE_MODE, 4, user);
        putDefaultIntForUser(context, NexusSettings.MDNIE_SCENARIO, 0, user);
        putDefaultIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_RED, 255, user);
        putDefaultIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_GREEN, 255, user);
        putDefaultIntForUser(context, NexusSettings.MDNIE_COLOR_CORRECTION_BLUE, 255, user);
        putDefaultBoolForUser(context, NexusSettings.TOUCHKEYS_ENABLED, true, user);
        putDefaultBoolForUser(context, NexusSettings.TOUCHKEYS_BACKLIGHT_DIRECT_ONLY, true, user);
        putDefaultIntForUser(context, NexusSettings.TOUCHKEYS_BACKLIGHT_TIMEOUT, 5000, user);
    }

    private static final void putDefaultBoolForUser(final Context context, String name, boolean value, final int user) {
        if (!NexusSettings.existsForUser(context, name, user))
			NexusSettings.putBoolForUser(context, name, value, user);
    }

    private static final void putDefaultIntForUser(final Context context, String name, int value, final int user) {
        if (!NexusSettings.existsForUser(context, name, user))
			NexusSettings.putIntForUser(context, name, value, user);
    }

    private static final void putDefaultStringForUser(final Context context, String name, String value, final int user) {
        if (!NexusSettings.existsForUser(context, name, user))
			NexusSettings.putStringForUser(context, name, value, user);
    }

}