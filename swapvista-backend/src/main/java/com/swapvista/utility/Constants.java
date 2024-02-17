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
	
	public enum ProductOfferStatus {
		ACTIVE("Active"), CANCELLED("Cancelled"), WIN("Win"), LOSE("Lose");


		private String status;
	private ProductOfferStatus(String status) {
			this.status = status;
		}
	public String value() {
		return this.status;
	}
	}
	

	public enum CategoryStatus {
		ACTIVE("Active"), DEACTIVATED("Deactivated");

		private String status;

		private CategoryStatus(String status) {
			this.status = status;
		}

		public String value() {
			return this.status;
		}
	}

	public enum ProductStatus {
		AVAILABLE("Available"), DEACTIVATED("Deactivated"), SOLD("Sold"), UNSOLD("UnSold");

		private String status;

		private ProductStatus(String status) {
			this.status = status;
		}

		
		public String value() {
			return this.status;
		}
	}


	}


