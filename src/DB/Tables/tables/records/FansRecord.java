/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.Fans;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FansRecord extends UpdatableRecordImpl<FansRecord> implements Record6<String, String, String, String, String, LocalDate> {

    private static final long serialVersionUID = -530680750;

    /**
     * Setter for <code>footballsystem_db.fans.userName</code>.
     */
    public void setUsername(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.userName</code>.
     */
    public String getUsername() {
        return (String) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.fans.fullName</code>.
     */
    public void setFullname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.fullName</code>.
     */
    public String getFullname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.fans.password_hash</code>.
     */
    public void setPasswordHash(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.password_hash</code>.
     */
    public String getPasswordHash() {
        return (String) get(2);
    }

    /**
     * Setter for <code>footballsystem_db.fans.phoneNumber</code>.
     */
    public void setPhonenumber(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.phoneNumber</code>.
     */
    public String getPhonenumber() {
        return (String) get(3);
    }

    /**
     * Setter for <code>footballsystem_db.fans.email</code>.
     */
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>footballsystem_db.fans.birthday</code>.
     */
    public void setBirthday(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>footballsystem_db.fans.birthday</code>.
     */
    public LocalDate getBirthday() {
        return (LocalDate) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, String, String, LocalDate> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, String, String, LocalDate> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Fans.FANS.USERNAME;
    }

    @Override
    public Field<String> field2() {
        return Fans.FANS.FULLNAME;
    }

    @Override
    public Field<String> field3() {
        return Fans.FANS.PASSWORD_HASH;
    }

    @Override
    public Field<String> field4() {
        return Fans.FANS.PHONENUMBER;
    }

    @Override
    public Field<String> field5() {
        return Fans.FANS.EMAIL;
    }

    @Override
    public Field<LocalDate> field6() {
        return Fans.FANS.BIRTHDAY;
    }

    @Override
    public String component1() {
        return getUsername();
    }

    @Override
    public String component2() {
        return getFullname();
    }

    @Override
    public String component3() {
        return getPasswordHash();
    }

    @Override
    public String component4() {
        return getPhonenumber();
    }

    @Override
    public String component5() {
        return getEmail();
    }

    @Override
    public LocalDate component6() {
        return getBirthday();
    }

    @Override
    public String value1() {
        return getUsername();
    }

    @Override
    public String value2() {
        return getFullname();
    }

    @Override
    public String value3() {
        return getPasswordHash();
    }

    @Override
    public String value4() {
        return getPhonenumber();
    }

    @Override
    public String value5() {
        return getEmail();
    }

    @Override
    public LocalDate value6() {
        return getBirthday();
    }

    @Override
    public FansRecord value1(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public FansRecord value2(String value) {
        setFullname(value);
        return this;
    }

    @Override
    public FansRecord value3(String value) {
        setPasswordHash(value);
        return this;
    }

    @Override
    public FansRecord value4(String value) {
        setPhonenumber(value);
        return this;
    }

    @Override
    public FansRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public FansRecord value6(LocalDate value) {
        setBirthday(value);
        return this;
    }

    @Override
    public FansRecord values(String value1, String value2, String value3, String value4, String value5, LocalDate value6) {
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
     * Create a detached FansRecord
     */
    public FansRecord() {
        super(Fans.FANS);
    }

    /**
     * Create a detached, initialised FansRecord
     */
    public FansRecord(String username, String fullname, String passwordHash, String phonenumber, String email, LocalDate birthday) {
        super(Fans.FANS);

        set(0, username);
        set(1, fullname);
        set(2, passwordHash);
        set(3, phonenumber);
        set(4, email);
        set(5, birthday);
    }
}
