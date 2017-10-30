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

package nexus.provider;

import android.annotation.SystemApi;
import android.annotation.RequiresPermission;
import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.HashSet;

/**
 * Utilities for system-wide per-app settings used to e.g. change
 * the apperance of the application
 *
 * @hide
 */
public class ApplicationSettings {

	/**
	 * @hide
	 */
	public static final String PREF_DPI = "dpi";

	private static final String SHARED_PREF_FILE = "app_settings";
	private static final HashSet<String> ALLOWED_PREFS;
	static {
		ALLOWED_PREFS = new HashSet<>();
		ALLOWED_PREFS.add(PREF_DPI);
	}

	private Context mContext;
	private String mPackageName;
	private String mPrefPrefix;

	/**
	 * @hide
	 */
	public ApplicationSettings(Context context, String packageName) {
		this.mContext = context;
		this.mPackageName = packageName;
		this.mPrefPrefix = SHARED_PREF_FILE + ":" + packageName + "+";
	}

	/**
	 * @hide
	 */
    @SystemApi
	public boolean getBoolean(String pref, boolean def) {
		assertPref(pref);
		return NexusSettings.getBoolForCurrentUser(mContext, mPrefPrefix + pref, def);
	}

	/**
	 * @hide
	 */
    @SystemApi
	public int getInt(String pref, int def) {
		assertPref(pref);
		return NexusSettings.getIntForCurrentUser(mContext, mPrefPrefix + pref, def);
	}

	/**
	 * @hide
	 */
    @SystemApi
	public String getString(String pref, String def) {
		assertPref(pref);
		String ret = NexusSettings.getStringForCurrentUser(mContext, mPrefPrefix + pref);
		if (ret == null || ret.equals(""))
			return def;
		return ret;
	}

	/**
	 * @hide
	 */
	@SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
	public void putBoolean(String pref, boolean value) {
		assertPref(pref);
		NexusSettings.putBoolForCurrentUser(mContext, mPrefPrefix + pref, value);
	}

	/**
	 * @hide
	 */
	@SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
	public void putInt(String pref, int value) {
		assertPref(pref);
		NexusSettings.putIntForCurrentUser(mContext, mPrefPrefix + pref, value);
	}

	/**
	 * @hide
	 */
	@SystemApi
	@RequiresPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS)
	public void putString(String pref, String value) {
		assertPref(pref);
		NexusSettings.putStringForCurrentUser(mContext, mPrefPrefix + pref, value);
	}

	private void assertPref(String pref) {
		if (!ALLOWED_PREFS.contains(pref))
			throw new IllegalArgumentException("Invalid application-preference: " + pref);
	}

}
