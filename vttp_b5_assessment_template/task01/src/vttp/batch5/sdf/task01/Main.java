package vttp.batch5.sdf.task01;

// Use this class as the entry point of your program
import java.io.*;
import java.util.*;

import vttp.batch5.sdf.task01.models.BikeEntry;
import vttp.batch5.sdf.task01.models.Information;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] position = { "highest", "second highest", "third highest", "fourth highest", "fifth highest" };
		// System.out.printf("hello, world\n");
		BikeEntry be = new BikeEntry();
		String[] columns = null;
		List<Information> cyclist = new ArrayList<Information>();

		try (BufferedReader br = new BufferedReader(new FileReader("day.csv"))) {
			String headers = br.readLine();
			String line;
			// System.out.println(headers);
			while ((line = br.readLine()) != null) {
				columns = parseLine(line);
				be = BikeEntry.toBikeEntry(columns);
				int total = be.getCasual() + be.getRegistered();
				String season = Utilities.toSeason(be.getSeason());
				String weekday = "";
				if(be.getWeekday() == 0)
					weekday = Utilities.toWeekday(1);
				else
					weekday = Utilities.toWeekday(be.getWeekday());
				String month = Utilities.toMonth(be.getMonth());
				String weather = toWeather(be.getWeather());
				cyclist.add(new Information(total, month, season, weekday, weather, be.isHoliday()));
			}

			Comparator<Information> compare = Comparator.comparing(Information::getTotal);
			cyclist.sort(compare.reversed());
		}

		for (int i = 0; i < 5; i++) {
			if (cyclist.get(i).isHoliday())
				System.out.println("\nThe " + position[i] + " (position) recorded number of cyclists was in "
						+ cyclist.get(i).getSeason() + " (season), on a " + cyclist.get(i).getDay()
						+ "(day) in the month of " + cyclist.get(i).getMonth() + " (month). There were a total of "
						+ cyclist.get(i).getTotal() + " (total) cyclist. The weather was " + cyclist.get(i).getWeather()
						+ " (weather)." + cyclist.get(i).getDay() + " (day) was a holiday.");

			else if (!cyclist.get(i).isHoliday())
				System.out.println("\nThe " + position[i] + " (position) recorded number of cyclists was in "
						+ cyclist.get(i).getSeason() + " (season), on a " + cyclist.get(i).getDay()
						+ "(day) in the month of " + cyclist.get(i).getMonth() + " (month). There were a total of "
						+ cyclist.get(i).getTotal() + " (total) cyclist. The weather was " + cyclist.get(i).getWeather()
						+ " (weather)." + cyclist.get(i).getDay() + " (day) was not a holiday.");
		}

	}

	public static final String[] WEATHER = { "Clear, Few clouds, Partly cloudy, Partly cloudy",
			"Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
			"Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
			"Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog" };

	public static String toWeather(int weather) {
		switch (weather) {
			case 1:
			case 2:
			case 3:
			case 4:
				return WEATHER[weather - 1];
			default:
				return "incorrect weather";
		}
	}

	// This method manually parses a CSV line, handling quoted fields correctly
	public static String[] parseLine(String line) {
		List<String> result = new ArrayList<>();
		StringBuilder currentField = new StringBuilder();
		boolean insideQuotes = false;

		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);

			if (currentChar == '\"') {
				// Toggle insideQuotes when encountering a quote character
				insideQuotes = !insideQuotes;
			} else if (currentChar == ',' && !insideQuotes) {
				// If we encounter a comma outside of quotes, it's the end of a field
				result.add(currentField.toString().trim());
				currentField.setLength(0); // Reset the field buffer
			} else {
				// Add the character to the current field
				currentField.append(currentChar);
			}
		}

		// Add the last field to the result (since the line might not end with a comma)
		result.add(currentField.toString().trim());

		return result.toArray(new String[0]);
	}
}