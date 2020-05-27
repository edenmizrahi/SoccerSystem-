/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.RefereeNotification;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefereeNotificationRecord extends UpdatableRecordImpl<RefereeNotificationRecord> implements Record6<LocalDateTime, String, String, String, String, Byte> {

    private static final long serialVersionUID = -3777615;

    /**
     * Setter for <code>footballsystem_db.referee_notification.match_date</code>.
     */
    public void setMatchDate(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.match_date</code>.
     */
    public LocalDateTime getMatchDate() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.referee_notification.home_team</code>.
     */
    public void setHomeTeam(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.home_team</code>.
     */
    public String getHomeTeam() {
        return (String) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.referee_notification.away_team</code>.
     */
    public void setAwayTeam(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.away_team</code>.
     */
    public String getAwayTeam() {
        return (String) get(2);
    }

    /**
     * Setter for <code>footballsystem_db.referee_notification.referee</code>.
     */
    public void setReferee(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.referee</code>.
     */
    public String getReferee() {
        return (String) get(3);
    }

    /**
     * Setter for <code>footballsystem_db.referee_notification.notification_content</code>.
     */
    public void setNotificationContent(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.notification_content</code>.
     */
    public String getNotificationContent() {
        return (String) get(4);
    }

    /**
     * Setter for <code>footballsystem_db.referee_notification.read</code>.
     */
    public void setRead(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>footballsystem_db.referee_notification.read</code>.
     */
    public Byte getRead() {
        return (Byte) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record5<LocalDateTime, String, String, String, String> key() {
        return (Record5) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<LocalDateTime, String, String, String, String, Byte> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<LocalDateTime, String, String, String, String, Byte> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return RefereeNotification.REFEREE_NOTIFICATION.MATCH_DATE;
    }

    @Override
    public Field<String> field2() {
        return RefereeNotification.REFEREE_NOTIFICATION.HOME_TEAM;
    }

    @Override
    public Field<String> field3() {
        return RefereeNotification.REFEREE_NOTIFICATION.AWAY_TEAM;
    }

    @Override
    public Field<String> field4() {
        return RefereeNotification.REFEREE_NOTIFICATION.REFEREE;
    }

    @Override
    public Field<String> field5() {
        return RefereeNotification.REFEREE_NOTIFICATION.NOTIFICATION_CONTENT;
    }

    @Override
    public Field<Byte> field6() {
        return RefereeNotification.REFEREE_NOTIFICATION.READ;
    }

    @Override
    public LocalDateTime component1() {
        return getMatchDate();
    }

    @Override
    public String component2() {
        return getHomeTeam();
    }

    @Override
    public String component3() {
        return getAwayTeam();
    }

    @Override
    public String component4() {
        return getReferee();
    }

    @Override
    public String component5() {
        return getNotificationContent();
    }

    @Override
    public Byte component6() {
        return getRead();
    }

    @Override
    public LocalDateTime value1() {
        return getMatchDate();
    }

    @Override
    public String value2() {
        return getHomeTeam();
    }

    @Override
    public String value3() {
        return getAwayTeam();
    }

    @Override
    public String value4() {
        return getReferee();
    }

    @Override
    public String value5() {
        return getNotificationContent();
    }

    @Override
    public Byte value6() {
        return getRead();
    }

    @Override
    public RefereeNotificationRecord value1(LocalDateTime value) {
        setMatchDate(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord value2(String value) {
        setHomeTeam(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord value3(String value) {
        setAwayTeam(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord value4(String value) {
        setReferee(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord value5(String value) {
        setNotificationContent(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord value6(Byte value) {
        setRead(value);
        return this;
    }

    @Override
    public RefereeNotificationRecord values(LocalDateTime value1, String value2, String value3, String value4, String value5, Byte value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RefereeNotificationRecord
     */
    public RefereeNotificationRecord() {
        super(RefereeNotification.REFEREE_NOTIFICATION);
    }

    /**
     * Create a detached, initialised RefereeNotificationRecord
     */
    public RefereeNotificationRecord(LocalDateTime matchDate, String homeTeam, String awayTeam, String referee, String notificationContent, Byte read) {
        super(RefereeNotification.REFEREE_NOTIFICATION);

        set(0, matchDate);
        set(1, homeTeam);
        set(2, awayTeam);
        set(3, referee);
        set(4, notificationContent);
        set(5, read);
    }
}
