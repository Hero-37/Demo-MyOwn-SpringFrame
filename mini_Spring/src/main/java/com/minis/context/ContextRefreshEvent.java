package com.minis.context;

/**
 * @author YuLong
 */
public class ContextRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a prototypical Event.
     *
     * @param arg0@throws IllegalArgumentException if source is null
     */
    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
