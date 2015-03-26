package com.generationminusone.wordcount2015;

/**
 * Created by Rob on 15/03/2015.
 * Setup & Handler for Profiles database table
 */
public class Prof_Handler {
    private int _id;
    private String _name;
    private String _rank;
    private int _ranknum;
    private int _totalxp;
    private int _totxptonext;
    private int _xptonextrank;
    private int _xplevelfloor;
    private int _xptarget;
    private int _totalwords;
    private int _numofposts;
    private int _numconsec;
    private int _wctarget;
    private int _active;
    private String _lastposted;
    private double _multiplier;
    private int _dailywc;
    private int _dailywcrem;

    public Prof_Handler() {

    }

    // Constructor with all variables attached
    public Prof_Handler(int id, String name, String rank, int ranknum, int totalxp, int totxptonext,
                        int xptonextrank, int xplevelfloor, int xptarget, int totalwords, int numofposts,
                        int numconsec, int wctarget, int active, String lastposted, double multiplier,
                        int dailywc, int dailywcrem) {
        this._id = id;
        this._name = name;
        this._rank = rank;
        this._ranknum = ranknum;
        this._totalxp = totalxp;
        this._totxptonext = totxptonext;
        this._xptonextrank = xptonextrank;
        this._xplevelfloor = xplevelfloor;
        this._xptarget = xptarget;
        this._totalwords = totalwords;
        this._numofposts = numofposts;
        this._numconsec = numconsec;
        this._wctarget = wctarget;
        this._active = active;
        this._lastposted = lastposted;
        this._multiplier = multiplier;
        this._dailywc = dailywc;
        this._dailywcrem = dailywcrem;
    }
    // Constructor for profile object where id hasn't yet been assigned (ie. to pass during creation)
    public Prof_Handler(String name, String rank, int ranknum, int totalxp, int totxptonext,
                        int xptonextrank, int xplevelfloor, int xptarget, int totalwords, int numofposts,
                        int numconsec, int wctarget, int active, String lastposted, double multiplier,
                        int dailywc, int dailywcrem) {
        this._name = name;
        this._rank = rank;
        this._ranknum = ranknum;
        this._totalxp = totalxp;
        this._totxptonext = totxptonext;
        this._xptonextrank = xptonextrank;
        this._xplevelfloor = xplevelfloor;
        this._xptarget = xptarget;
        this._totalwords = totalwords;
        this._numofposts = numofposts;
        this._numconsec = numconsec;
        this._wctarget = wctarget;
        this._active = active;
        this._lastposted = lastposted;
        this._multiplier = multiplier;
        this._dailywc = dailywc;
        this._dailywcrem = dailywcrem;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setRanknum(int ranknum) {
        this._ranknum = ranknum;
    }

    public int getRanknum() {
        return this._ranknum;
    }
}