package com.projects.wens.kandoeteami.organisation;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.themes.ListThemeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ListOrganisationFragment extends Fragment implements ListOrganisationContract.view {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    public static final String PREFS_NAME = "MyPrefs";
    private ListOrganisationContract.UserActionListener mOrgaActionListener;
    private ContentAdapter mOrganisationAdapter;
    private OrganisationService service;

    //We m


    public ListOrganisationFragment() {
        //Requires empty public constructor
    }

    public static ListOrganisationFragment newInstance() {
        return new ListOrganisationFragment();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrganisationAdapter = new ContentAdapter(new ArrayList<Organisation>(0), mItemListener, getActivity());
        service = ServiceGenerator.createService(
                OrganisationService.class,
                getResources().getString(R.string.baseURL));
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
    public void showOrganisationDetailUi(Integer organisationId) {
        Toast.makeText(getContext(), "showOrgaDetail: " + organisationId, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getContext(), OrganisationActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt("ORGAID", organisationId);
        i.putExtras(mBundle);
        startActivity(i);
    }

    @Override
    public void showOrganisationThemesUi(Integer organisationId) {
        Intent i = new Intent(getContext(), ListThemeActivity.class);
        i.putExtra("allThemes", false);
        i.putExtra("organisationId", organisationId);
        startActivity(i);
    }

    @Override
    public void showErrorMessage(int status) {
        Log.i("ORGANISATION API", "Error with api");
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
        private List<Organisation> mOrganisations;
        private OrganisationItemListener mOrganisationListener;
        private Context context;

        public ContentAdapter(List<Organisation> organisations, OrganisationItemListener itemListener, Context context) {
            mOrganisations = organisations;
            mOrganisationListener = itemListener;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View cardOrgView = inflater.inflate(R.layout.item_organisation, parent, false);

            return new ViewHolder(cardOrgView, mOrganisationListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Organisation organisation = mOrganisations.get(position);
            holder.title.setText(organisation.getOrganisationName());
            holder.description.setText(organisation.getAddress());
            holder.aantalUsersButton.setText(""+organisation.getCountUsers());

            if (organisation.getLogoURL().charAt(0) == 'r') {
                Picasso.with(context).load(PICASSO_BASEURL + organisation.getLogoURL()).into(holder.image);
            } else {
                Picasso.with(context).load(organisation.getLogoURL()).into(holder.image);
            }

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
            for (Organisation o : organisations) {
                mOrganisations.add(o);
            }
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView title;
            public TextView description;
            public ImageView image;
            public Button aantalUsersButton;
            public Button themesButton;

            private OrganisationItemListener mOrganisationListener;

            public ViewHolder(View itemView, OrganisationItemListener listener) {
                super(itemView);
                mOrganisationListener = listener;
                title = (TextView) itemView.findViewById(R.id.card_title);
                description = (TextView) itemView.findViewById(R.id.card_text);
                image = (ImageView) itemView.findViewById(R.id.card_image);
                aantalUsersButton = (Button) itemView.findViewById(R.id.button_totalusers);
                themesButton = (Button) itemView.findViewById(R.id.button_themes);
                themesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        Organisation orga = mOrganisations.get(position);
                        mOrganisationListener.onThemeButtonClick(orga);
                    }
                });
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Organisation orga = mOrganisations.get(position);
                mOrganisationListener.onOrganisationClick(orga);
            }
        }
    }

    //TODO:IMPLEMENTATIE OM DE LIST OF THEMA'S TE TONEN


    //IMPLEMENTATIE VOOR DE RECYCLERVIEW
    OrganisationItemListener mItemListener = new OrganisationItemListener() {
        @Override
        public void onOrganisationClick(Organisation clickOrganisation) {
            mOrgaActionListener.openOrganisationDetail(clickOrganisation);
        }

        @Override
        public void onThemeButtonClick(Organisation clickOrganisation) {
            mOrgaActionListener.openOrganisationThema(clickOrganisation);
        }
    };

    //Interface voor weer te geven als er op een Organisatie wordt geklikt.
    public interface OrganisationItemListener {
        void onOrganisationClick(Organisation clickOrganisation);
        void onThemeButtonClick(Organisation clickOrganisation);
    }


}

