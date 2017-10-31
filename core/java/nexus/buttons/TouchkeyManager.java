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

package nexus.buttons;

import android.content.Context;
import android.os.FileUtils;
import android.os.UserHandle;
import android.provider.Settings;
import static android.provider.Settings.System.DEV_FORCE_SHOW_NAVBAR;
import android.util.Log;

import nexus.hardware.Touchkeys;
import nexus.provider.NexusSettings;
import static nexus.provider.NexusSettings.TOUCHKEYS_ENABLED;

import java.io.IOException;

/**
 * @hide
 */
public class TouchkeyManager {

    private static final String TAG = "TouchkeyManager";

    public static void apply(final Context context) {
        boolean touchkeysEnabled = NexusSettings.getBoolForCurrentUser(context, TOUCHKEYS_ENABLED, true);

        Log.i(TAG, "apply");
        Touchkeys.setState(touchkeysEnabled);
        Settings.System.putIntForUser(context.getContentResolver(), DEV_FORCE_SHOW_NAVBAR,
                (!touchkeysEnabled ? 1 : 0), UserHandle.USER_CURRENT);
    }

}
