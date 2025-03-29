package com.minis.context;

import java.util.EventObject;

/**
 * @author YuLong
 */
public class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    protected String msg = null;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object arg0) {
        super(arg0);
        this.msg = arg0.toString();
    }
}
