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
public class CustomerRecord extends org.jooq.impl.UpdatableRecordImpl<com.gemtastic.carshop.db.tables.records.CustomerRecord> implements org.jooq.Record8<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer> {

	private static final long serialVersionUID = 1626622378;

	/**
	 * Setter for <code>public.customer.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.customer.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.customer.first_name</code>.
	 */
	public void setFirstName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.customer.first_name</code>.
	 */
	public java.lang.String getFirstName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>public.customer.last_name</code>.
	 */
	public void setLastName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.customer.last_name</code>.
	 */
	public java.lang.String getLastName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>public.customer.date_of_birth</code>.
	 */
	public void setDateOfBirth(java.sql.Date value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.customer.date_of_birth</code>.
	 */
	public java.sql.Date getDateOfBirth() {
		return (java.sql.Date) getValue(3);
	}

	/**
	 * Setter for <code>public.customer.gender</code>.
	 */
	public void setGender(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.customer.gender</code>.
	 */
	public java.lang.String getGender() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>public.customer.phone</code>.
	 */
	public void setPhone(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.customer.phone</code>.
	 */
	public java.lang.String getPhone() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>public.customer.email</code>.
	 */
	public void setEmail(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.customer.email</code>.
	 */
	public java.lang.String getEmail() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>public.customer.address</code>.
	 */
	public void setAddress(java.lang.Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.customer.address</code>.
	 */
	public java.lang.Integer getAddress() {
		return (java.lang.Integer) getValue(7);
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
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row8<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer> valuesRow() {
		return (org.jooq.Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.FIRST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.LAST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field4() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.DATE_OF_BIRTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.GENDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field8() {
		return com.gemtastic.carshop.db.tables.Customer.CUSTOMER.ADDRESS;
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
		return getFirstName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getLastName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value4() {
		return getDateOfBirth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getGender();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value8() {
		return getAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value2(java.lang.String value) {
		setFirstName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value3(java.lang.String value) {
		setLastName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value4(java.sql.Date value) {
		setDateOfBirth(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value5(java.lang.String value) {
		setGender(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value6(java.lang.String value) {
		setPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value7(java.lang.String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value8(java.lang.Integer value) {
		setAddress(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3, java.sql.Date value4, java.lang.String value5, java.lang.String value6, java.lang.String value7, java.lang.Integer value8) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CustomerRecord
	 */
	public CustomerRecord() {
		super(com.gemtastic.carshop.db.tables.Customer.CUSTOMER);
	}

	/**
	 * Create a detached, initialised CustomerRecord
	 */
	public CustomerRecord(java.lang.Integer id, java.lang.String firstName, java.lang.String lastName, java.sql.Date dateOfBirth, java.lang.String gender, java.lang.String phone, java.lang.String email, java.lang.Integer address) {
		super(com.gemtastic.carshop.db.tables.Customer.CUSTOMER);

		setValue(0, id);
		setValue(1, firstName);
		setValue(2, lastName);
		setValue(3, dateOfBirth);
		setValue(4, gender);
		setValue(5, phone);
		setValue(6, email);
		setValue(7, address);
	}
}
