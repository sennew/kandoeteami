package com.projects.wens.kandoeteami.organisation;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by senne on 01/03/2016.
 */
public class OrganisationListPresenterTest {
    private static List<Organisation> ORGANISATIONS = Lists.newArrayList(new Organisation("Test1", "Description1"), new Organisation("Test2", "Description2"));

    private static List<Organisation> EMPTY_LISTORGA = new ArrayList<>(0);

    private ListOrganisationPresenter mOrganisationPresenter;
    @Mock
    private ListOrganisationContract.view mListView;
    @Mock
    private OrganisationService mOrganisationService;
    @Captor
    private ArgumentCaptor<Callback<List<Organisation>>> mLoadOrganisationCallbackCaptor;

    @Before
    public void setupListOrganisationPresenter(){
        MockitoAnnotations.initMocks(this);
        mOrganisationPresenter = new ListOrganisationPresenter(mListView,mOrganisationService);
    }

    @Test
    public void loadOrganisationFromServiceAndLoadIntoView(){
        String token = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
        mOrganisationPresenter.loadOrganisations(true,token);

        verify(mOrganisationService).getOrganisations(anyString(), mLoadOrganisationCallbackCaptor.capture());

        mLoadOrganisationCallbackCaptor.getValue().success(ORGANISATIONS, null);
        verify(mListView).setProgressIndicator(false);
        verify(mListView).showOrganisations(ORGANISATIONS);
    }

}
