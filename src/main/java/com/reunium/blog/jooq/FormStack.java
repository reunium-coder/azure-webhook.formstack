/*
 * This file is generated by jOOQ.
 */
package com.reunium.blog.jooq;


import com.reunium.blog.jooq.tables.EventRegistration;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FormStack extends SchemaImpl {

    private static final long serialVersionUID = -1995532252;

    /**
     * The reference instance of <code>form-stack</code>
     */
    public static final FormStack FORM_STACK = new FormStack();

    /**
     * The table <code>form-stack.event-registration</code>.
     */
    public final EventRegistration EVENT_REGISTRATION = EventRegistration.EVENT_REGISTRATION;

    /**
     * No further instances allowed
     */
    private FormStack() {
        super("form-stack", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            EventRegistration.EVENT_REGISTRATION);
    }
}
