package com.generationminusone.wordcount2015;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Prof_Detail_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;

    private EditText mNameText;
    private TextView mWordcountTargetText;
    private TextView mEditcountTargetText;
    private TextView mEditunitTargetText;
    private TextView mRankText;
    private TextView mRankNum;
    private TextView mTotalXP;
    private TextView mXPToNextRank;
    private TextView mTotalWords;
    private TextView mActive;

    private Long mRowId;

    private NumberPicker mNumPick1;
    private NumberPicker mNumPick2;
    private NumberPicker mNumPick3;
    private NumberPicker mNumPick4;
    private NumberPicker mNumPick5;
    private Button submitButton;
    private Button cancelButton;
    private Button mWordcountTargetButton;
    public int tenthousands;
    public int thousands;
    public int hundreds;
    public int tens;
    public int ones;

    public int target;
    private String rankname;
    private int ranknum;
    private int totxp;
    private int xptonext;
    private int xptarget;
    private int totwc;
    private String activestr;
    private String lastposted;
    public static int wordcounttargetnum;
    public static int wordcounttarget;
    public static int activeProfile;

    public Prof_Detail_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prof_list, null);
        Log.d("Rob Debug", "Arrived in Prof_List_Fragment; inflating view");

        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        // Get profile ID from Main Activity
        mRowId = ((Main_Activity)getActivity()).profID;

        if (mRowId == null) {
            // TODO: Go to Create Profile fragment
        }

        // Create Prof_Handler object from database entry
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);
        Prof_Handler profile = dbHandler.fetchProfile(mRowId);


        // Find Views
        mNameText = (EditText) rootView.findViewById(R.id.profile_name);
        mWordcountTargetText = (EditText) rootView.findViewById(R.id.targetwordcount);
        mWordcountTargetButton = (Button) rootView.findViewById(R.id.targetbutton);

        mRankText = (TextView) findViewById(R.id.rank);
        mRankNum = (TextView) findViewById(R.id.ranknum);
        mTotalXP = (TextView) findViewById(R.id.totalxp);
        mXPToNextRank = (TextView) findViewById(R.id.xptonextrank);
        mTotalWords = (TextView) findViewById(R.id.totalwords);
        mActive = (TextView) findViewById(R.id.active);

        mWordcountTargetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Dialog targetdialog = new Dialog(getActivity());
                targetdialog.setContentView(R.layout.numpick_dialog);
                targetdialog.setTitle("Set Daily Wordcount Target");
                targetdialog.setCancelable(true);

                mNumPick1 = (NumberPicker) targetdialog.findViewById(R.id.Picker1);
                mNumPick2 = (NumberPicker) targetdialog.findViewById(R.id.Picker2);
                mNumPick3 = (NumberPicker) targetdialog.findViewById(R.id.Picker3);
                mNumPick4 = (NumberPicker) targetdialog.findViewById(R.id.Picker4);
                mNumPick5 = (NumberPicker) targetdialog.findViewById(R.id.Picker5);

                        /* Assign Max/Min values to each Picker */
                mNumPick1.setMinValue(0);
                mNumPick1.setMaxValue(9);
                mNumPick2.setMinValue(0);
                mNumPick2.setMaxValue(9);
                mNumPick2.setValue(1); /* Set default value to 1000 */
                mNumPick3.setMinValue(0);
                mNumPick3.setMaxValue(9);
                mNumPick4.setMinValue(0);
                mNumPick4.setMaxValue(9);
                mNumPick5.setMinValue(0);
                mNumPick5.setMaxValue(9);

                submitButton = (Button) targetdialog.findViewById(R.id.submit);
                cancelButton = (Button) targetdialog.findViewById(R.id.cancel);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tenthousands = mNumPick1.getValue();
                        thousands = mNumPick2.getValue();
                        hundreds = mNumPick3.getValue();
                        tens = mNumPick4.getValue();
                        ones = mNumPick5.getValue();
                        Log.d("Rob Debug","Number Picker 1 = "+ tenthousands);
                        Log.d("Rob Debug","Number Picker 2 = "+ thousands);
                        Log.d("Rob Debug","Number Picker 3 = "+ hundreds);
                        Log.d("Rob Debug","Number Picker 4 = "+ tens);
                        Log.d("Rob Debug","Number Picker 5 = "+ ones);
                        target = (10000*tenthousands)+(1000*thousands)+(100*hundreds)+(10*tens)+(1*ones);
                        Log.d("Rob Debug","New daily target = "+ target);

                        //populateFields();
                        targetdialog.dismiss();
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        targetdialog.cancel();
                    }
                });

                targetdialog.show();

            }
        });




//        idView = (TextView) rootView.findViewById(R.id.profile_ID);
//        nameBox = (EditText) rootView.findViewById(R.id.profile_name);
//        rankBox = (EditText) rootView.findViewById(R.id.rankNum);
//
//        Button btnAdd = (Button) rootView.findViewById(R.id.button1);
//        Button btnFind = (Button) rootView.findViewById(R.id.button2);
//        Button btnDelete = (Button) rootView.findViewById(R.id.button3);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Rob Debug","About to call newProfile");
//                newProfile(v);
//            }
//        });
//        btnFind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lookupProfile(v);
//            }
//        });
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeProfile(v);
//            }
//        });

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
//        if (mRowId != null) {
//            Cursor prof = mDbHelper.fetchProfile(mRowId);
//            startManagingCursor(prof);
//            name = prof.getString(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_NAME));
//            mNameText.setText(name);
//		        /* Populate SexSpinner */
//            mSexString = prof.getString(
//                    prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_SEX));
//            ArrayAdapter adapter = (ArrayAdapter) mSexSpinner.getAdapter();
//            int spinnerPosition = adapter.getPosition(mSexString);
//            mSexSpinner.setSelection(spinnerPosition);	//set the default according to value
//			    /* */
//            target = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_WORDCOUNTTARGET));
//            if (String.valueOf(target) != null) { mWordcountTargetText.setText(String.valueOf(target)); }
//            rankname = prof.getString(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_RANK));
//            mRankText.setText(rankname);
//            ranknum = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_RANKNUM));
//            if (String.valueOf(ranknum) != null) { mRankNum.setText(String.valueOf(ranknum)); }
//            totxp = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_TOTALXP));
//            if (String.valueOf(totxp) != null) { mTotalXP.setText(String.valueOf(totxp)); }
//            xptonext = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_XPTONEXTRANK));
//            if (String.valueOf(xptonext) != null) { mXPToNextRank.setText(String.valueOf(xptonext)); }
//            totwc = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_TOTALWORDS));
//            if (String.valueOf(totwc) != null) { mTotalWords.setText(String.valueOf(totwc)); }
//            activenum = prof.getInt(prof.getColumnIndexOrThrow(ProjDbAdapter.KEY_ACTIVE));
//            if (activenum == 1) {
//                activestr = "Yes";
//            }
//            else {
//                activestr = "No";
//            }
//            mActive.setText(activestr);
//        } else if (target > 0) { mWordcountTargetText.setText(String.valueOf(target)); }

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
