package com.generationminusone.wordcount2015;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;

import java.util.ArrayList;
import java.util.List;

public class Proj_List_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;

    private CardUI mCardView;

    // RecyclerView projRecyclerView; // TODO: Define ListView
    private int mEntityCounter = 0;
    private List<String> mData;
    // private BasicListAdapter mBasicListAdapter; // TODO: Set up CursorAdapter

    Cursor projCursor;

    private MyDBHandler dbHandler;

    public Proj_List_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_proj_list, null);
        Log.d("Rob Debug", "Arrived in Proj_List_Fragment; inflating view");

        // init CardView
        mCardView = (CardUI) rootView.findViewById(R.id.cardsview);
        mCardView.setSwipeable(true);

        CardStack stack2 = new CardStack();
        stack2.setTitle("REGULAR CARDS");
        mCardView.addStack(stack2);

        // add AndroidViews Cards
        mCardView.addCard(new MyCard("Get the CardsUI view"));
        mCardView.addCardToLastStack(new MyCard("for Android at"));
        mCardView.addCard(new MyCard("Rob Test"));
        MyCard androidViewsCard = new MyCard("www.androidviews.net");
        androidViewsCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.androidviews.net/"));
                startActivity(intent);

            }
        });

        // draw cards
        mCardView.refresh();

        dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        // Initialise RecyclerView
//        projRecyclerView = (RecyclerView) rootView.findViewById(R.id.proj_recycler_view);
//        projRecyclerView.setLayoutManager(getLayoutManager());
//        projRecyclerView.setAdapter(getAdapter());

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.proj_list_actionbar_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                fragManager = getFragmentManager();
                if (fragManager.getBackStackEntryCount() > 0) {
                    fragManager.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private RecyclerView.LayoutManager getLayoutManager() {
//        return new LinearLayoutManager(getActivity());
//    }

//    private RecyclerView.Adapter getAdapter() {
//        mBasicListAdapter = new BasicListAdapter(getActivity());
//        getProjects();
//        Log.d("Rob Debug","Finished getProjects() call");
//        mBasicListAdapter.setOnItemClickListener(new BasicListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BasicListAdapter.Entity entity) {
//                System.out.println("BasicListActivity.onItemClick entity : " + entity);
//                frag = new Proj_Info_Fragment();
//                fragTransaction = getFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//            }
//        });
//        return mBasicListAdapter;
//    }

//    private void getProjects() {
//        Log.d("Rob Debug","About To Fetch Profiles");
//        Cursor projCursor = dbHandler.fetchAllProfiles(); //TODO: Do I need to set up a Loader for this cursor?
//
//        Log.d("Rob Debug","About To Initialise ArrayList");
//        // Construct an ArrayList containing Entities for BasicListAdapter to display in the RecyclerView;
//        mData = new ArrayList<BasicListAdapter.Entity>();
//        Log.d("Rob Debug","About To Populate ArrayList");
//        // While there's another record in the cursor...;
//        while(projCursor.moveToNext()) {
//            // Add to the ArrayList an Entity named the value of KEY_NAME from the database table;
//            String entityTitle = projCursor.getString(1);
//            Log.d("Rob Debug","entityTitle = " + entityTitle);
//            mData.add(new BasicListAdapter.Entity(entityTitle));
//        }
//        Log.d("Rob Debug","Finished Populating ArrayList");
        // To delete items from ArrayList, use mData.remove;
//        for (int i = 0; i < 15; ++i) {
//            int r = (int) (Math.random() * mData.size());
//            mData.remove(r);
//        }

        // To add items to ArrayList, use mData.add;
//        for (int i = 0; i < 15; ++i) {
//            int r = (int) (Math.random() * mData.size());
//            mData.add(r, new BasicListAdapter.Entity("Item " + (++mEntityCounter)));
//        }

//        mBasicListAdapter.setData(new ArrayList<BasicListAdapter.Entity>(mData));
//    }
}
