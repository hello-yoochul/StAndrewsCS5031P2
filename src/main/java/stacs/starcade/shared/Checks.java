package stacs.starcade.shared;

import stacs.starcade.backend.impl.IPlayer;

public class Checks {
    /**
     * Checks whether given combination of three cards makes a set.
     * Three cards make a set if for all attributes (colour, shape, number and line_style) all values are:
     * - equal or
     * - different
     * @param threeCards given card objects
     * @return true if condition true.
     */
    public static boolean isSet(ICard[] threeCards) {
        boolean isSet = false;

        if (coloursValid(threeCards) && shapesValid(threeCards) && numbersValid(threeCards) && lineStylesValid(threeCards)) {
            isSet = true;
        }
        return isSet;
    }

    /**
     * Checks whether all colours of given cards equal or differ.
     * @param threeCards given three cards
     * @return true if condition true.
     */
    private static boolean coloursValid(ICard[] threeCards) {
        boolean coloursValid = false;

        // ordinal: colours need to be parsed into int values
        int[] colours = {threeCards[0].getColour().ordinal(), threeCards[1].getColour().ordinal(),
                threeCards[2].getColour().ordinal()};

        if (allExpressionsEqual(colours) || allExpressionsDifferent(colours)) {
            coloursValid = true;
        }
        return coloursValid;
    }

    /**
     * Checks whether all shapes of given cards equal or differ.
     * @param threeCards given three cards
     * @return true if condition true.
     */
    private static boolean shapesValid(ICard[] threeCards) {
        boolean shapesValid = false;

        // ordinal: shapes need to be parsed into int values
        int[] shapes = {threeCards[0].getShape().ordinal(), threeCards[1].getShape().ordinal(),
                threeCards[2].getShape().ordinal()};

        if (allExpressionsEqual(shapes) || allExpressionsDifferent(shapes)) {
            shapesValid = true;
        }
        return shapesValid;
    }

    /**
     * Checks whether all numbers of given cards equal or differ.
     * @param threeCards given three cards
     * @return true if condition true.
     */
    private static boolean numbersValid(ICard[] threeCards) {
        boolean coloursValid = false;

        // ordinal: numbers need to be parsed into int values
        int[] colours = {threeCards[0].getNumber().ordinal(), threeCards[1].getNumber().ordinal(),
                threeCards[2].getNumber().ordinal()};

        if (allExpressionsEqual(colours) || allExpressionsDifferent(colours)) {
            coloursValid = true;
        }
        return coloursValid;
    }

    /**
     * Checks whether all line_styles of given cards equal or differ.
     * @param threeCards given three cards
     * @return true if condition true.
     */
    private static boolean lineStylesValid(ICard[] threeCards) {
        boolean lineStyleValid = false;
        // ordinal: line_styles need to be parsed into int values
        int[] lineStyles = {threeCards[0].getLineStyle().ordinal(), threeCards[1].getLineStyle().ordinal(),
                threeCards[2].getLineStyle().ordinal()};
        if (allExpressionsEqual(lineStyles) || allExpressionsDifferent(lineStyles)) {
            lineStyleValid = true;
        }
        return lineStyleValid;
    }

    /**
     * Checks whether all items in a given set of three integers have the same value.
     * Integers represent the attributes colour, shape, number OR line_style.
     * @param ex represents attribute "expressions".
     * @return true if condition is true.
     */
    private static boolean allExpressionsEqual(int[] ex) {
        boolean expEqual = false;
        // Check for equality of expression values
        if (ex[0] == ex[1] && ex[1] == ex[2]) {
            expEqual = true;
        }
        return expEqual;
    }

    /**
     * Checks whether all items in a given set of three integers have different values.
     * Integers represent the attributes colour, shape, number OR line_style.
     * @param ex represents attribute "expressions".
     * @return true if condition is true.
     */
    private static boolean allExpressionsDifferent(int[] ex) {
        boolean expDifferent = false;
        // Check for equality of expression values
        if (ex[0] != ex[1] && ex[0] != ex[2] && ex[1] != ex[2]) {
            expDifferent = true;
        }
        return expDifferent;
    }
}
