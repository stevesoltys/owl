package com.stevesoltys.owl.controller;

import com.stevesoltys.owl.model.OwlComponent;

/**
 * A controller for a type of {@link OwlComponent}.
 *
 * @author Steve Soltys
 */
public abstract class OwlComponentController<T extends OwlComponent> {

    /**
     * A function that is used to initialize a component.
     *
     * @param component The component instance.
     */
    public abstract void init(T component);

    /**
     * A function that is called when it is time for a component to be updated.
     *
     * @param component The component instance.
     */
    public abstract void update(T component);
}
