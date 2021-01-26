/*
 * Copyright 2019-2020 Pranav Pandey
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

package com.pranavpandey.android.dynamic.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.preference.PreferenceManager;

import java.util.Set;

/**
 * Helper class to handle shared preferences operations like saving or retrieving the values from
 * default shared preferences. It must be initialized once before accessing its methods.
 */
public class DynamicPreferences {

    /**
     * Singleton instance of {@link DynamicPreferences}.
     */
    private static DynamicPreferences sInstance;

    /**
     * Context to retrieve the resources.
     */
    private Context mContext;

    /**
     * Making default constructor private so that it cannot be initialized without a context.
     * <p>Use {@link #initializeInstance(Context)} instead.
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    protected DynamicPreferences() { }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param context The context to retrieve the resources.
     */
    private DynamicPreferences(@NonNull Context context) {
        this.mContext = context;
    }

    /**
     * Initialize preferences when application starts.
     * <p>Must be initialized once.
     *
     * @param context The context to retrieve resources.
     */
    public static synchronized void initializeInstance(@Nullable Context context) {
        if (context == null) {
            throw new NullPointerException("Context should not be null");
        }

        if (sInstance == null) {
            sInstance = new DynamicPreferences(context);
        }
    }

    /**
     * Retrieves the singleton instance of {@link DynamicPreferences}.
     * <p>Must be called before accessing the public methods.
     *
     * @return The singleton instance of {@link DynamicPreferences}.
     */
    public static synchronized @NonNull DynamicPreferences getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(DynamicPreferences.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return sInstance;
    }

