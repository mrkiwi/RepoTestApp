package com.test.repo.data.network.transformer;

import com.apollographql.apollo.api.Response;
import com.test.repo.data.network.model.ReleaseModel;
import com.test.repo.data.network.model.RepositoryModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleTransformer;
import test.FindRepositoryQuery;

public class RepositoryTransformer {

	@Inject
	RepositoryTransformer() {

	}

	public SingleTransformer<Response<FindRepositoryQuery.Data>, RepositoryModel> transform() {
		return upstream -> upstream.map(this::map);
	}

	private RepositoryModel map(Response<FindRepositoryQuery.Data> response) {
		FindRepositoryQuery.Data dataResponse = response.data();
		if(dataResponse.repository() == null) return null;
		RepositoryModel repositoryModel = new RepositoryModel();
		repositoryModel.setDescription(dataResponse.repository().description());
		repositoryModel.setName(dataResponse.repository().name());
		ArrayList<ReleaseModel> releaseArrayList = new ArrayList<>();
		if(dataResponse.repository().releases() != null && dataResponse.repository().releases().edges().size() > 0) {
			for(FindRepositoryQuery.Edge item : dataResponse.repository().releases().edges()) {
				releaseArrayList.add(map(item.node()));
			}
		}
		repositoryModel.setReleases(releaseArrayList);
		return repositoryModel;
	}

	private ReleaseModel map(FindRepositoryQuery.Node node) {
		ReleaseModel releaseModel = new ReleaseModel();
		releaseModel.setAuthor(node.author().name());
		releaseModel.setDescription(node.description());
		releaseModel.setName(node.name());
		return releaseModel;
	}
}
