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

package nexus.os;

import android.annotation.SystemApi;
import android.annotation.RequiresPermission;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemProperties;
import android.util.Log;

import nexus.io.NexusFileUtils;
import nexus.provider.ApplicationSettings;
import static nexus.provider.ApplicationSettings.PREF_SKIP_SU_REQUEST;

/**
 * @hide
 */
@SystemApi
public class SuManager {

    private final String TAG = "SuManager";

    private static final String ROOT_ACCESS_PROPERTY = "persist.sys.nexussud.enabled";

    private Context mContext;
    private String mPackageName;
    private PackageInfo mPackageInfo;
    private ApplicationSettings mAppSettings;

    private PackageManager mPm;
    private AppOpsManager mAppOpsManager;

    /**
     * @hide
     */
    @SystemApi
    public SuManager(Context context, String packageName) {
        mContext = context;
        mPackageName = packageName;

        mPm = context.getPackageManager();
        try {
            mPackageInfo = mPm.getPackageInfo(mPackageName,
                    PackageManager.MATCH_DISABLED_COMPONENTS |
                    PackageManager.MATCH_ANY_USER |
                    PackageManager.GET_SIGNATURES |
                    PackageManager.GET_PERMISSIONS);
        } catch (NameNotFoundException e) {
            mPackageInfo = null;
            Log.e(TAG, "Exception when retrieving package:" + packageName, e);
        }

        mAppSettings = new ApplicationSettings(mContext, mPackageName);
        mAppOpsManager = (AppOpsManager) mContext.getSystemService(Context.APP_OPS_SERVICE);
    }

    /**
     * @hide
     */
    @SystemApi
    public boolean isAllowed() {
        if (mPackageInfo == null) {
            Log.e(TAG, "mPackageInfo was not initialized, consider to re-create this instance of SuManager");
            return false;
        }

        try {
            return mAppOpsManager.checkOp(AppOpsManager.OP_SU, mPackageInfo.applicationInfo.uid, mPackageName) == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {
            // we'll take exception as a clear no
            return false;
        }
    }

    /**
     * @hide
     */
    @SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
    public void setAllowed(boolean state) {
        if (mPackageInfo == null) {
            Log.e(TAG, "mPackageInfo was not initialized, consider to re-create this instance of SuManager");
            return;
        }

        mAppOpsManager.setMode(AppOpsManager.OP_SU, mPackageInfo.applicationInfo.uid, mPackageName,
                state ? AppOpsManager.MODE_ALLOWED : AppOpsManager.MODE_ERRORED);
    }

    /**
     * @hide
     */
    @SystemApi
    public boolean needRequestDialog() {
        return mAppSettings.getBoolean(PREF_SKIP_SU_REQUEST, true);
    }

    /**
     * @hide
     */
    @SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
    public void setRequestDialog(boolean state) {
        mAppSettings.putBoolean(PREF_SKIP_SU_REQUEST, state);
    }

    /**
     * @hide
     */
    @SystemApi
    public static boolean isRootAvailable() {
        return NexusFileUtils.isFile("/system/xbin/nexussu");
    }

    /**
     * @hide
     */
    @SystemApi
    public static boolean isRootEnabled() {
        if (!isRootAvailable()) {
            return false;
        }
        int value = SystemProperties.getInt(ROOT_ACCESS_PROPERTY, 0);
        return (value & 1) == 1;
    }

    /**
     * @hide
     */
    @SystemApi
    public static boolean isRootRunning() {
        if (!isRootAvailable()) {
            return false;
        }
        return SystemProperties.get("init.svc.nexussud", "absent").equals("running");
    }

    /**
     * @hide
     */
    @SystemApi
    public static boolean isRootFullyEnabled() {
        return isRootEnabled() && isRootRunning();
    }

    /**
     * @hide
     */
    @SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
    public static void setRootEnabled(boolean state) {
        SystemProperties.set(ROOT_ACCESS_PROPERTY, state ? "1" : "0");
    }

}
