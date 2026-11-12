/**
 * Represents a single appointment between a patient and a health
 * professional at a specific time slot on the current day.
 * <p>
 * Implements {@link Comparable} so that a collection of appointments can
 * be sorted from earliest to latest based on their time slot.
 */
public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional professional;
    private final TimeSlot timeSlot;

    /**
     * Constructs an Appointment.
     *
     * @param patient      the patient booking the appointment
     * @param professional the health professional being booked
     * @param timeSlot     the requested time slot
     * @throws IllegalArgumentException if any argument is null
     */
    public Appointment(Patient patient, HealthProfessional professional,
                        TimeSlot timeSlot) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("Professional cannot be null.");
        }
        if (timeSlot == null) {
            throw new IllegalArgumentException("Time slot cannot be null.");
        }
        this.patient = patient;
        this.professional = professional;
        this.timeSlot = timeSlot;
    }

    /**
     * Returns the patient for this appointment.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the health professional for this appointment.
     *
     * @return the professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the time slot of this appointment.
     *
     * @return the time slot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Compares this appointment to another by time slot order.
     *
     * @param other the other appointment to compare
     * @return negative, zero, or positive as this slot is earlier,
     *         equal, or later
     */
    @Override
    public int compareTo(Appointment other) {
        return this.timeSlot.compareTo(other.timeSlot);
    }

    @Override
    public String toString() {
        return "Appointment [Time: " + timeSlot
                + ", Patient: " + patient.getName()
                + " (" + patient.getMobile() + ")"
                + ", Professional: " + professional.getType()
                + " ID:" + professional.getId()
                + " " + professional.getName() + "]";
    }
}
