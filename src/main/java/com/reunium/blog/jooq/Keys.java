/*
 * This file is generated by jOOQ.
 */
package com.reunium.blog.jooq;


import com.reunium.blog.jooq.tables.EventRegistration;
import com.reunium.blog.jooq.tables.records.EventRegistrationRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>form-stack</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EventRegistrationRecord> KEY_EVENT_REGISTRATION_PRIMARY = UniqueKeys0.KEY_EVENT_REGISTRATION_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<EventRegistrationRecord> KEY_EVENT_REGISTRATION_PRIMARY = Internal.createUniqueKey(EventRegistration.EVENT_REGISTRATION, "KEY_event-registration_PRIMARY", new TableField[] { EventRegistration.EVENT_REGISTRATION.UNIQUEID }, true);
    }
}