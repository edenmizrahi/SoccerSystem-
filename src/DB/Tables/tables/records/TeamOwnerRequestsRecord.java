/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.TeamOwnerRequests;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TeamOwnerRequestsRecord extends UpdatableRecordImpl<TeamOwnerRequestsRecord> implements Record2<String, String> {

    private static final long serialVersionUID = -1097549539;

    /**
     * Setter for <code>footballsystem_db.team_owner_requests.team_owner</code>.
     */
    public void setTeamOwner(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.team_owner_requests.team_owner</code>.
     */
    public String getTeamOwner() {
        return (String) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.team_owner_requests.team_name</code>.
     */
    public void setTeamName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.team_owner_requests.team_name</code>.
     */
    public String getTeamName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return TeamOwnerRequests.TEAM_OWNER_REQUESTS.TEAM_OWNER;
    }

    @Override
    public Field<String> field2() {
        return TeamOwnerRequests.TEAM_OWNER_REQUESTS.TEAM_NAME;
    }

    @Override
    public String component1() {
        return getTeamOwner();
    }

    @Override
    public String component2() {
        return getTeamName();
    }

    @Override
    public String value1() {
        return getTeamOwner();
    }

    @Override
    public String value2() {
        return getTeamName();
    }

    @Override
    public TeamOwnerRequestsRecord value1(String value) {
        setTeamOwner(value);
        return this;
    }

    @Override
    public TeamOwnerRequestsRecord value2(String value) {
        setTeamName(value);
        return this;
    }

    @Override
    public TeamOwnerRequestsRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TeamOwnerRequestsRecord
     */
    public TeamOwnerRequestsRecord() {
        super(TeamOwnerRequests.TEAM_OWNER_REQUESTS);
    }

    /**
     * Create a detached, initialised TeamOwnerRequestsRecord
     */
    public TeamOwnerRequestsRecord(String teamOwner, String teamName) {
        super(TeamOwnerRequests.TEAM_OWNER_REQUESTS);

        set(0, teamOwner);
        set(1, teamName);
    }
}
