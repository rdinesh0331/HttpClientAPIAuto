package com.qa.data;

public class UsersUpdate {
		
		String name;
		String job;
		String updatedAt;

		public UsersUpdate() {
			
		}
		
		public String getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}

		public UsersUpdate(String name, String job) {
			this.name = name;
			this.job = job;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}


}
