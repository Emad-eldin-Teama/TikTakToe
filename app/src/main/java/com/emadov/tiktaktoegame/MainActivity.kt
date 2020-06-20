package com.emadov.tiktaktoegame

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnReset = findViewById(R.id.btnReset) as Button
        btnReset.setOnClickListener()
        {
            Toast.makeText(this@MainActivity, "Good Bye.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(
                {
                    // This method will be executed once the timer is over
                    moveTaskToBack(true);
                    exitProcess(-1)
                },
                500 // value in milliseconds
            )
        }

    }

    public fun buSelect(view: View) {
        val buChoise = view as Button
        var cellID = 0


        when (buChoise.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }
        Log.d("cellID:", cellID.toString())
        playgame(cellID, buChoise)


    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activeplayer = 1
    fun playgame(cellID: Int, buChoise: Button) {
        if (activeplayer == 1) {
            buChoise.text = "X"
            buChoise.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            activeplayer = 2
            autoPlay()
        }
        if (activeplayer == 2) {
            buChoise.text = "O"
            buChoise.setBackgroundResource(R.color.red)
            player2.add(cellID)
            activeplayer = 1
        }
        buChoise.isEnabled = false
        checkwinner()
    }

    fun checkwinner() {
        var winner = -1
        //raw 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1

        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //raw2

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //raw 3

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //column1

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        // sambokx1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //sambokx2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "The first player won the game!!", Toast.LENGTH_LONG).show()
                bu1.isEnabled = false
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false
                Handler().postDelayed(
                    {
                        // This method will be executed once the timer is over
                        startActivity(Intent(this, MainActivity::class.java))
                    },
                    1000 // value in milliseconds
                )


            } else {
                Toast.makeText(this, "The second player won the game!!", Toast.LENGTH_LONG).show()
                bu1.isEnabled = false
                bu2.isEnabled = false
                bu3.isEnabled = false
                bu4.isEnabled = false
                bu5.isEnabled = false
                bu6.isEnabled = false
                bu7.isEnabled = false
                bu8.isEnabled = false
                bu9.isEnabled = false

                Handler().postDelayed(
                    {
                        // This method will be executed once the timer is over
                        startActivity(Intent(this, MainActivity::class.java))

                    },
                    1000 // value in milliseconds
                )


            }


        }

    }

    //select empty cells
    fun autoPlay() {
        val emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        val r = Random
        val randIndex = r.nextInt(emptyCells.size - 0) + 0
        val CellID = emptyCells[randIndex]
        var buSelect: Button?
        when (CellID) {
            1 -> buSelect = bu1
            2 -> buSelect = bu2
            3 -> buSelect = bu3
            4 -> buSelect = bu4
            5 -> buSelect = bu5
            6 -> buSelect = bu6
            7 -> buSelect = bu7
            8 -> buSelect = bu8
            9 -> buSelect = bu9
            else -> {
                buSelect = null
            }
        }
        if (buSelect != null) {
            playgame(CellID, buSelect)
        }

    }

    fun btReset(view: View) {
        val buChoise = view as Button
        var cellID = 0
        buChoise.text = ""
        buChoise.setBackgroundResource(R.color.white)
        activeplayer = 1
    }


}


