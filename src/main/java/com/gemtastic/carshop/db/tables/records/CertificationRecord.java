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
public class CertificationRecord extends org.jooq.impl.TableRecordImpl<com.gemtastic.carshop.db.tables.records.CertificationRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -843692957;

	/**
	 * Setter for <code>public.certification.employee</code>.
	 */
	public void setEmployee(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.certification.employee</code>.
	 */
	public java.lang.Integer getEmployee() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.certification.make</code>.
	 */
	public void setMake(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.certification.make</code>.
	 */
	public java.lang.Integer getMake() {
		return (java.lang.Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.EMPLOYEE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.MAKE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getEmployee();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getMake();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CertificationRecord value1(java.lang.Integer value) {
		setEmployee(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CertificationRecord value2(java.lang.Integer value) {
		setMake(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CertificationRecord values(java.lang.Integer value1, java.lang.Integer value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CertificationRecord
	 */
	public CertificationRecord() {
		super(com.gemtastic.carshop.db.tables.Certification.CERTIFICATION);
	}

	/**
	 * Create a detached, initialised CertificationRecord
	 */
	public CertificationRecord(java.lang.Integer employee, java.lang.Integer make) {
		super(com.gemtastic.carshop.db.tables.Certification.CERTIFICATION);

		setValue(0, employee);
		setValue(1, make);
	}
}
