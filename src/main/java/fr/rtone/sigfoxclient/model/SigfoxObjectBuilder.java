package fr.rtone.sigfoxclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: Hani
 */
public class SigfoxObjectBuilder<T> {

    private final Supplier<T> instantiator;

    private List<Consumer<T>> instanceModifiers = new ArrayList<>();

    public SigfoxObjectBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> SigfoxObjectBuilder<T> of(Supplier<T> instantiator) {
        return new SigfoxObjectBuilder<T>(instantiator);
    }

    public <U> SigfoxObjectBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        Consumer<T> c = instance -> consumer.accept(instance, value);
        instanceModifiers.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        instanceModifiers.forEach(modifier -> modifier.accept(value));
        instanceModifiers.clear();
        return value;
    }

}
