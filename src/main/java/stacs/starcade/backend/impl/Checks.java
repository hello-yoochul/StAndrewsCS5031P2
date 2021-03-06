package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IPlayer;

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
        int [] colours = {threeCards[0].getColour(), threeCards[1].getColour(), threeCards[2].getColour()};
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
        int [] shapes = {threeCards[0].getShape(), threeCards[1].getShape(), threeCards[2].getShape()};
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
        int [] colours = {threeCards[0].getNumber(), threeCards[1].getNumber(), threeCards[2].getNumber()};
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
        boolean coloursValid = false;
        int [] colours = {threeCards[0].getLineStyle(), threeCards[1].getLineStyle(), threeCards[2].getLineStyle()};
        if (allExpressionsEqual(colours) || allExpressionsDifferent(colours)) {
            coloursValid = true;
        }
        return coloursValid;
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

    /**
     * Checks whether three given cards have the same owner
     * @return true if condition true
     */
    public static boolean isSameOwner(ICard[] threeCards) {
        boolean isSameOwner = false;
        IPlayer ownerC0 = threeCards[0].getOwner();
        IPlayer ownerC1 = threeCards[1].getOwner();
        IPlayer ownerC2 = threeCards[2].getOwner();
        if (ownerC0.equals(ownerC1) && ownerC1.equals(ownerC2)) {
            isSameOwner = true;
        }
        return isSameOwner;
    }
}
