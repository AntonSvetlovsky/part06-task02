package by.epam.jonline_introduction.part06.task02.bean;

public enum Result {

	SUCCESSFUL, INVALID_COMMAND, INVALID_INPUT, NO_MATCHES_FOUND, EMPTY, EXIT;

	public static boolean checkValue(String value) {

		Result[] resultArray = Result.values();

		for (Result result : resultArray) {
			if (result.toString().equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
}
