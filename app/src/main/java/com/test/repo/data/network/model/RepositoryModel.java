package com.test.repo.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RepositoryModel implements Parcelable {
	private String name;
	private String description;
	private ArrayList<ReleaseModel> releases;

	public RepositoryModel() {
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

	public ArrayList<ReleaseModel> getReleases() {
		return releases;
	}

	public void setReleases(ArrayList<ReleaseModel> releases) {
		this.releases = releases;
	}

	protected RepositoryModel(Parcel in) {
		name = in.readString();
		description = in.readString();
		if (in.readByte() == 0x01) {
			releases = new ArrayList<ReleaseModel>();
			in.readList(releases, ReleaseModel.class.getClassLoader());
		} else {
			releases = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(description);
		if (releases == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(releases);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<RepositoryModel> CREATOR = new Parcelable.Creator<RepositoryModel>() {
		@Override
		public RepositoryModel createFromParcel(Parcel in) {
			return new RepositoryModel(in);
		}

		@Override
		public RepositoryModel[] newArray(int size) {
			return new RepositoryModel[size];
		}
	};
}
