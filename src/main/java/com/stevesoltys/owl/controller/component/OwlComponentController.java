package com.stevesoltys.owl.controller.component;

import com.stevesoltys.owl.model.component.OwlComponent;

/**
 * @author Steve Soltys
 */
public abstract class OwlComponentController<T extends OwlComponent> {

    public abstract void update(T component);
}
