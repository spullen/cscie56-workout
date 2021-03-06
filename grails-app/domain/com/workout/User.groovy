package com.workout

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String firstName
	String lastName
	String email
	String preferredDistanceUnits = 'mi'

	static hasMany = [activities: Activity]

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		firstName blank: false
		lastName blank: false
		email blank: false, email: true, unique: true
		preferredDistanceUnits blank: false, inList: ['mi', 'km']
	}

	static mapping = {
		table 'users'
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
