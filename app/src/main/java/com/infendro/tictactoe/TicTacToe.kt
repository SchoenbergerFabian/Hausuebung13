package com.infendro.tictactoe

class TicTacToe {

    /*
     * returns 1 if Player 1 won, -1 if Player 2 won, 0 if noone won and the table is not yet full and -2 if noone won and the table is full
     */
    fun determineWinner(table: Array<Array<Int>>) : Int {
        //check rows and columns
        var rowSum = 0
        var columnSum = 0

        for(row_i in 0..2) {
            for(column_i in 0..2){
                rowSum+=table[row_i][column_i]
                columnSum+=table[column_i][row_i]
            }
            if(rowSum==3||columnSum==3){
                return 1
            }else if(rowSum==-3||columnSum==-3){
                return -1
            }else{
                rowSum=0
                columnSum=0
            }
        }

        //check diagonals
        //top-left to bottom-right
        var diagonal = 0

        for(diagonal_i in 0..2){
            diagonal+=table[diagonal_i][diagonal_i]
        }
        if(diagonal==3){
            return 1
        }else if(diagonal==-3){
            return -1
        }else{
            diagonal=0
        }

        //top-right to bottom-left
        for(diagonal_i in 0..2){
            diagonal+=table[table.size-1-diagonal_i][diagonal_i]
        }
        if(diagonal==3){
            return 1
        }else if(diagonal==-3){
            return -1
        }

        return 0
    }

    fun isFull(table: Array<Array<Int>>) : Boolean {
        var full = true
        for(row_i in 0..2) {
            for(column_i in 0..2){
                if(table[row_i][column_i]==0){
                    full = false
                }
            }
        }
        return full
    }
}