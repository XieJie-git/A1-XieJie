import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the collection of appointments for the health service.
 * Provides operations to add, filter, sort, and cancel appointments,
 * while preventing double-booking of a health professional at the
 * same time slot.
 */
public class AppointmentManager {

    private final ArrayList<Appointment> appointments;

    /**
     * Constructs an empty AppointmentManager.
     */
    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    /**
     * Attempts to add a new appointment. The operation fails if the
     * specified professional is already booked at the same time slot.
     *
     * @param appointment the appointment to add
     * @return a success or warning message
     */
    public String addAppointment(Appointment appointment) {
        // Check for double-booking: same professional + same time slot
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(appointment.getProfessional())
                    && existing.getTimeSlot() == appointment.getTimeSlot()) {
                return "WARNING: Professional "
                        + appointment.getProfessional().getId()
                        + " is already booked at "
                        + appointment.getTimeSlot().getLabel() + ".";
            }
        }
        appointments.add(appointment);
        return "SUCCESS: Appointment booked for "
                + appointment.getPatient().getName() + " with "
                + appointment.getProfessional().getName()
                + " at " + appointment.getTimeSlot().getLabel() + ".";
    }

    /**
     * Returns all appointments sorted by time.
     *
     * @return a sorted list of all appointments
     */
    public List<Appointment> getAllSorted() {
        ArrayList<Appointment> sorted = new ArrayList<>(appointments);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Returns all appointments (unsorted).
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

    /**
     * Filters appointments by the health professional's ID.
     *
     * @param professionalId the professional ID to match
     * @return a list of matching appointments, sorted by time
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getProfessional().getId().equals(professionalId)) {
                result.add(a);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Filters appointments by the patient's mobile phone number.
     *
     * @param mobile the patient mobile number to match
     * @return a list of matching appointments, sorted by time
     */
    public List<Appointment> filterByPatientMobile(String mobile) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().getMobile().equals(mobile)) {
                result.add(a);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Cancels an appointment matching the professional ID and time slot.
     *
     * @param professionalId the professional ID
     * @param timeSlot       the time slot
     * @return a success or warning message
     */
    public String cancelByProfessionalAndTime(String professionalId,
                                               TimeSlot timeSlot) {
        for (Appointment a : appointments) {
            if (a.getProfessional().getId().equals(professionalId)
                    && a.getTimeSlot() == timeSlot) {
                appointments.remove(a);
                return "SUCCESS: Appointment cancelled for professional ID "
                        + professionalId + " at " + timeSlot.getLabel() + ".";
            }
        }
        return "WARNING: No appointment found for professional ID "
                + professionalId + " at " + timeSlot.getLabel() + ".";
    }

    /**
     * Cancels all appointments for a given patient mobile number.
     *
     * @param mobile the patient mobile number
     * @return a success or warning message
     */
    public String cancelByPatientMobile(String mobile) {
        ArrayList<Appointment> toRemove = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().getMobile().equals(mobile)) {
                toRemove.add(a);
            }
        }
        if (toRemove.isEmpty()) {
            return "WARNING: No appointment found for patient mobile "
                    + mobile + ".";
        }
        appointments.removeAll(toRemove);
        return "SUCCESS: Cancelled " + toRemove.size()
                + " appointment(s) for patient mobile " + mobile + ".";
    }

    /**
     * Returns the number of current appointments.
     *
     * @return the count
     */
    public int count() {
        return appointments.size();
    }
}
