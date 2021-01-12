package com.infendro.tictactoe

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MiniMax {

    private val MAX_DEPTH = 6

    val tictactoe = TicTacToe()

    fun getBestMove(table: Array<Array<Int>>): IntArray {
        println(""+table[0][0]+table[0][1]+table[0][2])
        println(""+table[1][0]+table[1][1]+table[1][2])
        println(""+table[2][0]+table[2][1]+table[2][2])
        val bestMove = intArrayOf(-1, -1)
        var bestValue = Int.MIN_VALUE

        for (row_i in 0..2) {
            for (col_i in 0..2) {
                if (table[row_i][col_i]==0) {
                    table[row_i][col_i]=1
                    val moveValue = miniMax(table, MAX_DEPTH, false)
                    table[row_i][col_i]=0
                    if (moveValue > bestValue) {
                        bestMove[0] = row_i
                        bestMove[1] = col_i
                        bestValue = moveValue
                    }
                }
            }
        }
        println(""+bestMove[0]+" "+bestMove[1])
        return bestMove
    }

    fun miniMax(table: Array<Array<Int>>, depth: Int, isMax: Boolean): Int {
        val tableValue: Int = tictactoe.determineWinner(table)

        // win/lose/draw or max depth reached.
        if (abs(tableValue)==1 || depth == 0 || tictactoe.isFull(table)) {
            if(tableValue==1){
                return tableValue+depth
            }else if(tableValue==-1){
                return tableValue-depth
            }else{
                return tableValue
            }
        }

        // Maximising player, find the maximum attainable value.
        return if (isMax) {
            var highestVal = Int.MIN_VALUE
            for (row_i in 0..2) {
                for (col_i in 0..2) {
                    if (table[row_i][col_i]==0) {
                        table[row_i][col_i]=1
                        highestVal = max(highestVal, miniMax(table, depth - 1, false))
                        table[row_i][col_i] = 0
                    }
                }
            }
            highestVal
            // Minimising player, find the minimum attainable value;
        } else {
            var lowestVal = Int.MAX_VALUE
            for (row_i in 0..2) {
                for (col_i in 0..2) {
                    if (table[row_i][col_i]==0) {
                        table[row_i][col_i]=-1
                        lowestVal = min(lowestVal, miniMax(table, depth - 1, true))
                        table[row_i][col_i]=0
                    }
                }
            }
            lowestVal
        }
    }
}