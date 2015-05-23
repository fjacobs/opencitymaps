package com.dynacore.livemap.entity.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Properties {
	
		@Id
		private String Name;
		private String PubDate;
		private String Type;
		private String State;
		private String FreeSpaceShort;
		private String FreeSpaceLong;
		private String ShortCapacity;
		private String LongCapacity;
		
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getPubDate() {
			return PubDate;
		}
		public void setPubDate(String pubDate) {
			PubDate = pubDate;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public String getState() {
			return State;
		}
		public void setState(String state) {
			State = state;
		}
		public String getFreeSpaceShort() {
			return FreeSpaceShort;
		}
		public void setFreeSpaceShort(String freeSpaceShort) {
			FreeSpaceShort = freeSpaceShort;
		}
		public String getFreeSpaceLong() {
			return FreeSpaceLong;
		}
		public void setFreeSpaceLong(String freeSpaceLong) {
			FreeSpaceLong = freeSpaceLong;
		}
		public String getShortCapacity() {
			return ShortCapacity;
		}
		public void setShortCapacity(String shortCapacity) {
			ShortCapacity = shortCapacity;
		}
		public String getLongCapacity() {
			return LongCapacity;
		}
		public void setLongCapacity(String longCapacity) {
			LongCapacity = longCapacity;
		}				
}
