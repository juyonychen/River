
package com.river.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * The common base class for convention between dto and entity
 * Could linked list would be better than ArrayList?
 *
 * @param <D> The dto pojo
 * @param <E> The database entity
 *
 * @version 1.0.0, 2018-06-12 16:27
 * @since 1.0.0, 2018-06-12 16:27
 */
public abstract class BaseConverter<D, E> extends Converter<D, E> implements Serializable {
    private static final long serialVersionUID = 5949175273745318366L;

    protected static final JSONUtils JSON = JSONUtils.nonEmptyMapper();
    protected static final JavaType GENERIC_MAP_TYPE = JSON.buildMapType(HashMap.class, String.class, Object.class);

    static {
        JSON.enableEnumUseToString();
    }

    public D convertToDTO(E entity) {
        return reverse().convert(entity);
    }

    public List<D> convertToDTOs(Iterable<E> entities) {
        if (entities == null) {
            return emptyList();
        }
        return Lists.newLinkedList(reverse().convertAll(entities));
    }

    public E convertToEntity(D dto) {
        return convert(dto);
    }

    public List<E> convertToEntities(Iterable<D> dtos) {
        if (dtos == null) {
            return emptyList();
        }
        return Lists.newLinkedList(convertAll(dtos));
    }
}
