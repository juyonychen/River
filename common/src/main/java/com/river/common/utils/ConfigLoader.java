
package com.river.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.experimental.UtilityClass;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * A cache able typesafe config loader, supplies the entire typesafe tree to runtimes and modules.
 *
 *
 * @version 1.0.0, 2018-03-01 13:53
 * @since 1.0.0, 2018-03-01 13:53
 */
@UtilityClass
@NotThreadSafe
public class ConfigLoader {

    private static Config config = ConfigFactory.load();

    public static Config getConfig() {
        return config.resolve();
    }

    public static Config rawConfig() {
        return config;
    }

    /**
     * Append the given config to exist config.
     */
    public static void addConfig(Config newConfig) {
        config = newConfig.withFallback(config);
    }

    /**
     * Override the config instance with given one. Also a helper for simplify test.
     */
    public static void setConfig(Config newConfig) {
        config = newConfig;
    }

    public static Config resolveConfig(String configUrl) throws MalformedURLException {
        URL url = new URL(configUrl);
        Config urlConfig = ConfigFactory.parseURL(url);
        urlConfig.resolve();
        config = urlConfig;
        return config;
    }

    /**
     * Init the config loading from specified class path, also load the environment variables as the fallback.
     *
     * @param configPath The class path without {@code classpath:} prefix.
     */
    public static void initConfig(String configPath) {
        Config config = ConfigFactory.load(configPath);
        setConfig(config);
    }

    /**
     * After calling {@link #initConfig(String)}, your can use this method to get a specified config instance.
     * The conversion was based on Jackson.
     */
    public static <T extends Serializable> T loadBeanConfig(Class<T> clazz, String subPath) {
        return new ComponentConfigurator<>(clazz).detectConfiguration(subPath);
    }

    /**
     * After calling {@link #initConfig(String)}, your can use this method to get a specified list config instance.
     * The conversion was based on Jackson.
     */
    public static <T extends Serializable> List<T> loadListConfig(TypeReference<List<T>> type, String subPath) {
        return new ComponentConfigurator<>(type).detectConfiguration(subPath);
    }

    /**
     * After calling {@link #initConfig(String)}, your can use this method to get a specified map config instance.
     * The conversion was based on Jackson.
     */
    public static <K extends Serializable, V extends Serializable> Map<K, V> loadMapConfig(TypeReference<Map<K, V>> type, String subPath) {
        return new ComponentConfigurator<>(type).detectConfiguration(subPath);
    }
}
