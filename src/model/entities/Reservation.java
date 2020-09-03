package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {

	private int roomNumber = 0;
	private Date checkin;
	private Date checkout;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {
	}

	public Reservation(int roomNumber, Date checkin, Date checkout) throws DomainException {
		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservation dates must be future dates");
		}
		if (!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	private long duration() {
		// Get the difference between the dates in milliseconds
		long difference = checkout.getTime() - checkin.getTime();
		// convert the milliseconds into days and returns it
		return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkin, Date checkout) throws DomainException {
		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}

		if (!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}

		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room ");
		sb.append(getRoomNumber());
		sb.append(", check-in: ");
		sb.append(sdf.format(checkin));
		sb.append(", check-out: ");
		sb.append(sdf.format(checkout));
		sb.append(", ");
		sb.append(duration());
		sb.append(" nights");
		return sb.toString();
	}
}
