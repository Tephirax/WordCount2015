package com.generationminusone.wordcount2015;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

public class Prof_Detail_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;

    private EditText mNameText;
    private CheckBox mEdCheckBox;
    private Spinner mEdUnitSpinner;
    private TextView mRankText;
    private TextView mRankNum;
    private TextView mTotalXP;
    private TextView mXPToNextRank;
    private TextView mTotalWords;
    private TextView mActive;

    private Long mRowId;
    private String mRowIdc;

    private NumberPicker wcNumPick1;
    private NumberPicker wcNumPick2;
    private NumberPicker wcNumPick3;
    private NumberPicker wcNumPick4;
    private NumberPicker wcNumPick5;
    private NumberPicker edNumPick1;
    private NumberPicker edNumPick2;
    private NumberPicker edNumPick3;
    private NumberPicker edNumPick4;
    private NumberPicker edNumPick5;

    public int wctenthousands;
    public int wcthousands;
    public int wchundreds;
    public int wctens;
    public int wcones;
    public int edtenthousands;
    public int edthousands;
    public int edhundreds;
    public int edtens;
    public int edones;

    public String name;
    public String rankname;
    public int ranknum;
    public int totalxp;
    public int totxptonext;
    public int xptonextrank;
    public int xplevelfloor;
    public int xptarget;
    public int totalwords;
    public int numofposts;
    public int numconsec;
    public int wctarget;
    public int edtarget;
    public int edunit;
    public int active;
    public String activestr;
    public String lastposted;
    public double multiplier;
    public int dailywc;
    public int dailywcrem;
    public static int wordcounttargetnum;
    public static int wordcounttarget;
    public static int activeProfile;
    View rootView;

    public Prof_Detail_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get profile ID from Main Activity TODO: Set up interface to transfer this position info between fragments
        mRowId = ((Main_Activity)getActivity()).profId;

        // Inflate the layout for this fragment
        if (mRowId == null) {
            rootView = inflater.inflate(R.layout.fragment_prof_create, null);
            Log.d("Rob Debug", "Arrived in Prof_Detail_Fragment; inflating Profile Creation view");
        } else {
            rootView = inflater.inflate(R.layout.fragment_prof_detail, null);
            Log.d("Rob Debug", "Arrived in Prof_Detail_Fragment; inflating Profile Display view");
        }

        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        // Find views: Name, Wordcount & Editcount should always be present. Other fields only post-creation
        mNameText = (EditText) rootView.findViewById(R.id.profile_name);
        wcNumPick1 = (NumberPicker) rootView.findViewById(R.id.WCPicker1); // Note: Default values for
        wcNumPick2 = (NumberPicker) rootView.findViewById(R.id.WCPicker2); // NumberPickers has been
        wcNumPick3 = (NumberPicker) rootView.findViewById(R.id.WCPicker3); // moved to populateFields
        wcNumPick4 = (NumberPicker) rootView.findViewById(R.id.WCPicker4); // method.
        wcNumPick5 = (NumberPicker) rootView.findViewById(R.id.WCPicker5);
        mEdCheckBox = (CheckBox) rootView.findViewById(R.id.editcheckbox);
        edNumPick1 = (NumberPicker) rootView.findViewById(R.id.EdPicker1);
        edNumPick2 = (NumberPicker) rootView.findViewById(R.id.EdPicker2);
        edNumPick3 = (NumberPicker) rootView.findViewById(R.id.EdPicker3);
        edNumPick4 = (NumberPicker) rootView.findViewById(R.id.EdPicker4);
        edNumPick5 = (NumberPicker) rootView.findViewById(R.id.EdPicker5);
        // Initialise Spinner
        mEdUnitSpinner = (Spinner) rootView.findViewById(R.id.editunit);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.unit_array,
                                    android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEdUnitSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        mEdUnitSpinner.setAdapter(adapter);

        if (mRowId != null) {
            mRankText = (TextView) rootView.findViewById(R.id.rank);
            mRankNum = (TextView) rootView.findViewById(R.id.ranknum);
            mTotalXP = (TextView) rootView.findViewById(R.id.totalxp);
            mXPToNextRank = (TextView) rootView.findViewById(R.id.xptonextrank);
            mTotalWords = (TextView) rootView.findViewById(R.id.totalwords);
            mActive = (TextView) rootView.findViewById(R.id.active);
        }

        /* Assign Max/Min values to each Picker */
        wcNumPick1.setMinValue(0);
        wcNumPick1.setMaxValue(9);
        wcNumPick2.setMinValue(0);
        wcNumPick2.setMaxValue(9);
        wcNumPick3.setMinValue(0);
        wcNumPick3.setMaxValue(9);
        wcNumPick4.setMinValue(0);
        wcNumPick4.setMaxValue(9);
        wcNumPick5.setMinValue(0);
        wcNumPick5.setMaxValue(9);

        /* Assign Max/Min values to each Picker */
        edNumPick1.setMinValue(0);
        edNumPick1.setMaxValue(9);
        edNumPick2.setMinValue(0);
        edNumPick2.setMaxValue(9);
        edNumPick3.setMinValue(0);
        edNumPick3.setMaxValue(9);
        edNumPick4.setMinValue(0);
        edNumPick4.setMaxValue(9);
        edNumPick5.setMinValue(0);
        edNumPick5.setMaxValue(9);

        populateFields();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.prof_detail_actionbar_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                if (checkForChanges()) { //TODO: Implement saveChanges dialog box;
//                    fragManager = getFragmentManager();
//                    saveChangesDialogFragment saveChanges = new saveChangesDialogFragment();
//                    saveChanges.setTargetFragment(this, 1);
//                    saveChanges.show(fragManager, "Save Changes Dialog");
                }
                fragManager = getFragmentManager();
                if (fragManager.getBackStackEntryCount() > 0) {
                    fragManager.popBackStack();
                }
                return true;
            case R.id.action_accept:
                saveState();
                fragManager = getFragmentManager();
                if (fragManager.getBackStackEntryCount() > 0) {
                    fragManager.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateFields() {
        // Create Profile: initialise targets
        if (mRowId == null) {
            Log.d("Rob Debug", "Prof_Detail_Fragment, Initialising new profile wctarget & edtarget");
            wctarget = 1000;
            edtarget = 1000;
            mEdUnitSpinner.setSelection(1); // Defaults to 'words'
        } else { // Display Profile: populate values from database
            MyDBHandler dbhandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
            Prof_Handler profile = dbhandler.fetchProfile(mRowId);

            mNameText.setText(profile.getName());
            mRankText.setText(profile.getRank());
            mRankNum.setText(profile.getRanknum());
            mTotalXP.setText(profile.getTotalXP());
            mXPToNextRank.setText(profile.getXPToNextRank());
            mTotalWords.setText(profile.getTotalWords());
            int active = profile.getActiveProf();
            if (active == 1) {
                activestr = "Yes";
            } else {
                activestr = "No";
            }
            mActive.setText(activestr);

            // EditUnit Spinner
            mEdUnitSpinner.setSelection(profile.getEdUnit());    //set the default according to value

            wctarget = profile.getWCTarget();
            edtarget = profile.getEdTarget();
        }

        // Assign NumberPicker values based on wctarget/edtarget
        wctenthousands = (wctarget / 10000) % 10;
        wcthousands = (wctarget / 1000) % 10;
        wchundreds = (wctarget / 100) % 10;
        wctens = (wctarget / 10) % 10;
        wcones = wctarget % 10;
        edtenthousands = (edtarget / 10000) % 10;
        edthousands = (edtarget / 1000) % 10;
        edhundreds = (edtarget / 100) % 10;
        edtens = (edtarget / 10) % 10;
        edones = edtarget % 10;

        wcNumPick1.setValue(wctenthousands);
        wcNumPick2.setValue(wcthousands);
        wcNumPick3.setValue(wchundreds);
        wcNumPick4.setValue(wctens);
        wcNumPick5.setValue(wcones);
        edNumPick1.setValue(edtenthousands);
        edNumPick2.setValue(edthousands);
        edNumPick3.setValue(edhundreds);
        edNumPick4.setValue(edtens);
        edNumPick5.setValue(edones);

    }

    public boolean checkForChanges() {
        //TODO: Compare values in Name, Wordcount, Editcount & EditUnit fields against database values, return true if different;
        //return change;
        return false;
    }

    public void createProfile() {
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
        Prof_Handler profile = new Prof_Handler(name, rankname, ranknum, totalxp, totxptonext,
                                    xptonextrank, xplevelfloor, xptarget, totalwords, numofposts, numconsec,
                                    wctarget, edtarget, edunit, active, lastposted, multiplier, dailywc, dailywcrem);

        dbHandler.createProfile(profile);
        Log.d("Rob Debug","Created profile");
    }

    public void updateProfile() {
        //TODO: Call this whenever leaving this fragment, to update values in database
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
        Prof_Handler profile = new Prof_Handler(mRowId, name, rankname, ranknum, totalxp, totxptonext,
                                    xptonextrank, xplevelfloor, xptarget, totalwords, numofposts, numconsec,
                                    wctarget, edtarget, edunit, active, lastposted, multiplier, dailywc, dailywcrem);

        dbHandler.updateProfile(profile);
        Log.d("Rob Debug","Updated profile");
    }

    public void saveState() {
        // Map editable fields to variables;
        name = mNameText.getText().toString();

        wctenthousands = wcNumPick1.getValue();
        wcthousands = wcNumPick2.getValue();
        wchundreds = wcNumPick3.getValue();
        wctens = wcNumPick4.getValue();
        wcones = wcNumPick5.getValue();
        Log.d("Rob Debug","WC Number Picker 1 = "+ wctenthousands);
        Log.d("Rob Debug","WC Number Picker 2 = "+ wcthousands);
        Log.d("Rob Debug","WC Number Picker 3 = "+ wchundreds);
        Log.d("Rob Debug","WC Number Picker 4 = "+ wctens);
        Log.d("Rob Debug","WC Number Picker 5 = "+ wcones);
        wctarget = (10000*wctenthousands)+(1000*wcthousands)+(100*wchundreds)+(10*wctens)+(1*wcones);
        Log.d("Rob Debug","New daily wordcount target = "+ wctarget);

        edtenthousands = edNumPick1.getValue();
        edthousands = edNumPick2.getValue();
        edhundreds = edNumPick3.getValue();
        edtens = edNumPick4.getValue();
        edones = edNumPick5.getValue();
        Log.d("Rob Debug","ED Number Picker 1 = "+ edtenthousands);
        Log.d("Rob Debug","ED Number Picker 2 = "+ edthousands);
        Log.d("Rob Debug","ED Number Picker 3 = "+ edhundreds);
        Log.d("Rob Debug","ED Number Picker 4 = "+ edtens);
        Log.d("Rob Debug","ED Number Picker 5 = "+ edones);
        edtarget = (10000*edtenthousands)+(1000*edthousands)+(100*edhundreds)+(10*edtens)+(1*edones);
        Log.d("Rob Debug","New daily editing target = "+ edtarget);
        // edunit already mapped by spinner

        if (mRowId == null) {
            createProfile();
        } else {
            updateProfile();
        }
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
            edunit = parent.getSelectedItemPosition();
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

//    public void newProfile (View view) {
//        Log.d("Rob Debug","About to call MyDBHandler");
//        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
//
//        int ranknum = Integer.parseInt(rankBox.getText().toString());
//
//        Prof_Handler profile = new Prof_Handler(nameBox.getText().toString(), ranknum);
//
//        dbHandler.addProfile(profile);
//        nameBox.setText("");
//        rankBox.setText("");
//    }
//
//    public void lookupProfile (View view) {
//        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
//
//        Prof_Handler profile = dbHandler.findProfile(nameBox.getText().toString());
//
//        if (profile != null) {
//            idView.setText(String.valueOf(profile.getID()));
//
//            rankBox.setText(String.valueOf(profile.getRanknum()));
//        } else {
//            idView.setText("No Match Found");
//        }
//    }
//
//    public void removeProfile (View view) {
//        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
//
//        boolean result = dbHandler.deleteProfile(
//                nameBox.getText().toString());
//
//        if (result)
//        {
//            idView.setText("Record Deleted");
//            nameBox.setText("");
//            rankBox.setText("");
//        }
//        else
//            idView.setText("No Match Found");
//    }
}

// Maybe move the following to a savestate method
//            Prof_Handler profile = new Prof_Handler();
//
//            profile.setRank("WordMonkey");
//            profile.setRanknum(1);
//            profile.setTotalXP(0);
//            profile.setTotXPToNext(0);
//            profile.setXPToNextRank(0);
//            profile.setXPLevelFloor(0);
//            profile.setXPTarget(0);
//            profile.setTotalWords(0);
//            profile.setNumOfPosts(0);
//            profile.setNumConsec(0);
//            profile.setWCTarget(1000);
//            profile.setEdTarget(0);
//            profile.setEdUnit(0);
//            profile.setActiveProf(1);
//            profile.setLastPosted(null);
//            profile.setMultiplier(1.0);
//            profile.setDailyWC(0);
//            profile.setDailyWCRem(1000);
