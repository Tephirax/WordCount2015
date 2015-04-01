package com.generationminusone.wordcount2015;

/**
 * Created by Rob on 15/03/2015.
 * Setup & Handler for Profiles database table
 */
public class Prof_Handler {
    private Long _id;
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
    private int _edtarget;
    private int _edunit;
    private int _activeprof;
    private String _lastposted;
    private double _multiplier;
    private int _dailywc;
    private int _dailywcrem;

    public Prof_Handler() {

    }

    // Constructor with all variables attached
    public Prof_Handler(Long id, String name, String rank, int ranknum, int totalxp, int totxptonext,
                        int xptonextrank, int xplevelfloor, int xptarget, int totalwords, int numofposts,
                        int numconsec, int wctarget, int edtarget, int edunit, int activeprof, String lastposted,
                        double multiplier, int dailywc, int dailywcrem) {
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
        this._edtarget = edtarget;
        this._edunit = edunit;
        this._activeprof = activeprof;
        this._lastposted = lastposted;
        this._multiplier = multiplier;
        this._dailywc = dailywc;
        this._dailywcrem = dailywcrem;
    }
    // Constructor for profile object where id hasn't yet been assigned (ie. to pass during creation)
    public Prof_Handler(String name, String rank, int ranknum, int totalxp, int totxptonext,
                        int xptonextrank, int xplevelfloor, int xptarget, int totalwords, int numofposts,
                        int numconsec, int wctarget, int edtarget, int edunit, int activeprof, String lastposted,
                        double multiplier, int dailywc, int dailywcrem) {
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
        this._edtarget = edtarget;
        this._edunit = edunit;
        this._activeprof = activeprof;
        this._lastposted = lastposted;
        this._multiplier = multiplier;
        this._dailywc = dailywc;
        this._dailywcrem = dailywcrem;
    }

    public void setID(Long id) {
        this._id = id;
    }
    public Long getID() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }
    public String getName() {
        return this._name;
    }

    public void setRank(String rank) { this._rank = rank; }
    public String getRank() { return this._rank; }

    public void setRanknum(int ranknum) {
        this._ranknum = ranknum;
    }
    public int getRanknum() {
        return this._ranknum;
    }

    public void setTotalXP(int totalxp) { this._totalxp = totalxp; }
    public int getTotalXP() { return this._totalxp; }

    public void setTotXPToNext(int totxptonext) { this._totxptonext = totxptonext; }
    public int getTotXPToNext() { return this._totxptonext; }

    public void setXPToNextRank(int xptonextrank) { this._xptonextrank = xptonextrank; }
    public int getXPToNextRank() { return this._xptonextrank; }

    public void setXPLevelFloor(int xplevelfloor) { this._xplevelfloor = xplevelfloor; }
    public int getXPLevelFloor() { return this._xplevelfloor; }

    public void setXPTarget(int xptarget) { this._xptarget = xptarget; }
    public int getXPTarget() { return this._xptarget; }

    public void setTotalWords(int totalwords) { this._totalwords = totalwords; }
    public int getTotalWords() { return this._totalwords; }

    public void setNumOfPosts(int numofposts) { this._numofposts = numofposts; }
    public int getNumOfPosts() { return this._numofposts; }

    public void setNumConsec(int numconsec) { this._numconsec = numconsec; }
    public int getNumConsec() { return this._numconsec; }

    public void setWCTarget(int wctarget) { this._wctarget = wctarget; }
    public int getWCTarget() { return this._wctarget; }

    public void setEdTarget(int edtarget) { this._edtarget = edtarget; }
    public int getEdTarget() { return this._edtarget; }

    public void setEdUnit(int edunit) { this._edunit = edunit; }
    public int getEdUnit() { return this._edunit; }

    public void setActiveProf(int activeprof) { this._activeprof = activeprof; }
    public int getActiveProf() { return this._activeprof; }

    public void setLastPosted(String lastposted) { this._lastposted = lastposted; }
    public String getLastPosted() { return this._lastposted; }

    public void setMultiplier(double multiplier) { this._multiplier = multiplier; }
    public double getMultiplier() { return this._multiplier; }

    public void setDailyWC(int dailywc) { this._dailywc = dailywc; }
    public int getDailyWC() { return this._dailywc; }

    public void setDailyWCRem(int dailywcrem) { this._dailywcrem = dailywcrem; }
    public int getDailyWCRem() { return this._dailywcrem; }

}