package com.generationminusone.wordcount2015;

/**
 * Created by Rob on 15/03/2015.
 * Setup & Handler for Profiles database table
 */
public class Prof_Handler {
    private int _id;
    private int _ranknum;
    private String _name;

    public Prof_Handler() {

    }

    public Prof_Handler(int id, int ranknum, String name) {
        this._id = id;
        this._ranknum = ranknum;
        this._name = name;
    }

    public Prof_Handler(String name, int ranknum) {
        this._name = name;
        this._ranknum = ranknum;
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