/*
 * Copyright 2019-2022 Pranav Pandey
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.preference.PreferenceManager;

import java.util.Set;

/**
 * Helper class to handle shared preferences operations like saving or retrieving the values from
 * default shared preferences.
 * <p>It must be initialized once before accessing its methods.
 */
public class DynamicPreferences {

    /**
     * Singleton instance of {@link DynamicPreferences}.
     */
    @SuppressLint("StaticFieldLeak")
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
    private DynamicPreferences() { }

    /**
     * Constructor to initialize an object of this class.
     *
     * @param context The context to be used.
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
            throw new NullPointerException("Context should not be null.");
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
                    " is not initialized, call initializeInstance(...) method first.");
        }

        return sInstance;
    }

    /**
     * Returns the context used by this instance.
     *
     * @return The context to retrieve the resources.
     */
    public @NonNull Context getContext() {
        return mContext;
    }

    /**
     * Returns the {@link SharedPreferences} object for the supplied name.
     * <p>Use {@code null} to get the default shared preferences object.
     *
     * @param preferences The name of the shared preferences.
     *
     * @return The shared preferences object.
     *
     * @see Context#getSharedPreferences(String, int)
     * @see PreferenceManager#getDefaultSharedPreferences(Context)
     */
    public @NonNull SharedPreferences getSharedPreferences(@Nullable String preferences) {
        if (preferences != null) {
            return getContext().getSharedPreferences(preferences, Context.MODE_PRIVATE);
        }

        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    /**
     * Remove a key from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to remove.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#remove(String)
     */
    public void delete(@Nullable String preferences, @Nullable String key) {
        if (key == null) {
            return;
        }

        try {
            getSharedPreferences(preferences).edit().remove(key).apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Remove a key from the default shared preferences.
     *
     * @param key The preference key to remove.
     *
     * @see #delete(String, String)
     */
    public void delete(@Nullable String key) {
        delete(null, key);
    }

    /**
     * Get the default shared preferences name to perform other operations like backup
     * and restore.
     *
     * @return The default shared preferences name.
     *
     * @see #getSharedPreferences(String)
     */
    public @NonNull String getDefaultSharedPreferencesName() {
        return getContext().getPackageName() + "_preferences";
    }

    /**
     * Delete a shared preferences.
     *
     * @param preferences The preferences to be deleted.
     *                    <p>Set {@code null} to delete the default shared preferences.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#clear()
     */
    public void deleteSharedPreferences(@Nullable String preferences) {
        try {
            getSharedPreferences(preferences).edit().clear().apply();
        } catch (Exception ignored) {
        }
    }

    /**
     * Delete the default shared preferences.
     *
     * @see #getSharedPreferences(String)
     */
    public void deleteSharedPreferences() {
        deleteSharedPreferences(null);
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

    /**
     * Save a preference value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param removeOnNull {@code true} to remove key if the value is {@code null}.
     * @param <T> The type of shared preference.
     *
     * @see #getSharedPreferences(String)
     * @see #delete(String)
     * @see SharedPreferences.Editor#putBoolean(String, boolean)
     * @see SharedPreferences.Editor#putInt(String, int)
     * @see SharedPreferences.Editor#putFloat(String, float)
     * @see SharedPreferences.Editor#putLong(String, long)
     * @see SharedPreferences.Editor#putString(String, String)
     */
    public <T> void save(@Nullable String preferences, @Nullable String key,
            @Nullable T value, boolean removeOnNull) {
        if (key == null) {
            return;
        }

        if (removeOnNull && value == null) {
            delete(preferences, key);
        } else if (value instanceof Boolean) {
            getSharedPreferences(preferences).edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            getSharedPreferences(preferences).edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            getSharedPreferences(preferences).edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            getSharedPreferences(preferences).edit().putLong(key, (Long) value).apply();
        } else if (value == null || value instanceof String) {
            getSharedPreferences(preferences).edit().putString(key, (String) value).apply();
        }
    }

    /**
     * Save a preference value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param <T> The type of shared preference.
     *
     * @see #save(String, String, Object, boolean)
     */
    public <T> void save(@Nullable String preferences, @Nullable String key, @Nullable T value) {
        save(preferences, key, value, false);
    }

    /**
     * Save a preference value in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param removeOnNull {@code true} to remove key if the value is {@code null}.
     * @param <T> The type of shared preference.
     *
     * @see #save(String, String, Object, boolean)
     */
    public <T> void save(@Nullable String key, @Nullable T value, boolean removeOnNull) {
        save(null, key, value, removeOnNull);
    }

    /**
     * Save a preference value in the default shared preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param <T> The type of the shared preference.
     *
     * @see #save(String, String, Object)
     */
    public <T> void save(@Nullable String key, @Nullable T value) {
        if (key == null) {
            return;
        }

        save(null, key, value);
    }

    /**
     * Save a {@literal Set<String>} in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param removeOnNull {@code true} to remove key if the value is {@code null}.
     *
     * @see #getSharedPreferences(String)
     * @see #delete(String)
     * @see SharedPreferences.Editor#putStringSet(String, Set)
     */
    public void saveStringSet(@Nullable String preferences, @Nullable String key,
            @Nullable Set<String> value, boolean removeOnNull) {
        if (key == null) {
            return;
        }

        if (removeOnNull && value == null) {
            delete(preferences, key);
        } else {
            getSharedPreferences(preferences).edit().putStringSet(key, value).apply();
        }
    }

    /**
     * Save a {@literal Set<String>} in the supplied preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     *
     * @see #saveStringSet(String, String, Set, boolean)
     */
    public void saveStringSet(@Nullable String preferences,
            @Nullable String key, @Nullable Set<String> value) {
        saveStringSet(preferences, key, value, false);
    }

    /**
     * Save a {@literal Set<String>} in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     * @param removeOnNull {@code true} to remove key if the value is {@code null}.
     *
     * @see #saveStringSet(String, String, Set, boolean)
     */
    public void saveStringSet(@Nullable String key,
            @Nullable Set<String> value, boolean removeOnNull) {
        saveStringSet(null, key, value, removeOnNull);
    }

    /**
     * Save a {@literal Set<String>} in the default preferences editor and call
     * {@link SharedPreferences.Editor#apply()} to apply changes back from this editor.
     *
     * @param key The preference key to be saved or modified.
     * @param value The value for the preference.
     *
     * @see #saveStringSet(String, String, Set)
     */
    public void saveStringSet(@Nullable String key, @Nullable Set<String> value) {
        saveStringSet(null, key, value);
    }

    /**
     * Retrieve a {@code boolean} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code boolean}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getBoolean(String, boolean)
     */
    public boolean load(@Nullable String preferences, @Nullable String key, boolean value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getBoolean(key, value);
    }

    /**
     * Retrieve a {@code boolean} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code boolean}.
     *
     * @see #load(String, String, boolean)
     */
    public boolean load(@Nullable String key, boolean value) {
        return load(null, key, value);
    }

    /**
     * Retrieve an {@code integer} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not an {@code integer}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getInt(String, int)
     */
    public int load(@Nullable String preferences, @Nullable String key, int value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getInt(key, value);
    }

    /**
     * Retrieve an {@code integer} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not an {@code integer}.
     *
     * @see #load(String, String, int)
     */
    public int load(@Nullable String key, int value) {
        return load(null, key, value);
    }

    /**
     * Retrieve a {@code float} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code float}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getFloat(String, float)
     */
    public float load(@Nullable String preferences, @Nullable String key, float value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getFloat(key, value);
    }

    /**
     * Retrieve a {@code float} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code float}.
     *
     * @see #load(String, String, float)
     */
    public float load(@Nullable String key, float value) {
        return load(null, key, value);
    }

    /**
     * Retrieve a {@code long} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code long}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getLong(String, long)
     */
    public long load(@Nullable String preferences, @Nullable String key, long value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getLong(key, value);
    }

    /**
     * Retrieve a {@code long} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@code long}.
     *
     * @see #load(String, String, long)
     */
    public long load(@Nullable String key, long value) {
        return load(null, key, value);
    }

    /**
     * Retrieve a {@link String} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@link String}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getString(String, String)
     */
    public @Nullable String load(@Nullable String preferences,
            @Nullable String key, @Nullable String value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getString(key, value);
    }

    /**
     * Retrieve a {@link String} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@link String}.
     *
     * @see #load(String, String, String)
     */
    public @Nullable String load(@Nullable String key, @Nullable String value) {
        return load(null, key, value);
    }

    /**
     * Retrieve a {@literal Set<String>} preference value from the supplied shared preferences.
     *
     * @param preferences The shared preferences to be used.
     *                    <p>Set {@code null} to use the default shared preferences.
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@literal Set<String>}.
     *
     * @see #getSharedPreferences(String)
     * @see SharedPreferences.Editor#getStringSet(String, Set)
     */
    public @Nullable Set<String> loadStringSet(@Nullable String preferences,
            @Nullable String key, @Nullable Set<String> value) {
        if (key == null) {
            return value;
        }

        return getSharedPreferences(preferences).getStringSet(key, value);
    }

    /**
     * Retrieve a {@literal Set<String>} preference value from the supplied shared preferences.
     *
     * @param key The preference key to be retrieved.
     * @param value The default value for the preference.
     *
     * @return Returns the preference value if it exists, or the default value.
     *         <p>Throws {@link ClassCastException} if there is a preference with this name
     *         that is not a {@literal Set<String>}.
     *
     * @see #loadStringSet(String, String, Set)
     */
    public @Nullable Set<String> loadStringSet(@Nullable String key, @Nullable Set<String> value) {
        return loadStringSet(null, key, value);
    }
}
