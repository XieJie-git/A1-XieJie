import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a generic health professional working at the health service.
 * This abstract class captures the information and behaviour common to
 * every type of health professional regardless of their specialty.
 * <p>
 * Common attributes include:
 * <ul>
 *   <li>A numeric ID (digits only) for identification</li>
 *   <li>A name</li>
 *   <li>A fixed set of working days each week</li>
 * </ul>
 * <p>
 * The class is abstract because a "plain" health professional with no
 * specialty should never be instantiated directly; only specific
 * subclasses (e.g. GeneralPractitioner, Specialist) can be created.
 */
public abstract class HealthProfessional {

    private final String id;
    private final String name;
    private final String[] workingDays;

    /**
     * Constructs a HealthProfessional with all common attributes.
     *
     * @param id          the numeric ID of the professional (digits only)
     * @param name        the full name of the professional
     * @param workingDays the days of the week the professional works
     * @throws IllegalArgumentException if id is null, empty, or contains
     *                                  non-digit characters
     */
    public HealthProfessional(String id, String name, String[] workingDays) {
        if (id == null || id.isBlank() || !id.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Professional ID must contain digits only.");
        }
        this.id = id;
        this.name = name;
        this.workingDays = workingDays != null ? workingDays : new String[0];
    }

    /**
     * Constructs a HealthProfessional with a default working schedule of
     * Monday to Friday.
     *
     * @param id   the numeric ID of the professional (digits only)
     * @param name the full name of the professional
     */
    public HealthProfessional(String id, String name) {
        this(id, name, new String[]{"Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday"});
    }

    /**
     * Returns the numeric ID of this professional.
     *
     * @return the ID string
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this professional.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working days array to protect internal state.
     *
     * @return a new array containing the working days
     */
    public String[] getWorkingDays() {
        return Arrays.copyOf(workingDays, workingDays.length);
    }

    /**
     * Returns a short description of the professional's type.
     * Subclasses must override this to identify their specialty.
     *
     * @return the type description, e.g. "General Practitioner"
     */
    public abstract String getType();

    /**
     * Returns a string representation of this health professional,
     * including type, ID, name, and working days.
     *
     * @return a readable string with all details
     */
    @Override
    public String toString() {
        return getType() + " [ID: " + id + ", Name: " + name
                + ", Working Days: " + Arrays.toString(workingDays) + "]";
    }

    /**
     * Two health professionals are considered equal if they have the same ID.
     *
     * @param o the object to compare
     * @return true if the IDs match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthProfessional)) return false;
        HealthProfessional that = (HealthProfessional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
