
package com.river.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigRenderOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * ComponentConfigurator supplies serializable configuration beans derived from a specified typesafe path or object.
 * <p>
 * Typically a component will select a 'default' typesafe path to be used if no other path or object is provided.
 * <p>
 * For example, streams-persist-elasticsearch will use 'elasticsearch' by default, but an implementation
 * such as github.com/apache/streams-examples/local/elasticsearch-reindex
 * can resolve a reader from elasticsearch.source
 * and a writer from elasticsearch.destination.
 *
 *
 * @version 1.0.0, 2018-03-01 12:48
 * @since 1.0.0, 2018-03-01 12:48
 */
@Slf4j
public class ComponentConfigurator<T> {

    private static final ObjectMapper fallbackMapper;

    static {
        JSONUtils json = JSONUtils.nonEmptyMapper();
        json.enableEnumUseToString();
        fallbackMapper = json.getMapper();
    }

    private Class<T> configClass;

    private TypeReference<T> type;

    private ObjectMapper alternativeMapper;

    public ComponentConfigurator(Class<T> configClass) {
        this.configClass = configClass;
    }

    public ComponentConfigurator(TypeReference<T> type) {
        this.type = type;
    }

    public ComponentConfigurator(Class<T> configClass, ObjectMapper mapper) {
        this.configClass = configClass;
        this.alternativeMapper = mapper;
    }

    public ComponentConfigurator(TypeReference<T> type, ObjectMapper mapper) {
        this.type = type;
        this.alternativeMapper = mapper;
    }

    /**
     * resolve a serializable configuration pojo compiled from appropriate parts of the global JVM configuration tree.
     * <p>
     * the entire object, or fragments of it, will be collected and merged from:
     * - the simple class name of the configured class
     * - the fully qualified class name of the configured class
     * - any of the ancestor classes of the configured class
     * - the configured class's package
     * - any of the parent packages of the configured class's package
     *
     * @return result
     */
    public T detectConfiguration() {
        T pojoConfig = null;
        Config rootConfig = ConfigLoader.getConfig();
        Config cascadeConfig = null;
        String[] canonicalNameParts = configClass.getCanonicalName().split("\\.", -1);

        for (int partIndex = 1; partIndex < canonicalNameParts.length; partIndex++) {
            String[] partialPathParts = (String[]) Array.newInstance(String.class, partIndex);
            System.arraycopy(canonicalNameParts, 0, partialPathParts, 0, partIndex);
            String partialPath = String.join(".", partialPathParts);

            if (rootConfig.hasPath(partialPath)) {
                Config partialPathConfig = rootConfig.getConfig(partialPath).withoutPath(canonicalNameParts[partIndex]);
                if (!partialPathConfig.root().isEmpty()) {
                    if (cascadeConfig == null) {
                        cascadeConfig = partialPathConfig;
                    } else {
                        cascadeConfig = partialPathConfig.withFallback(cascadeConfig);
                    }
                }
            }
        }

        List<Class> superclasses = getSuperClasses(configClass);
        for (Class superclass : superclasses) {
            String superclassCanonicalName = superclass.getCanonicalName();
            cascadeConfig = getConfigWithFallback(rootConfig, cascadeConfig, superclassCanonicalName);
        }

        String simpleName = configClass.getSimpleName();
        cascadeConfig = getConfigWithFallback(rootConfig, cascadeConfig, simpleName);

        String canonicalName = configClass.getCanonicalName();
        cascadeConfig = getConfigWithFallback(rootConfig, cascadeConfig, canonicalName);

        try {
            String fullJsonStr = cascadeConfig.root().render(ConfigRenderOptions.concise());
            pojoConfig = renderJsonConfig(fullJsonStr);
        } catch (Exception ex) {
            log.warn("Could not parse:", cascadeConfig, ex);
        }

        return pojoConfig;
    }

    private Config getConfigWithFallback(Config rootConfig, Config cascadeConfig, String path) {
        if (rootConfig.hasPath(path)) {
            Config config = rootConfig.getConfig(path);
            if (cascadeConfig == null) {
                return config;
            } else {
                return config.withFallback(cascadeConfig);
            }
        }
        return cascadeConfig;
    }

    /**
     * resolve a serializable configuration pojo from a given typesafe config object.
     *
     * @param typesafeConfig typesafeConfig
     * @return result
     */
    public T detectConfiguration(Config typesafeConfig) {
        T pojoConfig = null;

        try {
            String fullJsonStr = typesafeConfig.root().render(ConfigRenderOptions.concise());
            pojoConfig = renderJsonConfig(fullJsonStr);
        } catch (Exception ex) {
            log.warn("Could not parse:", typesafeConfig, ex);
        }

        return pojoConfig;
    }

    /**
     * resolve a serializable configuration pojo from a portion of the JVM config object.
     *
     * @param subConfig subConfig
     * @return result
     */
    public T detectConfiguration(String subConfig) {
        Config streamsConfig = ConfigLoader.getConfig();
        return detectConfiguration(streamsConfig.getConfig(subConfig));
    }

    /**
     * resolve a serializable configuration pojo from a portion of a given typesafe config object.
     *
     * @param typesafeConfig typesafeConfig
     * @param subConfig      subConfig
     * @return result
     */
    public T detectConfiguration(Config typesafeConfig, String subConfig) {
        return detectConfiguration(typesafeConfig.getConfig(subConfig));
    }

    @SneakyThrows
    private T renderJsonConfig(String json) {
        if (configClass != null) {
            return getMapper().readValue(json, configClass);
        } else if (type != null) {
            return getMapper().readValue(json, type);
        } else {
            throw new IllegalArgumentException("No TypeReference nor type Class was provided.");
        }
    }

    /*
     * return class hierarchy in order from furthest to nearest ancestor
     */
    public static List<Class> getSuperClasses(Class clazz) {
        List<Class> classList = new ArrayList<>();
        Class superclass = clazz.getSuperclass();
        while (superclass != null && !superclass.isInterface() && superclass != Object.class) {
            classList.add(0, superclass);
            superclass = superclass.getSuperclass();
        }
        return classList;
    }

    private ObjectMapper getMapper() {
        if (alternativeMapper != null) {
            return alternativeMapper;
        } else {
            return fallbackMapper;
        }
    }
}
