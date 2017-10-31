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

package com.nexus.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.nexus.server.intent.BootCompletedIntent;
import com.nexus.server.intent.ScreenOnIntent;
import com.nexus.server.intent.UserForegroundIntent;

class IntentReceiver extends BroadcastReceiver {

    private IntentFilter filter;

    public IntentReceiver(Context context) {
        filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        context.registerReceiver(this, filter);
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        new Thread() {
            @Override
            public void run() {
                onReceiveAsync(context, intent);
            }
        }.start();
    }

    private void onReceiveAsync(final Context context, final Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_BOOT_COMPLETED:
                BootCompletedIntent.onReceive(context, intent);
                break;

            case Intent.ACTION_SCREEN_ON:
                ScreenOnIntent.onReceive(context, intent);
                break;

            case Intent.ACTION_USER_FOREGROUND:
                UserForegroundIntent.onReceive(context, intent);
                break;
        }
    }

}
