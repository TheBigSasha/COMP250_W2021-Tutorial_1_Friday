public class TicTacToe {
    private static String name = "Super TicTacBros Ultimate 2: Electric Boogaloo";
    private Boolean[][] board;

    private  Boolean lastPlayer = null;

    public TicTacToe(int size){
        board = new Boolean[size][size];
    }

    public String play(int x, int y, Boolean xOrO){

        if(y > board.length - 1 || x > board[0].length -1){
            throw new IllegalArgumentException("yer outta bounds, son");
        }

        if(xOrO.equals(lastPlayer)){
            throw new IllegalArgumentException("Ya can't play twice, fool!");
        }

        lastPlayer = xOrO;

        if(board[y][x] != null){
            throw new IllegalArgumentException("Cannot place where there is a thing oops");
        }

        board[y][x] = xOrO;

        Boolean winner = null;
        if(verticalWinner(board) != null) winner = verticalWinner(board);
        if(horizontalWinner(board) != null) winner = horizontalWinner(board);
        if(diagonalWinner(board) != null) winner = diagonalWinner(board);

        if(winner != null){
            display();
            board = new Boolean[board.length][board.length];
            if(winner){
                return "X wins";
            }else{
                return "O wins";
            }
        }

        return "";
    }

    public void display() {
    for(Boolean[] row : board){
        for(Boolean item : row){
            if(item == null){
                System.out.print("-");

            } else if (item) {
                System.out.print("x");
            }else{
                System.out.print("o");
            }
        }
        System.out.println();
    }
    }

    public static Boolean verticalWinner(Boolean[][] board){
        //Check verticals, meaning we check that lines that are vertical on the board
        //Have 3 of the same not-null symbol in a row.
        for(int i = 0; i <  board[0].length; i++){
            boolean columnAllEqual = true;
            Boolean columnFirst = board[0][i];
            if(columnFirst != null){
                for(int j = 1; j < board.length; j++){
                    if(!columnFirst.equals(board[j][i])){
                        columnAllEqual = false;
                        break;
                    }
                }

                if(columnAllEqual){
                    return columnFirst;
                }
            }else{
                continue;
            }

        }

        return null;
    }

    public static Boolean horizontalWinner(Boolean[][] board){
        //In tic tac tow, 3 values exist, X, O, or nothing
        //In Boolean, 3 values exist, true, false, or null
        //We use our Boolean to save the most memory and be very efficient so we use a type with 3 values

        for(Boolean[] row : board){
            //We have no int i, so we don't have to say board[i][j]
            boolean rowAllEqual = true;

            //Assume the thing is true and prove it wrong if its not.

            if(row[0] == null){         //If the first thing is empty, we can't win so we skip this row!
                continue;
            }else{                      //The first thing isn't empty, now we check that the rest is the same as the first!
                for(int i = 1;i < row.length; i++){
                    if (!row[i - 1].equals(row[i])) {
                        rowAllEqual = false;
                        break;
                    }
                }

                if(rowAllEqual){
                    return  row[0];
                }
            }
        }

        return null;
    }

    public static Boolean diagonalWinner(Boolean[][] board){
        Boolean first = board[0][0];
        Boolean second = board[0][board.length - 1];
        boolean checkFirst = true;
        boolean checkSecond = true;

        if(first == null){
            checkFirst = false;
        }

        if(second == null){
            checkSecond = false;
        }

        for(int i = 0; i < board.length; i++){
            if(checkFirst){
                //i,i
                //0,0
                //1,1
                //2,2
                //...
                //length - 1, length - 1
                if(!first.equals(board[i][i])){
                    //Not null before .equals, maybe null after .equals
                    checkFirst = false;
                }
            }
            if(checkSecond){
                //i, length - 1 - i
                //0, length,
                //1, length -1
                //...
                //length, 0
                if(!second.equals(board[i][board.length -1 - i])){
                    //Not null before .equals, maybe null after .equals
                    checkFirst = false;
                }
            }
        }

        if(checkFirst){
            return first;
        }

        if(checkSecond){
            return  second;
        }

        return null;
    }

    public static String getName() {
        return name;
    }

    public Boolean[][] getBoard() {
        return board;
    }

}
