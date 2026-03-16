package com.santiago.hexagonal.infrastructure;

import java.util.List;

public interface IMapper<E, D> {
    D toDomain(E entity);

    E toEntity(D domain);

    List<D> toDomainList(List<E> entities);

    List<E> toEntityList(List<D> domains);
}
