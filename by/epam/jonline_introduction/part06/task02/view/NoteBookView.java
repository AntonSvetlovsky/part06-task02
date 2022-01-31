package by.epam.jonline_introduction.part06.task02.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import by.epam.jonline_introduction.part06.task02.bean.Result;
import by.epam.jonline_introduction.part06.task02.controller.ControllerProvider;
import by.epam.jonline_introduction.part06.task02.controller.NoteBookController;

public class NoteBookView {

	private static final ControllerProvider provider = ControllerProvider.getInstance();
	private final NoteBookController controller = provider.getController();
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void show() {

		try (reader) {
			printMessage("Welcome to The Note Book!!!" + "\n");
			boolean flag = true;
			while (flag) {
				printMessage("-".repeat(48) + "\n" + " ".repeat(15) + "Available Options" + " ".repeat(16) + "\n"
						+ "-".repeat(48) + "\n" + "1.  Add New Note." + "\n"
						+ "    Enter subject, email address, message after the number of command separated by comma."
						+ "\n" + "    Input example : 1,subject,email,message ." + "\n"

						+ "2.  Remove Note." + "\n" + "    Enter note id after the number of command dividing by comma."
						+ "\n" + "    Input example : 2,id ." + "\n"

						+ "3.  Show All Notes." + "\n"

						+ "4.  Find Notes By Date." + "\n"
						+ "    Enter the requested date after the number of command separated by comma." + "\n"
						+ "    Input example : 2,YYYY-MM-DD ." + "\n"

						+ "5.  Find Notes By Subject." + "\n"
						+ "    Enter the requested subject after the number of command separated by comma." + "\n"
						+ "    Input example : 5,subject ." + "\n"

						+ "6.  Find Notes By Email Address." + "\n"
						+ "    Enter the requested email address after the number of command separated by comma." + "\n"
						+ "    Input example : 6,email ." + "\n"

						+ "7.  Find Latest Notes By Subject After An Entered Date." + "\n"
						+ "    Enter the requested subject and date after the number of command separated by comma."
						+ "\n" + "    Input example : 7,subject,YYYY-MM-DD ." + "\n"

						+ "8.  Find Notes By Word In Message." + "\n"
						+ "    Enter the requested word after the number of command separated by comma." + "\n"
						+ "    Input example : 8,word ." + "\n"

						+ "9.  Exit." + "\n" + "-".repeat(48) + "\n" + "Enter Your Choice:" + "\n");

				printMessage(">>");
				String request = reader.readLine();

				String response = controller.doAction(request);
				String[] result = response.split("\n", 2);

				if (Result.checkValue(result[0])) {
					Result key = Result.valueOf(result[0]);
					printMessage(key.toString());
					if (key.equals(Result.EXIT)) {
						flag = false;
					}
					if (result.length > 1) {
						printNote(result[1]);
					}
				} else {
					printMessage("UNEXPECTED ERROR");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	private void printNote(String result) {

		String[] noteArray = result.split("\n");

		for (String note : noteArray) {
			String[] noteData = note.split("\\|");
			System.out.println("Note ID: " + noteData[0]);
			System.out.println("Note Subject: " + noteData[1]);
			System.out.println("Note Date: " + noteData[2]);
			System.out.println("Note Email: " + noteData[3]);
			System.out.println("Note Message: " + noteData[4]);
			System.out.println();
		}
	}

}
