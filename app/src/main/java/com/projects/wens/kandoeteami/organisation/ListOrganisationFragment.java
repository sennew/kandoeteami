package com.projects.wens.kandoeteami.organisation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ListOrganisationFragment extends Fragment implements ListOrganisationContract.view {
    public static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private ListOrganisationContract.UserActionListener mOrgaActionListener;
    private ContentAdapter mOrganisationAdapter;
    private OrganisationService service;


    public ListOrganisationFragment() {
        //Requires empty public constructor
    }

    public static ListOrganisationFragment newInstance() {
        return new ListOrganisationFragment();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrganisationAdapter = new ContentAdapter(new ArrayList<Organisation>(0), mItemListener);
        service = ServiceGenerator.createService(
                OrganisationService.class,
                "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mOrgaActionListener = new ListOrganisationPresenter(this, service);
    }

    public void onResume() {
        super.onResume();

        //Restore preferences
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        mOrgaActionListener.loadOrganisations(true, token);
    }


    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organisation, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.organisation_list);
        recyclerView.setAdapter(mOrganisationAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //SWIPE AND REFRESH
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Restore preferences
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                mOrgaActionListener.loadOrganisations(true, token);

            }
        });


        return root;
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout srl = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showOrganisations(List<Organisation> organisations) {
        mOrganisationAdapter.replaceData(organisations);
    }

    @Override
    public void showOrganisationDetailUi(Integer noteId) {
        //TODO: NIEUWE INTENT AANMAKEN OM DETAIL ACTIVITY OP TE STARTEN
    }

    @Override
    public void showErrorMessage(int status) {
        Log.i("ORGANISATION API", "Error with api");
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
        private List<Organisation> mOrganisations;
        private OrganisationItemListener mOrganisationListener;

        public ContentAdapter(List<Organisation> organisations, OrganisationItemListener itemListener) {
            mOrganisations = organisations;
            mOrganisationListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            //FIXME: fix error
            View cardOrgView = inflater.inflate(R.layout.item_organisation, parent, false);

            return new ViewHolder(cardOrgView, mOrganisationListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Organisation organisation = mOrganisations.get(position);
            holder.title.setText(organisation.getOrganisationName());
            //TODO:DE DESCRIPTION MOET NOG TOEGEVOEGD WORDEN AAN HET MODEL
            holder.description.setText(organisation.getOrganisationName());
            Context context = holder.image.getContext();
            Picasso.with(context).load(PICASSO_BASEURL + organisation.getLogoUrl()).into(holder.image);
        }

        public void replaceData(List<Organisation> organisations) {
            setList(organisations);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mOrganisations.size();
        }

        private void setList(List<Organisation> organisations) {
            mOrganisations.clear();
            for (Organisation o : organisations){
                mOrganisations.add(o);
            }
        }

        public Organisation getOrga(int position) {
            return mOrganisations.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView title;
            public TextView description;
            public ImageView image;

            private OrganisationItemListener mOrganisationListener;

            public ViewHolder(View itemView, OrganisationItemListener listener) {
                super(itemView);
                mOrganisationListener = listener;
                title = (TextView) itemView.findViewById(R.id.card_title);
                description = (TextView) itemView.findViewById(R.id.card_text);
                image = (ImageView) itemView.findViewById(R.id.card_image);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Organisation orga = getOrga(position);
                mOrganisationListener.onOrganisationClick(orga);
            }
        }
    }

    //TODO:IMPLEMENTATIE OM DE LIST OF THEMA'S TE TONEN


    //IMPLEMENTATIE VOOR DE RECYCLERVIEW
    OrganisationItemListener mItemListener = new OrganisationItemListener() {
        @Override
        public void onOrganisationClick(Organisation clickOrganisation) {
            mOrgaActionListener.openOrganisationThema(clickOrganisation);
        }
    };

    //Interface voor weer te geven als er op een Organisatie wordt geklikt.
    public interface OrganisationItemListener {
        void onOrganisationClick(Organisation clickOrganisation);
    }
}

