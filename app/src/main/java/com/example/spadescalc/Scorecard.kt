package com.example.spadescalc

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*


class Scorecard {

    var group1Player1HandBids by mutableIntStateOf(0)
    var group1Player2HandBids by mutableIntStateOf(0)
    var group2Player1HandBids by mutableIntStateOf(0)
    var group2Player2HandBids by mutableIntStateOf(0)
    var group1Player1HandTricks by mutableIntStateOf(0)
    var group1Player2HandTricks by mutableIntStateOf(0)
    var group2Player1HandTricks by mutableIntStateOf(0)
    var group2Player2HandTricks by mutableIntStateOf(0)


    var group1HandBids by mutableIntStateOf(0)
        private set
    var group2HandBids by mutableIntStateOf(0)
        private set

    var group1HandTricks by mutableIntStateOf(0)
        private set
    var group2HandTricks by mutableIntStateOf(0)
        private set

    var group1TotalBags by mutableIntStateOf(0)
        private set
    var group2TotalBags by mutableIntStateOf(0)
        private set

    var group1TotalPoints by mutableIntStateOf(0)
        private set
    var group2TotalPoints by mutableIntStateOf(0)
        private set

    /////////////////////////////////

    // partner bids are still calculated together even if one bids nil
    fun calculateG1() {

        group1HandBids = group1Player1HandBids + group1Player2HandBids
        group1HandTricks = group1Player1HandTricks + group1Player2HandTricks
        updateG1Tricks()

        if ((group1Player1HandBids == 0) or (group1Player2HandBids == 0)) {
            if ((group1Player1HandBids == 0) and (group1Player1HandTricks == 0)) {
                group1TotalPoints += 100
            } else if ((group1Player1HandBids == 0) and (group1Player1HandTricks != 0)) {
                group1TotalPoints -= 100
            }
            if ((group1Player2HandBids == 0) and (group1Player2HandTricks == 0)) {
                group1TotalPoints += 100
            } else if ((group1Player2HandBids == 0) and (group1Player2HandTricks != 0)) {
                group1TotalPoints -= 100
            }
        }

        group1Player1HandBids=0
        group1Player2HandBids=0
        group1Player1HandTricks=0
        group1Player2HandTricks=0


    }
    fun calculateG2() {

        group2HandBids = group2Player1HandBids + group2Player2HandBids
        group2HandTricks = group2Player1HandTricks + group2Player2HandTricks
        updateG2Tricks()

        if ((group2Player1HandBids == 0) or (group2Player2HandBids == 0)) {
            if ((group2Player1HandBids == 0) and (group2Player1HandTricks == 0)) {
                group2TotalPoints += 100
            } else if ((group2Player1HandBids == 0) and (group2Player1HandTricks != 0)) {
                group2TotalPoints -= 100
            }
            if ((group2Player2HandBids == 0) and (group2Player2HandTricks == 0)) {
                group2TotalPoints += 100
            } else if ((group2Player2HandBids == 0) and (group2Player2HandTricks != 0)) {
                group2TotalPoints -= 100
            }
        }

        group2Player1HandBids=0
        group2Player2HandBids=0
        group2Player1HandTricks=0
        group2Player2HandTricks=0
    }

    fun updateG1Tricks() {
        if (group1HandBids <= group1HandTricks) {
            group1TotalBags += (group1HandTricks - group1HandBids)
            group1TotalPoints += group1HandBids*10 + (group1HandTricks - group1HandBids)
        }
        else{
            group1TotalPoints -= group1HandBids*10
        }

        if (group1TotalBags>=10) {
            group1TotalBags -= 10
            group1TotalPoints -= 100

        }
    }
    fun updateG2Tricks() {
        if (group2HandBids <= group2HandTricks) {
            group2TotalBags += (group2HandTricks - group2HandBids)
            group2TotalPoints += group2HandBids*10 + (group2HandTricks - group2HandBids)
        }
        else{
            group2TotalPoints -= group2HandBids*10
        }

        if (group2TotalBags>=10) {
            group2TotalBags -= 10
            group2TotalPoints -= 100

        }
    }

}


