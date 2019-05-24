package smartsag.DTO.Interfaces;

/**
 *
 * Generic type interface holding the build method.
 * Implementing class will be of generic type and will return itself upon "build".
 */
public interface BuilderInterface<O> {
    public O build();
}
