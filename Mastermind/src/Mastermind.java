/*
 * Mastermind game
 * Assaf Mor assafm04 036539096
 * oop 2013-2014
 */
import il.ac.huji.cs.oop.mastermind.*;
public class Mastermind{
    public static void main(String[] args) {    
        // initialize parameters
        int games = 0;
        int wins = 0;
        int guess = 0;
        int gameGuess = 0;
        int codeLength = 0;
		int numOfValues = 0;
		int numOfGuesses = 0;
        double winRate = 0;
        double turnsPerWin = 0.0/0.0;
        boolean changeParameters = true;
        boolean nextGame;
        boolean correct = false;
         
        MastermindUI ui = MastermindUIFactory.newMastermindUI();
        
        //play the game
        do {
            // reset codeLength, numOfValues and numOfGuesses.
            // runs for the first time and if user wish to change parameters.
            if (changeParameters){
                codeLength = getCodeLength(ui);
                numOfValues = getNumValues(ui);
                numOfGuesses = getNumGuesses(ui);
                ui.reset(codeLength, numOfValues, numOfGuesses);
            }
            else { 
            	//clear settings.
                ui.clear();
            }
            //generate random code.
            Code code = CodeGenerator.newCode(codeLength, numOfValues); 
            
            for (int i = numOfGuesses; i > 0 && !correct; i--){
            	int  bulls = 0;
                int cows = 0;
                Code getCode = ui.askGuess("Please enter your guess: ", codeLength);
                //count the bulls and cows
                bulls = bullsCounter(codeLength, code, getCode, bulls);
                cows = cowsCounter(codeLength, numOfValues, code, getCode, bulls, cows);
                
                gameGuess++;
                ui.showGuessResult(getCode, bulls, cows);
                
                //if the guess match the code
                correct = code.equals(getCode);
            }
            //display message according to game outcome
            ui.displayMessage(!correct ? "You failed to guess the code, Better luck next time"
            					: "You guessed it right in "+ gameGuess + " turns!");
            games++;
            //update game statistics
            if (correct==true){
                wins++;
                guess += gameGuess;
                turnsPerWin = guess/(double)wins;
            }
 
            winRate = wins/(double)games;
            ui.showStats(games, wins, winRate, turnsPerWin);
            //ask if another game is wanted
            nextGame = ui.askYesNo("Would you like to play again?");
            if (nextGame==true){
                changeParameters = ui.askYesNo("Would you like to change the game's parameters?");
                //reset game's parameters
                if (changeParameters==true){ 
                    games = 0;
                    wins = 0;
                    winRate = 0;
                    turnsPerWin = 0.0/0.0;
                    guess = 0;
                }
                gameGuess = 0;
                correct = false;
            }
            else{ 
            	//close the game if another game is not wished for
                ui.close();
            }
        } while (nextGame==true);
 
    }
     
    /**
     * a private method which sets the requested code length from the user
     * and displays an error message if the input is not a positive integer.
     * @param ui
     * @return codeLength
     */
    private static int getCodeLength(MastermindUI ui) {
        int codeLength;
        codeLength = ui.askNumber("Enter code length: ");
        while (codeLength <=0 ){ 
            ui.displayErrorMessage("Value must be positive!wrong ");
            codeLength = ui.askNumber("Enter code length: ");
        }
        return codeLength;
    }
    /**
     * a private method which sets the requested number of values a code will be consisted of 
     * and displays an error message if the input is not a positive integer.
     * @param ui
     * @return numOfValues
     */
    private static int getNumValues(MastermindUI ui) {
        int numOfValues;
        numOfValues = ui.askNumber("Enter number of values: "); 
        while (numOfValues <=0 ){
            ui.displayErrorMessage("Value must be positive!");
            numOfValues = ui.askNumber("Enter number of values: ");
        }
        return numOfValues;
    }
    /**
     * a private method which sets the requested max guesses possible for a game 
     * and displays an error message if the input is not a positive integer.
     * @param ui
     * @return numOfGuesses
     */
    private static int getNumGuesses(MastermindUI ui) {
        int numOfGuesses;
        numOfGuesses = ui.askNumber("Enter max number of guesses: "); 
        while (numOfGuesses <=0 ){ 
            ui.displayErrorMessage("Value must be positive!");
            numOfGuesses = ui.askNumber("Enter max number of guesses: ");
        }
        return numOfGuesses;
    }
    /**
     * a private method which run over a given code and a given guess and return
     * the number of appearances of the same digits in same index.
     * @param codeLength
     * @param setCode
     * @param getCode
     * @param bulls
     * @return bulls
     */
    private static int bullsCounter(int codeLength, Code setCode, Code getCode,
            int bulls) {
        for (int j = 1; j <= codeLength; j++){
            if (getCode.getValue(j) == setCode.getValue(j)){
                bulls++;
            }
        }
        return bulls;
    }
    /**
     * a private method which counts all the cows in the guess.
     * the method runs on both the code and guess and counts all the appearances of the same digit
     * then deduces the number of bulls, afterward adding to the cows found to  
     * the toll number of cows found yet.
     * @param codeLength
     * @param numOfValues
     * @param setCode
     * @param getCode
     * @param bulls
     * @param cows
     * @return cows
     */
    private static int cowsCounter(int codeLength, int numOfValues, Code setCode, Code getCode, 
                                                                        int bulls, int cows) {
        int codeCows = 0;
        int guessCows = 0;
        for (int i = 0; i<= numOfValues; i++){
            //counts  the number of cows in the guess and code per given digit.
            for (int k = 1; k <= codeLength ; k++){
                codeCows += (setCode.getValue(k) == i) ? 1 : 0;
                guessCows += (getCode.getValue(k) == i) ? 1 : 0;
            }
            // add to cow counting the minimal value guessCows or codeCows.
            cows += (codeCows > guessCows) ? guessCows : codeCows;
            codeCows = 0;
            guessCows = 0;
        }
        cows -= bulls;
        return cows;
    }
}

