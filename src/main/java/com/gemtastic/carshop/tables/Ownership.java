/**
 * This class is generated by jOOQ
 */
package com.gemtastic.carshop.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ownership extends org.jooq.impl.TableImpl<com.gemtastic.carshop.tables.records.OwnershipRecord> {

	private static final long serialVersionUID = -1085360089;

	/**
	 * The reference instance of <code>public.ownership</code>
	 */
	public static final com.gemtastic.carshop.tables.Ownership OWNERSHIP = new com.gemtastic.carshop.tables.Ownership();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.gemtastic.carshop.tables.records.OwnershipRecord> getRecordType() {
		return com.gemtastic.carshop.tables.records.OwnershipRecord.class;
	}

	/**
	 * The column <code>public.ownership.owner</code>.
	 */
	public final org.jooq.TableField<com.gemtastic.carshop.tables.records.OwnershipRecord, java.lang.Integer> OWNER = createField("owner", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.ownership.car</code>.
	 */
	public final org.jooq.TableField<com.gemtastic.carshop.tables.records.OwnershipRecord, java.lang.Integer> CAR = createField("car", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>public.ownership</code> table reference
	 */
	public Ownership() {
		this("ownership", null);
	}

	/**
	 * Create an aliased <code>public.ownership</code> table reference
	 */
	public Ownership(java.lang.String alias) {
		this(alias, com.gemtastic.carshop.tables.Ownership.OWNERSHIP);
	}

	private Ownership(java.lang.String alias, org.jooq.Table<com.gemtastic.carshop.tables.records.OwnershipRecord> aliased) {
		this(alias, aliased, null);
	}

	private Ownership(java.lang.String alias, org.jooq.Table<com.gemtastic.carshop.tables.records.OwnershipRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.gemtastic.carshop.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.gemtastic.carshop.tables.records.OwnershipRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.gemtastic.carshop.tables.records.OwnershipRecord>>asList(com.gemtastic.carshop.Keys.OWNERSHIP_OWNER_CAR_KEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<com.gemtastic.carshop.tables.records.OwnershipRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<com.gemtastic.carshop.tables.records.OwnershipRecord, ?>>asList(com.gemtastic.carshop.Keys.OWNERSHIP__OWNERSHIP_OWNER_FKEY, com.gemtastic.carshop.Keys.OWNERSHIP__OWNERSHIP_CAR_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.gemtastic.carshop.tables.Ownership as(java.lang.String alias) {
		return new com.gemtastic.carshop.tables.Ownership(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.gemtastic.carshop.tables.Ownership rename(java.lang.String name) {
		return new com.gemtastic.carshop.tables.Ownership(name, null);
	}
}
