package com.test.repo.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReleaseModel implements Parcelable {
	private String name;
	private String description;
	private String author;

	public ReleaseModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	protected ReleaseModel(Parcel in) {
		name = in.readString();
		description = in.readString();
		author = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(author);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<ReleaseModel> CREATOR = new Parcelable.Creator<ReleaseModel>() {
		@Override
		public ReleaseModel createFromParcel(Parcel in) {
			return new ReleaseModel(in);
		}

		@Override
		public ReleaseModel[] newArray(int size) {
			return new ReleaseModel[size];
		}
	};
}
