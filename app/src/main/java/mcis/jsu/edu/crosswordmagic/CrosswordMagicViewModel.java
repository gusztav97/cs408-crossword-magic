package mcis.jsu.edu.crosswordmagic;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class CrosswordMagicViewModel extends ViewModel {

    /* Application Context */

    private final MutableLiveData<Context> context = new MutableLiveData<Context>();

    /* Display Properties */

    private final MutableLiveData<Integer> windowOverheadDp = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> windowHeightDp = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> windowWidthDp = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> puzzleHeight = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> puzzleWidth = new MutableLiveData<Integer>();

    /* Puzzle Data */

    private final MutableLiveData<Integer> puzzleID = new MutableLiveData<Integer>();
    private final MutableLiveData<HashMap<String, Word>> words = new MutableLiveData<>();
    private final MutableLiveData<String> aClues = new MutableLiveData<String>();
    private final MutableLiveData<String> dClues = new MutableLiveData<String>();

    private final MutableLiveData<Character[][]> letters = new MutableLiveData<Character[][]>();
    private final MutableLiveData<Integer[][]> numbers = new MutableLiveData<Integer[][]>();

    /* Setters / Getters */

    public void setContext(Context c) {
        context.setValue(c);
    }

    public void setWindowHeightDp(int height) {
        windowHeightDp.setValue(height);
    }

    public void setWindowWidthDp(int width) {
        windowWidthDp.setValue(width);
    }

    public void setPuzzleHeight(int height) {
        puzzleHeight.setValue(height);
    }

    public void setPuzzleWidth(int width) {
        puzzleWidth.setValue(width);
    }

    public void setWindowOverheadDp(int width) {
        windowOverheadDp.setValue(width);
    }

    public void setPuzzleID(int id) {
        if ( (puzzleID.getValue() == null) || (puzzleID.getValue() != id) ) {
            getPuzzleData(id);
            puzzleID.setValue(id);
        }
    }

    public Context getContext() {
        return context.getValue();
    }

    public int getWindowHeightDp() {
        return windowHeightDp.getValue();
    }

    public int getWindowWidthDp() {
        return windowWidthDp.getValue();
    }

    public int getPuzzleHeight() {
        return puzzleHeight.getValue();
    }

    public int getPuzzleWidth() {
        return puzzleWidth.getValue();
    }

    public int getWindowOverheadDp() {
        return windowOverheadDp.getValue();
    }

    public int getPuzzleID() {
        return puzzleID.getValue();
    }

    public String getAClues() {
        return aClues.getValue();
    }

    public String getDClues() {
        return dClues.getValue();
    }

    public Character[][] getLetters() {
        return letters.getValue();
    }

    public Integer[][] getNumbers() {
        return numbers.getValue();
    }

    public HashMap<String, Word> getWords() {
        return words.getValue();
    }

    /* Load Puzzle Data from Input File */

    private void getPuzzleData(int id) {

        BufferedReader br = new BufferedReader(new InputStreamReader(context.getValue().getResources().openRawResource(id)));
        String line;
        String[] fields;

        HashMap<String, Word> wordMap = new HashMap<>();
        StringBuilder aString = new StringBuilder();
        StringBuilder dString = new StringBuilder();

        try {

            // Read from the input file using the "br" input stream shown above.  Your program
            // should get the puzzle height/width from the header row in the first line of the
            // input file.  Replace the placeholder values shown below with the values from the
            // file.  Get the data from the remaining rows, splitting each tab-delimited line
            // into an array of strings, which you can use to initialize a Word object.  Add each
            // Word object to the "wordMap" hash map; for the key names, use the box number
            // followed by the direction (for example, "16D" for Box # 16, Down).

            line = br.readLine();

            fields = line.split("\t");
            puzzleWidth.setValue(Integer.parseInt(fields[0]));
            puzzleHeight.setValue(Integer.parseInt(fields[1]));

            while((line = br.readLine()) != null){
                line.trim();
                fields = line.split("\t");
                if (fields[3].equals("A")){
                    aString.append(fields[4]);
                }

                if (fields[3].equals("D")){
                    dString.append(fields[4]);
                }
            }

        } catch (Exception e) {}

        words.setValue(wordMap);
        aClues.setValue(aString.toString());
        dClues.setValue(dString.toString());

        Character[][] aLetters = new Character[puzzleHeight.getValue()][puzzleWidth.getValue()];
        Integer[][] aNumbers = new Integer[puzzleHeight.getValue()][puzzleWidth.getValue()];

        for (int i = 0; i < aLetters.length; ++i) {
            Arrays.fill(aLetters[i], '*');
        }

        for (int i = 0; i < aNumbers.length; ++i) {
            Arrays.fill(aNumbers[i], 0);
        }

        for (HashMap.Entry<String, Word> e : wordMap.entrySet()) {

            Word w = e.getValue();

            // INSERT YOUR CODE HERE

        }

        this.letters.setValue(aLetters);
        this.numbers.setValue(aNumbers);

    }

}