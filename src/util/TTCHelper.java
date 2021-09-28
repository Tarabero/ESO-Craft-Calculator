package util;


import entities.Material;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TTCHelper {

    public static void updateMaterialsWithTTCPrices(List<Material> materialsToUpdate) throws FileNotFoundException {
        String ttcPriceList = createTTCPriceListSuperString();
        for (Material material :
                materialsToUpdate) {
            int ttcID = material.getTtcId();
            int newPrice = (int) Math.round(getSuggestedPriceFromTTCFor(ttcID, ttcPriceList));
            material.setPrice(newPrice);
        }
    }

    private static String createTTCPriceListSuperString() throws FileNotFoundException {
        String programDir = System.getProperty("user.dir");
        String filePath = programDir + "/PriceTable.lua";
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        StringBuilder result = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            result.append(fileScanner.nextLine());
        }
        return result.toString();
    }

    private static double getSuggestedPriceFromTTCFor(int ttcMaterialID, String ttcPriceList) {
        StringBuilder regexGlobalSearch = new StringBuilder("\\[");
        regexGlobalSearch.append(ttcMaterialID);
        regexGlobalSearch.append("\\]=\\{\\[\\d*\\]=\\{\\[\\d*\\]=\\{\\[\\-?\\d*\\]=\\{\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,\\[\\\"Max\\\"\\]=\\d*\\.?\\d*,\\[\\\"Min\\\"\\]=\\d*\\.?\\d*,\\[\\\"EntryCount\\\"\\]=\\d*,\\[\\\"AmountCount\\\"\\]=\\d*,\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,},},}");
        String resultGlobalSearch = getSubstringFrom(ttcPriceList, regexGlobalSearch.toString());
        String regexSuggested = "\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,";
        double suggestedPrice = getPriceFrom(resultGlobalSearch, regexSuggested);
        System.out.println("Suggested price for " + ttcMaterialID + " is " + suggestedPrice + " gold");
        return suggestedPrice;
    }

    private static double getAvgPriceFromTTCFor(int ttcMaterialID, String ttcPriceList) {
        StringBuilder regexGlobalSearch = new StringBuilder("\\[");
        regexGlobalSearch.append(ttcMaterialID);
        regexGlobalSearch.append("\\]=\\{\\[\\d\\]=\\{\\[\\d\\]=\\{\\[\\-?\\d\\]=\\{\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,\\[\\\"Max\\\"\\]=\\d*,\\[\\\"Min\\\"\\]=\\d*\\.?\\d*,\\[\\\"EntryCount\\\"\\]=\\d*,\\[\\\"AmountCount\\\"\\]=\\d*,\\[\\\"SuggestedPrice\\\"\\]=\\d*\\.?\\d*,},},}");
        String resultGlobalSearch = getSubstringFrom(ttcPriceList, regexGlobalSearch.toString());
        String regexAvg = "\\[\\\"Avg\\\"\\]=\\d*\\.?\\d*,";
        double avgPrice = getPriceFrom(resultGlobalSearch, regexAvg);
        System.out.println("Average price for " + ttcMaterialID + " is " + avgPrice + " gold");
        return avgPrice;
    }

    private static String getSubstringFrom(String sourceString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceString);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            return matcher.group(0);
        }
        System.out.println("No match found with regex: " + regex);
        return null;
    }

    private static double getPriceFrom(String fullString, String regexNeededPriceLine) {
        String resultString = getSubstringFrom(fullString, regexNeededPriceLine);
        String regexNum = "\\d*\\.?\\d";
        String numInString = getSubstringFrom(resultString, regexNum);
        return Double.parseDouble(numInString);
    }
}




