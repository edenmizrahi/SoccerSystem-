/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.Events;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsRecord extends UpdatableRecordImpl<EventsRecord> implements Record8<Integer, LocalDateTime, String, LocalDateTime, String, String, String, Integer> {

    private static final long serialVersionUID = -1083918939;

    /**
     * Setter for <code>footballsystem_db.events.event_id</code>.
     */
    public void setEventId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.event_id</code>.
     */
    public Integer getEventId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.events.date</code>.
     */
    public void setDate(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.date</code>.
     */
    public LocalDateTime getDate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.events.referee</code>.
     */
    public void setReferee(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.referee</code>.
     */
    public String getReferee() {
        return (String) get(2);
    }

    /**
     * Setter for <code>footballsystem_db.events.match_date</code>.
     */
    public void setMatchDate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.match_date</code>.
     */
    public LocalDateTime getMatchDate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>footballsystem_db.events.home_team_match</code>.
     */
    public void setHomeTeamMatch(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.home_team_match</code>.
     */
    public String getHomeTeamMatch() {
        return (String) get(4);
    }

    /**
     * Setter for <code>footballsystem_db.events.away_team_match</code>.
     */
    public void setAwayTeamMatch(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.away_team_match</code>.
     */
    public String getAwayTeamMatch() {
        return (String) get(5);
    }

    /**
     * Setter for <code>footballsystem_db.events.name</code>.
     */
    public void setName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.name</code>.
     */
    public String getName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>footballsystem_db.events.minute_in_match</code>.
     */
    public void setMinuteInMatch(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>footballsystem_db.events.minute_in_match</code>.
     */
    public Integer getMinuteInMatch() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, LocalDateTime, String, LocalDateTime, String, String, String, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, LocalDateTime, String, LocalDateTime, String, String, String, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Events.EVENTS.EVENT_ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return Events.EVENTS.DATE;
    }

    @Override
    public Field<String> field3() {
        return Events.EVENTS.REFEREE;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Events.EVENTS.MATCH_DATE;
    }

    @Override
    public Field<String> field5() {
        return Events.EVENTS.HOME_TEAM_MATCH;
    }

    @Override
    public Field<String> field6() {
        return Events.EVENTS.AWAY_TEAM_MATCH;
    }

    @Override
    public Field<String> field7() {
        return Events.EVENTS.NAME;
    }

    @Override
    public Field<Integer> field8() {
        return Events.EVENTS.MINUTE_IN_MATCH;
    }

    @Override
    public Integer component1() {
        return getEventId();
    }

    @Override
    public LocalDateTime component2() {
        return getDate();
    }

    @Override
    public String component3() {
        return getReferee();
    }

    @Override
    public LocalDateTime component4() {
        return getMatchDate();
    }

    @Override
    public String component5() {
        return getHomeTeamMatch();
    }

    @Override
    public String component6() {
        return getAwayTeamMatch();
    }

    @Override
    public String component7() {
        return getName();
    }

    @Override
    public Integer component8() {
        return getMinuteInMatch();
    }

    @Override
    public Integer value1() {
        return getEventId();
    }

    @Override
    public LocalDateTime value2() {
        return getDate();
    }

    @Override
    public String value3() {
        return getReferee();
    }

    @Override
    public LocalDateTime value4() {
        return getMatchDate();
    }

    @Override
    public String value5() {
        return getHomeTeamMatch();
    }

    @Override
    public String value6() {
        return getAwayTeamMatch();
    }

    @Override
    public String value7() {
        return getName();
    }

    @Override
    public Integer value8() {
        return getMinuteInMatch();
    }

    @Override
    public EventsRecord value1(Integer value) {
        setEventId(value);
        return this;
    }

    @Override
    public EventsRecord value2(LocalDateTime value) {
        setDate(value);
        return this;
    }

    @Override
    public EventsRecord value3(String value) {
        setReferee(value);
        return this;
    }

    @Override
    public EventsRecord value4(LocalDateTime value) {
        setMatchDate(value);
        return this;
    }

    @Override
    public EventsRecord value5(String value) {
        setHomeTeamMatch(value);
        return this;
    }

    @Override
    public EventsRecord value6(String value) {
        setAwayTeamMatch(value);
        return this;
    }

    @Override
    public EventsRecord value7(String value) {
        setName(value);
        return this;
    }

    @Override
    public EventsRecord value8(Integer value) {
        setMinuteInMatch(value);
        return this;
    }

    @Override
    public EventsRecord values(Integer value1, LocalDateTime value2, String value3, LocalDateTime value4, String value5, String value6, String value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EventsRecord
     */
    public EventsRecord() {
        super(Events.EVENTS);
    }

    /**
     * Create a detached, initialised EventsRecord
     */
    public EventsRecord(Integer eventId, LocalDateTime date, String referee, LocalDateTime matchDate, String homeTeamMatch, String awayTeamMatch, String name, Integer minuteInMatch) {
        super(Events.EVENTS);

        set(0, eventId);
        set(1, date);
        set(2, referee);
        set(3, matchDate);
        set(4, homeTeamMatch);
        set(5, awayTeamMatch);
        set(6, name);
        set(7, minuteInMatch);
    }
}