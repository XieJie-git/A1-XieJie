import java.util.Objects;

/**
 * Represents a patient with basic contact details needed for booking
 * an appointment. A patient is identified by name and mobile number.
 */
public class Patient {

    private final String name;
    private final String mobile;

    /**
     * Constructs a Patient with name and mobile number.
     *
     * @param name   the patient's full name
     * @param mobile the patient's mobile phone number
     * @throws IllegalArgumentException if name or mobile is null/blank
     */
    public Patient(String name, String mobile) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be blank.");
        }
        if (mobile == null || mobile.isBlank()) {
            throw new IllegalArgumentException(
                    "Patient mobile number cannot be blank.");
        }
        this.name = name;
        this.mobile = mobile;
    }

    /**
     * Returns the patient's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return the mobile number
     */
    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "Patient{Name: " + name + ", Mobile: " + mobile + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(mobile, patient.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobile);
    }
}
