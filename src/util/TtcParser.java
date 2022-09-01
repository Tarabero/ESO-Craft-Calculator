package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TtcParser {
    public static final int PARSE_ERROR = -1;
    private static final String REGEX_MATERIAL = "\\[%1$d\\]=\\{\\[\\d*\\]=\\{\\[\\d*\\]=\\{\\[\\-?\\d*\\]=\\{\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,\\[\\\"Max\\\"\\]=\\d*\\.?\\d*,\\[\\\"Min\\\"\\]=\\d*\\.?\\d*,\\[\\\"EntryCount\\\"\\]=\\d*,\\[\\\"AmountCount\\\"\\]=\\d*,\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,},},}";
    private static final String REGEX_SUGGESTED_PRICE = "\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,";
    private static final String REGEX_AVERAGE_PRICE = "\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,";
    private static final String REGEX_NUMBER = "\\d*\\.?\\d";
    private static final String REGEX_TTC_PRICE_TABLE_MARKER = "function TamrielTradeCentrePrice:LoadPriceTable";


    private final String ttcPriceList;

    public TtcParser(String ttcPriceListFilePath) throws FileNotFoundException {
        ttcPriceList = createTtcPriceListSuperString(ttcPriceListFilePath);
    }

    public boolean isTtcPriceTable() {
        Pattern pattern = Pattern.compile(REGEX_TTC_PRICE_TABLE_MARKER);
        Matcher matcher = pattern.matcher(ttcPriceList);
        return matcher.find();
    }

    private static String createTtcPriceListSuperString(String ttcPriceListFilePath) throws FileNotFoundException {
        File file = new File(ttcPriceListFilePath);
        Scanner fileScanner = new Scanner(file);
        StringBuilder result = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            result.append(fileScanner.nextLine());
        }
        return result.toString();
    }

    public double getSuggestedPriceFromTtcFor(int ttcMaterialID) {
        String resultGlobalSearch = getSubstringFrom(ttcPriceList, String.format(REGEX_MATERIAL, ttcMaterialID));
        if (resultGlobalSearch == null) {
            return PARSE_ERROR;
        }
        return getPriceFrom(resultGlobalSearch, REGEX_SUGGESTED_PRICE);
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
        if (numInString == null) {
            return PARSE_ERROR;
        }
        return Double.parseDouble(numInString);
    }
}




