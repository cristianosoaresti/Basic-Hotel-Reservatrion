package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exception.DomainException;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			Integer roomNumber = sc.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy).: ");
			Date checkin = sdf.parse(sc.next());
			sc.nextLine();
			System.out.print("Check-out date (dd/MM/yyyy): ");
			Date checkout = sdf.parse(sc.next());
			sc.nextLine();
			
			Reservation res = new Reservation(roomNumber, checkin, checkout);
			System.out.println("Reservation: " + res);
			
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy).: ");
			checkin = sdf.parse(sc.next());
			sc.nextLine();
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkout = sdf.parse(sc.next());
			sc.nextLine();
			
			res.updateDates(checkin, checkout);
			System.out.println("Reservation: " + res);
			
		} catch (DomainException e){
			System.out.println("Error in reservation: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Error in the parse...");
		} catch (InputMismatchException e) {
			System.out.println("Illegal value informed...");
		}
		sc.close();
	}

}
