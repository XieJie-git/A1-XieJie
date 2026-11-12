/**
 * Represents a Specialist doctor — a physician who has additional training
 * in a specific field such as paediatrics, radiology, or cardiology.
 * <p>
 * In addition to the common health professional attributes, a Specialist
 * has a named area of specialty.
 */
public class Specialist extends HealthProfessional {

    private final String specialty;

    /**
     * Constructs a Specialist with all attributes.
     *
     * @param id          the numeric ID (digits only)
     * @param name        the full name
     * @param workingDays the working days of the week
     * @param specialty   the area of specialty, e.g. "Cardiology"
     */
    public Specialist(String id, String name, String[] workingDays,
                      String specialty) {
        super(id, name, workingDays);
        this.specialty = specialty;
    }

    /**
     * Constructs a Specialist with a default Monday–Friday schedule.
     *
     * @param id        the numeric ID (digits only)
     * @param name      the full name
     * @param specialty the area of specialty
     */
    public Specialist(String id, String name, String specialty) {
        super(id, name);
        this.specialty = specialty;
    }

    /**
     * Returns the specialty area.
     *
     * @return the specialty name
     */
    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getType() {
        return "Specialist (" + specialty + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " (Specialty: " + specialty + ")";
    }
}
