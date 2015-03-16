/**
 * This class is generated by jOOQ
 */
package com.gemtastic.carshop.db;

/**
 * A class modelling foreign key relationships between tables of the <code>public</code> 
 * schema
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.AddressRecord, java.lang.Integer> IDENTITY_ADDRESS = Identities0.IDENTITY_ADDRESS;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, java.lang.Integer> IDENTITY_APPOINTMENTS = Identities0.IDENTITY_APPOINTMENTS;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CarRecord, java.lang.Integer> IDENTITY_CAR = Identities0.IDENTITY_CAR;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CarModelRecord, java.lang.Integer> IDENTITY_CAR_MODEL = Identities0.IDENTITY_CAR_MODEL;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CustomerRecord, java.lang.Integer> IDENTITY_CUSTOMER = Identities0.IDENTITY_CUSTOMER;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.EmployeesRecord, java.lang.Integer> IDENTITY_EMPLOYEES = Identities0.IDENTITY_EMPLOYEES;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.MakeRecord, java.lang.Integer> IDENTITY_MAKE = Identities0.IDENTITY_MAKE;
	public static final org.jooq.Identity<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord, java.lang.Integer> IDENTITY_MALFUNCTION_REPORTS = Identities0.IDENTITY_MALFUNCTION_REPORTS;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.AddressRecord> ADDRESS_PKEY = UniqueKeys0.ADDRESS_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord> APPOINTMENTS_PKEY = UniqueKeys0.APPOINTMENTS_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CarRecord> CAR_PKEY = UniqueKeys0.CAR_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CarModelRecord> CAR_MODEL_PKEY = UniqueKeys0.CAR_MODEL_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CertificationRecord> CERTIFICATION_EMPLOYEE_MAKE_KEY = UniqueKeys0.CERTIFICATION_EMPLOYEE_MAKE_KEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CustomerRecord> CUSTOMER_PKEY = UniqueKeys0.CUSTOMER_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.EmployeesRecord> EMPLOYEES_PKEY = UniqueKeys0.EMPLOYEES_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.MakeRecord> MAKE_PKEY = UniqueKeys0.MAKE_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord> MALFUNCTION_REPORTS_PKEY = UniqueKeys0.MALFUNCTION_REPORTS_PKEY;
	public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord> OWNERSHIP_OWNER_CAR_KEY = UniqueKeys0.OWNERSHIP_OWNER_CAR_KEY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.EmployeesRecord> APPOINTMENTS__APPOINTMENTS_MECHANIC_FKEY = ForeignKeys0.APPOINTMENTS__APPOINTMENTS_MECHANIC_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.CarRecord> APPOINTMENTS__APPOINTMENTS_CAR_FKEY = ForeignKeys0.APPOINTMENTS__APPOINTMENTS_CAR_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.CustomerRecord> APPOINTMENTS__APPOINTMENTS_COMMISSIONER_FKEY = ForeignKeys0.APPOINTMENTS__APPOINTMENTS_COMMISSIONER_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CarRecord, com.gemtastic.carshop.db.tables.records.CarModelRecord> CAR__CAR_CAR_MODEL_FKEY = ForeignKeys0.CAR__CAR_CAR_MODEL_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CarModelRecord, com.gemtastic.carshop.db.tables.records.MakeRecord> CAR_MODEL__CAR_MODEL_MAKE_FKEY = ForeignKeys0.CAR_MODEL__CAR_MODEL_MAKE_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CertificationRecord, com.gemtastic.carshop.db.tables.records.EmployeesRecord> CERTIFICATION__CERTIFICATION_EMPLOYEE_FKEY = ForeignKeys0.CERTIFICATION__CERTIFICATION_EMPLOYEE_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CertificationRecord, com.gemtastic.carshop.db.tables.records.MakeRecord> CERTIFICATION__CERTIFICATION_MAKE_FKEY = ForeignKeys0.CERTIFICATION__CERTIFICATION_MAKE_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CustomerRecord, com.gemtastic.carshop.db.tables.records.AddressRecord> CUSTOMER__CUSTOMER_ADDRESS_FKEY = ForeignKeys0.CUSTOMER__CUSTOMER_ADDRESS_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord, com.gemtastic.carshop.db.tables.records.CarRecord> MALFUNCTION_REPORTS__MALFUNCTION_REPORTS_CAR_FKEY = ForeignKeys0.MALFUNCTION_REPORTS__MALFUNCTION_REPORTS_CAR_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord, com.gemtastic.carshop.db.tables.records.CustomerRecord> OWNERSHIP__OWNERSHIP_OWNER_FKEY = ForeignKeys0.OWNERSHIP__OWNERSHIP_OWNER_FKEY;
	public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord, com.gemtastic.carshop.db.tables.records.CarRecord> OWNERSHIP__OWNERSHIP_CAR_FKEY = ForeignKeys0.OWNERSHIP__OWNERSHIP_CAR_FKEY;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends org.jooq.impl.AbstractKeys {
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.AddressRecord, java.lang.Integer> IDENTITY_ADDRESS = createIdentity(com.gemtastic.carshop.db.tables.Address.ADDRESS, com.gemtastic.carshop.db.tables.Address.ADDRESS.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, java.lang.Integer> IDENTITY_APPOINTMENTS = createIdentity(com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CarRecord, java.lang.Integer> IDENTITY_CAR = createIdentity(com.gemtastic.carshop.db.tables.Car.CAR, com.gemtastic.carshop.db.tables.Car.CAR.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CarModelRecord, java.lang.Integer> IDENTITY_CAR_MODEL = createIdentity(com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL, com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.CustomerRecord, java.lang.Integer> IDENTITY_CUSTOMER = createIdentity(com.gemtastic.carshop.db.tables.Customer.CUSTOMER, com.gemtastic.carshop.db.tables.Customer.CUSTOMER.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.EmployeesRecord, java.lang.Integer> IDENTITY_EMPLOYEES = createIdentity(com.gemtastic.carshop.db.tables.Employees.EMPLOYEES, com.gemtastic.carshop.db.tables.Employees.EMPLOYEES.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.MakeRecord, java.lang.Integer> IDENTITY_MAKE = createIdentity(com.gemtastic.carshop.db.tables.Make.MAKE, com.gemtastic.carshop.db.tables.Make.MAKE.ID);
		public static org.jooq.Identity<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord, java.lang.Integer> IDENTITY_MALFUNCTION_REPORTS = createIdentity(com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS, com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS.ID);
	}

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.AddressRecord> ADDRESS_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Address.ADDRESS, com.gemtastic.carshop.db.tables.Address.ADDRESS.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord> APPOINTMENTS_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CarRecord> CAR_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Car.CAR, com.gemtastic.carshop.db.tables.Car.CAR.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CarModelRecord> CAR_MODEL_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL, com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CertificationRecord> CERTIFICATION_EMPLOYEE_MAKE_KEY = createUniqueKey(com.gemtastic.carshop.db.tables.Certification.CERTIFICATION, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.EMPLOYEE, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.MAKE);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.CustomerRecord> CUSTOMER_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Customer.CUSTOMER, com.gemtastic.carshop.db.tables.Customer.CUSTOMER.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.EmployeesRecord> EMPLOYEES_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Employees.EMPLOYEES, com.gemtastic.carshop.db.tables.Employees.EMPLOYEES.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.MakeRecord> MAKE_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.Make.MAKE, com.gemtastic.carshop.db.tables.Make.MAKE.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord> MALFUNCTION_REPORTS_PKEY = createUniqueKey(com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS, com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS.ID);
		public static final org.jooq.UniqueKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord> OWNERSHIP_OWNER_CAR_KEY = createUniqueKey(com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP.OWNER, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP.CAR);
	}

	private static class ForeignKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.EmployeesRecord> APPOINTMENTS__APPOINTMENTS_MECHANIC_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.EMPLOYEES_PKEY, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS.MECHANIC);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.CarRecord> APPOINTMENTS__APPOINTMENTS_CAR_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CAR_PKEY, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS.CAR);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.AppointmentsRecord, com.gemtastic.carshop.db.tables.records.CustomerRecord> APPOINTMENTS__APPOINTMENTS_COMMISSIONER_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CUSTOMER_PKEY, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS, com.gemtastic.carshop.db.tables.Appointments.APPOINTMENTS.COMMISSIONER);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CarRecord, com.gemtastic.carshop.db.tables.records.CarModelRecord> CAR__CAR_CAR_MODEL_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CAR_MODEL_PKEY, com.gemtastic.carshop.db.tables.Car.CAR, com.gemtastic.carshop.db.tables.Car.CAR.CAR_MODEL);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CarModelRecord, com.gemtastic.carshop.db.tables.records.MakeRecord> CAR_MODEL__CAR_MODEL_MAKE_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.MAKE_PKEY, com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL, com.gemtastic.carshop.db.tables.CarModel.CAR_MODEL.MAKE);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CertificationRecord, com.gemtastic.carshop.db.tables.records.EmployeesRecord> CERTIFICATION__CERTIFICATION_EMPLOYEE_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.EMPLOYEES_PKEY, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.EMPLOYEE);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CertificationRecord, com.gemtastic.carshop.db.tables.records.MakeRecord> CERTIFICATION__CERTIFICATION_MAKE_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.MAKE_PKEY, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION, com.gemtastic.carshop.db.tables.Certification.CERTIFICATION.MAKE);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.CustomerRecord, com.gemtastic.carshop.db.tables.records.AddressRecord> CUSTOMER__CUSTOMER_ADDRESS_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.ADDRESS_PKEY, com.gemtastic.carshop.db.tables.Customer.CUSTOMER, com.gemtastic.carshop.db.tables.Customer.CUSTOMER.ADDRESS);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.MalfunctionReportsRecord, com.gemtastic.carshop.db.tables.records.CarRecord> MALFUNCTION_REPORTS__MALFUNCTION_REPORTS_CAR_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CAR_PKEY, com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS, com.gemtastic.carshop.db.tables.MalfunctionReports.MALFUNCTION_REPORTS.CAR);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord, com.gemtastic.carshop.db.tables.records.CustomerRecord> OWNERSHIP__OWNERSHIP_OWNER_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CUSTOMER_PKEY, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP.OWNER);
		public static final org.jooq.ForeignKey<com.gemtastic.carshop.db.tables.records.OwnershipRecord, com.gemtastic.carshop.db.tables.records.CarRecord> OWNERSHIP__OWNERSHIP_CAR_FKEY = createForeignKey(com.gemtastic.carshop.db.Keys.CAR_PKEY, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP, com.gemtastic.carshop.db.tables.Ownership.OWNERSHIP.CAR);
	}
}