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

import static android.provider.Settings.Secure.MDNIE_MODE;
import static android.provider.Settings.Secure.MDNIE_SCENARIO;
import static android.provider.Settings.Secure.MDNIE_COLOR_CORRECTION_RED;
import static android.provider.Settings.Secure.MDNIE_COLOR_CORRECTION_GREEN;
import static android.provider.Settings.Secure.MDNIE_COLOR_CORRECTION_BLUE;

import java.io.IOException;

/**
 * @hide
 */
public class MdnieManager {

    private static final String TAG = "MdnieManager";

    private static final String PATH_MDNIE_MODE = "/sys/class/mdnie/mdnie/mode";
    private static final String PATH_MDNIE_SCENARIO = "/sys/class/mdnie/mdnie/scenario";
    private static final String PATH_MDNIE_SENSORRGB = "/sys/class/mdnie/mdnie/sensorRGB";

    private final Context mContext;

    public MdnieManager(final Context context) {
        this.mContext = context;
    }

    public void apply() {
        this.applyMdnieMode();
        this.applyMdnieScenario();
        this.applyMdnieSensorRGB();
    }

    public void applyMdnieMode() {
        Log.i(TAG, "applyMdnieMode");
        try {
            FileUtils.stringToFile(PATH_MDNIE_MODE,
                    String.valueOf(Settings.Secure.getIntForCurrentUser(mContext, MDNIE_MODE, 0)));
        } catch (IOException e) { }
    }

    public void applyMdnieScenario() {
        Log.i(TAG, "applyMdnieScenario");
        try {
            FileUtils.stringToFile(PATH_MDNIE_SCENARIO,
                    String.valueOf(Settings.Secure.getIntForCurrentUser(mContext, MDNIE_SCENARIO, 0)));
        } catch (IOException e) { }
    }

    public void applyMdnieSensorRGB() {
        Log.i(TAG, "applyMdnieSensorRGB");
        try {
            FileUtils.stringToFile(PATH_MDNIE_SENSORRGB,
                    Settings.Secure.getIntForCurrentUser(mContext, MDNIE_COLOR_CORRECTION_RED, 255) + " " +
                    Settings.Secure.getIntForCurrentUser(mContext, MDNIE_COLOR_CORRECTION_GREEN, 255) + " " +
                    Settings.Secure.getIntForCurrentUser(mContext, MDNIE_COLOR_CORRECTION_BLUE, 255) + "\n");
        } catch (IOException e) { }
    }

}