    /**
     * Returns the context used by this instance.
     *
     * @return The context to retrieve the resources.
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Set a boolean value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The preferences name to store the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void save(@Nullable String preferences, @Nullable String key, boolean value) {
        if (preferences == null || key == null) {
            return;
        }
        
        mContext.getSharedPreferences(preferences, Context.MODE_PRIVATE)
                .edit().putBoolean(key, value).apply();
    }

    /**
     * Set a boolean value in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void save(@Nullable String key, boolean value) {
        if (key == null) {
            return;
        }
        
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit().putBoolean(key, value).apply();
    }

    /**
     * Set a integer value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The preferences name to store the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void save(@Nullable String preferences, @Nullable String key, int value) {
        if (preferences == null || key == null) {
            return;
        }
        
        mContext.getSharedPreferences(preferences, Context.MODE_PRIVATE)
                .edit().putInt(key, value).apply();
    }

    /**
     * Set an integer value in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void save(@Nullable String key, int value) {
        if (key == null) {
            return;
        }
        
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit().putInt(key, value).apply();
    }

    /**
     * Set a float value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The preferences name to store the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void save(@Nullable String preferences, @Nullable String key, float value) {
        if (preferences == null || key == null) {
            return;
        }
        
        mContext.getSharedPreferences(preferences, Context.MODE_PRIVATE)
                .edit().putFloat(key, value).apply();
    }
    
    /**
     * Set a float value in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void save(@Nullable String key, float value) {
        if (key == null) {
            return;
        }
        
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit().putFloat(key, value).apply();
    }

    /**
     * Set a string value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The preferences name to store the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void save(@Nullable String preferences, @Nullable String key, @Nullable String value) {
        if (preferences == null || key == null) {
            return;
        }
        
        mContext.getSharedPreferences(preferences, Context.MODE_PRIVATE)
                .edit().putString(key, value).apply();
    }

    /**
     * Set a string value in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void save(@Nullable String key, @Nullable String value) {
        if (key == null) {
            return;
        }
        
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit().putString(key, value).apply();
    }

    /**
     * Set a string string in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The preferences name to store the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void saveStringSet(@Nullable String preferences, 
            @Nullable String key, @Nullable Set<String> value) {
        if (preferences == null || key == null) {
            return;
        }
        
        mContext.getSharedPreferences(preferences, Context.MODE_PRIVATE)
                .edit().putStringSet(key, value).apply();
    }

    /**
     * Set a string set in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void saveStringSet(@Nullable String key, @Nullable Set<String> value) {
        if (key == null) {
            return;
        }
        
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit().putStringSet(key, value).apply();
    }

    /**
     * Retrieve a boolean value from the supplied preferences.
     *
     * @param preferences The preferences name to find the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a boolean.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public boolean load(@Nullable String preferences, @Nullable String key, boolean value) {
        if (preferences == null || key == null) {
            return value;
        }
        
        return mContext.getSharedPreferences(preferences,
                Context.MODE_PRIVATE).getBoolean(key, value);
    }

    /**
     * Retrieve a boolean value from the default preferences.
     *
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a boolean.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public boolean load(@Nullable String key, boolean value) {
        if (key == null) {
            return value;
        }
        
        return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(key, value);
    }

    /**
     * Retrieve an integer value from the supplied preferences.
     *
     * @param preferences The preferences name to find the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not an integer.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public int load(@Nullable String preferences, @Nullable String key, int value) {
        if (preferences == null || key == null) {
            return value;
        }
        
        return mContext.getSharedPreferences(preferences,
                Context.MODE_PRIVATE).getInt(key, value);
    }

    /**
     * Retrieve an integer value from the default preferences.
     *
     * @param key The preference key to retrieve.
     * @param value Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not an integer.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public int load(@Nullable String key, int value) {
        if (key == null) {
            return value;
        }

        return PreferenceManager.getDefaultSharedPreferences(mContext).getInt(key, value);
    }

    /**
     * Retrieve a float value from the supplied preferences.
     *
     * @param preferences The preferences name to find the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a float.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public float load(@Nullable String preferences, @Nullable String key, float value) {
        if (preferences == null || key == null) {
            return value;
        }
        
        return mContext.getSharedPreferences(preferences,
                Context.MODE_PRIVATE).getFloat(key, value);
    }

    /**
     * Retrieve an float value from the default preferences.
     *
     * @param key The preference key to retrieve.
     * @param value Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a float.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public float load(@Nullable String key, float value) {
        if (key == null) {
            return value;
        }

        return PreferenceManager.getDefaultSharedPreferences(mContext).getFloat(key, value);
    }

    /**
     * Retrieve a string value from the supplied preferences.
     *
     * @param preferences The preferences name to find the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a string.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public @Nullable String load(@Nullable String preferences, 
            @Nullable String key, @Nullable String value) {
        if (preferences == null || key == null) {
            return value;
        }
        
        return mContext.getSharedPreferences(preferences,
                Context.MODE_PRIVATE).getString(key, value);
    }

    /**
     * Retrieve a string value from the default preferences.
     *
     * @param key The preference key to retrieve.
     * @param value Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a string.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public @Nullable String load(@Nullable String key, @Nullable String value) {
        if (key == null) {
            return value;
        }

        return PreferenceManager.getDefaultSharedPreferences(mContext).getString(key, value);
    }

    /**
     * Retrieve a string set from the supplied preferences.
     *
     * @param preferences The preferences name to find the key.
     * @param key The preference key to modify.
     * @param value The value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a string.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public @Nullable Set<String> loadStringSet(@Nullable String preferences,
            @Nullable String key, @Nullable Set<String> value) {
        if (preferences == null || key == null) {
            return value;
        }
        
        return mContext.getSharedPreferences(preferences,
                Context.MODE_PRIVATE).getStringSet(key, value);
    }

    /**
     * Retrieve a string set from the default preferences.
     *
     * @param key The preference key to retrieve.
     * @param value Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a string.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public @Nullable Set<String> loadStringSet(@Nullable String key, @Nullable Set<String> value) {
        if (key == null) {
            return value;
        }

        return PreferenceManager.getDefaultSharedPreferences(mContext).getStringSet(key, value);
    }

    /**
     * Remove a key from the supplied preferences.
     *
     * @param preferences The preferences name to remove the key.
     * @param key The preference key to remove.
     *
     * @see Context#getSharedPreferences(String, int)
     */
    public void delete(@Nullable String preferences, @Nullable String key) {
        if (preferences == null || key == null) {
            return;
        }

        try {
            mContext.getSharedPreferences(preferences,
                    Context.MODE_PRIVATE).edit().remove(key).apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Remove a key from the default preferences.
     *
     * @param key The preference key to remove.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public void delete(@Nullable String key) {
        if (key == null) {
            return;
        }

        try {
            PreferenceManager.getDefaultSharedPreferences(mContext).edit().remove(key).apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Get the default shared preferences name to perform other operations like backup
     * and restore.
     *
     * @return The default shared preferences name.
     *
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public @NonNull String getDefaultSharedPreferencesName() {
        return mContext.getPackageName() + "_preferences";
    }

    /**
     * Delete a shared preferences.
     *
     * @param preferences The preferences to be deleted.
     */
    public void deleteSharedPreferences(@Nullable String preferences) {
        if (preferences == null) {
            return;
        }

        try {
            mContext.getSharedPreferences(preferences,
                    Context.MODE_PRIVATE).edit().clear().apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Delete the default shared preferences.
     */
    public void deleteSharedPreferences() {
        try {
            PreferenceManager.getDefaultSharedPreferences(mContext).edit().clear().apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Checks whether the key is {@code null} as listener returns {@code null} key when
     * it is removed (or cleared) since API 30.
     *
     * @param key The key to be checked.
     *
     * @return {@code true} if the key is {@code null}.
     */
    public static boolean isNullKey(@Nullable String key) {
        return key == null;
    }
}
