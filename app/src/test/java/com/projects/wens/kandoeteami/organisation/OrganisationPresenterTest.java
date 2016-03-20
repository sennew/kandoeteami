package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit.Callback;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class OrganisationPresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
    private static final Organisation ORGANISATION = new Organisation("KdG Groenplaats", "KdG Adress");

    private OrganisationPresenter presenter;
    @Mock
    private OrganisationContract.View view;
    @Mock
    private OrganisationService service;
    @Captor
    private ArgumentCaptor<Callback<Organisation>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new OrganisationPresenter(view, service);
    }

    @Test
    public void testShouldLoadOrganisation() throws Exception {
        presenter.loadOrganisation(TOKEN, 1);
        verify(service).getOrganisation(anyString(), anyInt(), argumentCaptor.capture());
        argumentCaptor.getValue().success(ORGANISATION,null);
        verify(view).showSuccesMessage("Successfully loaded organisations");
    }


}