package com.swapvista.utility;

public class Constants {

	public enum UserRole {
		ROLE_CUSTOMER("Customer"), ROLE_ADMIN("Admin"), ROLE_SELLER("Seller");

		private String role;

		private UserRole(String role) {
			this.role = role;
		}

		public String value() {
			return this.role;
		}
	}

	public enum UserStatus {
		ACTIVE("Active"), DEACTIVATED("Deactivated");

		private String status;

		private UserStatus(String status) {
			this.status = status;
		}

		public String value() {
			return this.status;
		}
	}



}
