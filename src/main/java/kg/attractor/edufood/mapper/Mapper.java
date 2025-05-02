package kg.attractor.edufood.mapper;

public interface Mapper<D, E> {
    D mapToDto(E e);
    E mapToEntity(D d);
}
