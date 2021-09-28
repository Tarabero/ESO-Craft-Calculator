package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TTCParser {

    private static final String REGEX_MATERIAL = "\\[%1$d\\]=\\{\\[\\d*\\]=\\{\\[\\d*\\]=\\{\\[\\-?\\d*\\]=\\{\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,\\[\\\"Max\\\"\\]=\\d*\\.?\\d*,\\[\\\"Min\\\"\\]=\\d*\\.?\\d*,\\[\\\"EntryCount\\\"\\]=\\d*,\\[\\\"AmountCount\\\"\\]=\\d*,\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,},},}";
    private static final String REGEX_SUGGESTED_PRICE = "\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,";
    private static final String REGEX_AVERAGE_PRICE = "\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,";
    private static final String REGEX_NUMBER = "\\d*\\.?\\d";

    private final String ttcPriceList;

    public TTCParser(String ttcPriceListFilePath) throws FileNotFoundException {
        ttcPriceList = createTTCPriceListSuperString(ttcPriceListFilePath);
    }

    public boolean chosenFileIsTTCPriceTable() {
        Pattern pattern = Pattern.compile("function TamrielTradeCentrePrice:LoadPriceTable\\(\\)");
        Matcher matcher = pattern.matcher(ttcPriceList);
        return matcher.find();
    }

    private static String createTTCPriceListSuperString(String ttcPriceListFilePath) throws FileNotFoundException {
        File file = new File(ttcPriceListFilePath);
        Scanner fileScanner = new Scanner(file);
        StringBuilder result = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            result.append(fileScanner.nextLine());
        }
        return result.toString();
    }

    public double getSuggestedPriceFromTTCFor(int ttcMaterialID) {
        String resultGlobalSearch = getSubstringFrom(ttcPriceList, String.format(REGEX_MATERIAL, ttcMaterialID));
        if (resultGlobalSearch == null) {
            return -1;
        }
        return getPriceFrom(resultGlobalSearch, REGEX_SUGGESTED_PRICE);
    }

    private double getAvgPriceFromTTCFor(int ttcMaterialID) {
        String resultGlobalSearch = getSubstringFrom(ttcPriceList, String.format(REGEX_MATERIAL, ttcMaterialID));
        double avgPrice = getPriceFrom(resultGlobalSearch, REGEX_AVERAGE_PRICE);
        System.out.println("Average price for " + ttcMaterialID + " is " + avgPrice + " gold");
        return avgPrice;
    }

    private String getSubstringFrom(String sourceString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceString);
        if (matcher.find()) {
            return matcher.group(0);
        }
        System.out.println("No match found with regex: " + regex);
        return null;
    }

    private double getPriceFrom(String fullString, String regexNeededPriceLine) {
        String resultString = getSubstringFrom(fullString, regexNeededPriceLine);
        String numInString = getSubstringFrom(resultString, REGEX_NUMBER);
        return Double.parseDouble(numInString);
    }
}




