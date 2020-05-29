/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables;


import DB.Tables.FootballsystemDb;
import DB.Tables.Keys;
import DB.Tables.tables.records.CalculationpolicysRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row1;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Calculationpolicys extends TableImpl<CalculationpolicysRecord> {

    private static final long serialVersionUID = -1009936300;

    /**
     * The reference instance of <code>footballsystem_db.calculationpolicys</code>
     */
    public static final Calculationpolicys CALCULATIONPOLICYS = new Calculationpolicys();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CalculationpolicysRecord> getRecordType() {
        return CalculationpolicysRecord.class;
    }

    /**
     * The column <code>footballsystem_db.calculationpolicys.name</code>.
     */
    public final TableField<CalculationpolicysRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>footballsystem_db.calculationpolicys</code> table reference
     */
    public Calculationpolicys() {
        this(DSL.name("calculationpolicys"), null);
    }

    /**
     * Create an aliased <code>footballsystem_db.calculationpolicys</code> table reference
     */
    public Calculationpolicys(String alias) {
        this(DSL.name(alias), CALCULATIONPOLICYS);
    }

    /**
     * Create an aliased <code>footballsystem_db.calculationpolicys</code> table reference
     */
    public Calculationpolicys(Name alias) {
        this(alias, CALCULATIONPOLICYS);
    }

    private Calculationpolicys(Name alias, Table<CalculationpolicysRecord> aliased) {
        this(alias, aliased, null);
    }

    private Calculationpolicys(Name alias, Table<CalculationpolicysRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Calculationpolicys(Table<O> child, ForeignKey<O, CalculationpolicysRecord> key) {
        super(child, key, CALCULATIONPOLICYS);
    }

    @Override
    public Schema getSchema() {
        return FootballsystemDb.FOOTBALLSYSTEM_DB;
    }

    @Override
    public UniqueKey<CalculationpolicysRecord> getPrimaryKey() {
        return Keys.KEY_CALCULATIONPOLICYS_PRIMARY;
    }

    @Override
    public List<UniqueKey<CalculationpolicysRecord>> getKeys() {
        return Arrays.<UniqueKey<CalculationpolicysRecord>>asList(Keys.KEY_CALCULATIONPOLICYS_PRIMARY);
    }

    @Override
    public Calculationpolicys as(String alias) {
        return new Calculationpolicys(DSL.name(alias), this);
    }

    @Override
    public Calculationpolicys as(Name alias) {
        return new Calculationpolicys(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Calculationpolicys rename(String name) {
        return new Calculationpolicys(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Calculationpolicys rename(Name name) {
        return new Calculationpolicys(name, null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }
}
