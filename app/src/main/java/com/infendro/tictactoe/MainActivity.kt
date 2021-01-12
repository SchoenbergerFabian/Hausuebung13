package com.infendro.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var tictactoe = TicTacToe()

    var player = 'O'
    var freeSpaces = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reloadFreeSpaces()
        reloadPlayer()
    }

    fun setButtonText(view: View){
        if(player=='O'){
            val button = view as Button
            if(button.text.toString().equals("")){
                button.text=player.toString()
                endRound()
            }
        }
    }

    fun tableToArray(table : TableLayout): Array<Array<Int>> {
        val rows = table.children.toList() as List<TableRow>
        val array = Array(rows.count()) { Array(0) {0} }
        for((indexRow,row) in rows.withIndex()){
            val buttons = row.children.toList() as List<Button>
            array[indexRow]=Array(buttons.size) {0}
            for((indexColumn,button) in buttons.withIndex()){
                val text = button.text.toString()
                when(text){
                    "X" -> {
                        array[indexRow][indexColumn] = 1
                    }
                    "O" -> {
                        array[indexRow][indexColumn] = -1
                    }
                }
            }
        }
        return array
    }

    fun endRound(){
        freeSpaces--
        reloadFreeSpaces()

        val table = tableToArray(table)

        val winner = tictactoe.determineWinner(table)
        if(winner==1){
            end('X')
            return
        }else if(winner==-1){
            end('O')
            return
        }else if(tictactoe.isFull(table)){
            end(' ')
            return
        }

        when(player){
            'X' -> player = 'O'
            'O' -> player = 'X'
        }
        reloadPlayer()

        if(player=='X'){
            val minimax = MiniMax()
            minimaxSetButtonText(minimax.getBestMove(table))
        }
    }

    fun minimaxSetButtonText(move: IntArray){
        val rows = table.children.toList() as List<TableRow>
        val buttons = rows[move[0]].children.toList() as List<Button>
        buttons[move[1]].text=player.toString()
        endRound()
    }

    fun reloadFreeSpaces(){
        textViewFreeSpaces.text = ""+freeSpaces
    }

    fun reloadPlayer(){
        textViewPlayer.text = "Spieler: "+player
    }

    fun end(char: Char) {
        if(char==' '){
            showDraw()
        }else{
            showWin(char)
        }
        reset()
    }

    fun showWin(char: Char){
        Toast.makeText(this, "$char has won!",Toast.LENGTH_LONG).show()
    }

    fun showDraw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_LONG).show()
    }

    fun reset() {
        player = 'O'
        reloadPlayer()
        freeSpaces = 9
        reloadFreeSpaces()
        resetTable()
    }

    fun resetTable(){
        val rows = table.children.toList() as List<TableRow>
        for(row in rows){
            val buttons = row.children.toList() as List<Button>
            for(button in buttons){
                button.text=""
            }
        }
    }

}