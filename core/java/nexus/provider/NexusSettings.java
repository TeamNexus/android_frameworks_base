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
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;

/**
 * Global user-level preferences for NexusOS-abilities
 * @hide
 */
public final class NexusSettings {

    /**
     * Prefix of all NexusOS-settings. May not be changed too often as it clears all
	 * settings set by the user.
     * @hide
     */
    public static final String NEXUS_SETTINGS_PREFIX = "nexus::";
	
    /**
     * Whether user can unlock the device with the fingerprint
     * @hide
     */
    public static final String FINGERPRINT_UNLOCK_AFTER_REBOOT = "fingerprint_unlock_after_reboot";

    /**
     * Whether the lockscreen and/or doze-clock shows seconds
     * Takes 0 (Off), 1 (Lockscreen), 2 (Doze) and 3 (Lockscreen and doze) as values
     * @hide
     */
    public static final String KEYGUARD_CLOCK_SHOW_SECONDS = "keyguard_clock_show_seconds";

    /**
     * Whether to keep fingerprint on even when screen is off
     * @hide
     */
    public static final String FINGERPRINT_UNLOCK_WHILE_SLEEPING = "fingerprint_unlock_while_sleeping";

    /**
     * Whether to keep fingerprint on even if Ambient/Always-on-Display
     * @hide
     */
    public static final String FINGERPRINT_UNLOCK_WHILE_DOZING = "fingerprint_unlock_while_dozing";

    /**
     * Wrapper for getIntForCurrentUser(). Returns true if the stored integerfor
     * the current user is not 0, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored boolean/def is nothing was found
     */
    public static final boolean getBoolForCurrentUser(Context context, String name, boolean def) {
        return Settings.Secure.getIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def ? 1 : 0,
                UserHandle.myUserId()) != 0;
    }

    /**
     * Wrapper for getIntForCurrentUser(). Returns true if the stored integerfor
     * the current user is not 0, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored boolean/def is nothing was found
     */
    public static final boolean getBoolForUser(Context context, String name, boolean def, int user) {
        return Settings.Secure.getIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def ? 1 : 0,
                user) != 0;
    }

    /**
     * Wrapper for putIntForCurrentUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putBoolForCurrentUser(Context context, String name, boolean value) {
        Settings.Secure.putIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value ? 1 : 0,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for putIntForCurrentUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putBoolForUser(Context context, String name, boolean value, int user) {
        Settings.Secure.putIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value ? 1 : 0,
                user);
    }

    /**
     * Wrapper for getIntForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored integer/def is nothing was found
     */
    public static final int getIntForCurrentUser(Context context, String name, int def) {
        return Settings.Secure.getIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for getIntForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored integer/def is nothing was found
     */
    public static final int getIntForUser(Context context, String name, int def, int user) {
        return Settings.Secure.getIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def,
                user);
    }

    /**
     * Wrapper for putIntForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putIntForCurrentUser(Context context, String name, int value) {
        Settings.Secure.putIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for putIntForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putIntForUser(Context context, String name, int value, int user) {
        Settings.Secure.putIntForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                user);
    }

    /**
     * Wrapper for getFloatForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored integer/def is nothing was found
     */
    public static final float getFloatForCurrentUser(Context context, String name, float def) {
        return Settings.Secure.getFloatForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for getFloatForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @param def the default value if no setting was found
     * @return return stored integer/def is nothing was found
     */
    public static final float getFloatForUser(Context context, String name, float def, int user) {
        return Settings.Secure.getFloatForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                def,
                user);
    }

    /**
     * Wrapper for putFloatForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putFloatForCurrentUser(Context context, String name, float value) {
        Settings.Secure.putFloatForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for putFloatForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putFloatForUser(Context context, String name, float value, int user) {
        Settings.Secure.putFloatForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                user);
    }

    /**
     * Wrapper for getStringForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @return return stored string
     */
    public static final String getStringForCurrentUser(Context context, String name) {
        return Settings.Secure.getStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for getStringForUser(). Returns the requested setting for
     * the current user, returning a default value if no stored value
     * was found
     *
     * @param context the context to use
     * @param name name of the setting to find
     * @return return stored string
     */
    public static final String getStringForUser(Context context, String name, int user) {
        return Settings.Secure.getStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                user);
    }

    /**
     * Wrapper for putStringForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putStringForCurrentUser(Context context, String name, String value) {
        Settings.Secure.putStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                UserHandle.myUserId());
    }

    /**
     * Wrapper for putStringForUser(). Saves the value with to the passed
     * name for the current user
     *
     * @param context the context to use
     * @param name name of the setting to update
     * @param value the new value of the setting
     */
    public static final void putStringForUser(Context context, String name, String value, int user) {
        Settings.Secure.putStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                value,
                user);
    }

    /**
     * Checks if the given setting was set for the current user
     *
     * @param context the context to use
     * @param name name of the setting to check
     */
    public static final boolean existsForCurrentUser(Context context, String name) {
        String result = Settings.Secure.getStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                UserHandle.myUserId());
		return result != null;
    }

    /**
     * Checks if the given setting was set for the given user
     *
     * @param context the context to use
     * @param name name of the setting to check
     * @param user ID of the user
     */
    public static final boolean existsForUser(Context context, String name, int user) {
        String result = Settings.Secure.getStringForUser(
                context.getContentResolver(),
                NEXUS_SETTINGS_PREFIX + name,
                user);
		return result != null;
    }

    /**
     * Wrapper for System.Secure.getUriFor(String). Converts given setting-names
	 * to NexusSettings-compatible ones
	 *
     * @param name to look up in the table
     * @return the corresponding content URI, or null if not present
     */
    public static Uri getUriFor(String name) {
        return Settings.Secure.getUriFor(NEXUS_SETTINGS_PREFIX + name);
    }

}
