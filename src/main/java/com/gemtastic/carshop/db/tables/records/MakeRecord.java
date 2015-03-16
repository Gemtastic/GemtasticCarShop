/**
 * This class is generated by jOOQ
 */
package com.gemtastic.carshop.db.tables.records;

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
public class MakeRecord extends org.jooq.impl.UpdatableRecordImpl<com.gemtastic.carshop.db.tables.records.MakeRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.String> {

	private static final long serialVersionUID = -142482123;

	/**
	 * Setter for <code>public.make.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.make.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.make.make</code>.
	 */
	public void setMake(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.make.make</code>.
	 */
	public java.lang.String getMake() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.gemtastic.carshop.db.tables.Make.MAKE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.gemtastic.carshop.db.tables.Make.MAKE.MAKE_;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getMake();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MakeRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MakeRecord value2(java.lang.String value) {
		setMake(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MakeRecord values(java.lang.Integer value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached MakeRecord
	 */
	public MakeRecord() {
		super(com.gemtastic.carshop.db.tables.Make.MAKE);
	}

	/**
	 * Create a detached, initialised MakeRecord
	 */
	public MakeRecord(java.lang.Integer id, java.lang.String make) {
		super(com.gemtastic.carshop.db.tables.Make.MAKE);

		setValue(0, id);
		setValue(1, make);
	}
}