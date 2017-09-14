/*
 * Copyright (C) 2017 TeamNexus
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

package nexus.display;

import android.content.Context;
import android.os.FileUtils;
import android.provider.Settings;
import android.util.Log;

import nexus.hardware.MdnieDisplay;
import nexus.provider.NexusSettings;
import static nexus.provider.NexusSettings.MDNIE_MODE;
import static nexus.provider.NexusSettings.MDNIE_SCENARIO;
import static nexus.provider.NexusSettings.MDNIE_COLOR_CORRECTION_RED;
import static nexus.provider.NexusSettings.MDNIE_COLOR_CORRECTION_GREEN;
import static nexus.provider.NexusSettings.MDNIE_COLOR_CORRECTION_BLUE;

import java.io.IOException;

/**
 * @hide
 */
public class MdnieManager {

    private static final String TAG = "MdnieManager";

    public static void apply(final Context context) {
        applyMdnieMode(context);
        applyMdnieScenario(context);
        applyMdnieSensorRGB(context);
    }

    public static void applyMdnieMode(final Context context) {
        Log.i(TAG, "applyMdnieMode");
        MdnieDisplay.setMdnieMode(NexusSettings.getIntForCurrentUser(context, MDNIE_MODE, 0));
    }

    public static void applyMdnieScenario(final Context context) {
        Log.i(TAG, "applyMdnieScenario");
        MdnieDisplay.setMdnieScenario(NexusSettings.getIntForCurrentUser(context, MDNIE_SCENARIO, 0));
    }

    public static void applyMdnieSensorRGB(final Context context) {
        Log.i(TAG, "applyMdnieSensorRGB");
        MdnieDisplay.setColorCorrection(
                NexusSettings.getIntForCurrentUser(context, MDNIE_COLOR_CORRECTION_RED, 255),
                NexusSettings.getIntForCurrentUser(context, MDNIE_COLOR_CORRECTION_GREEN, 255),
                NexusSettings.getIntForCurrentUser(context, MDNIE_COLOR_CORRECTION_BLUE, 255));
    }

}
