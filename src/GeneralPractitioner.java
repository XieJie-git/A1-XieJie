/**
 * Represents a General Practitioner (GP) — a family doctor who provides
 * primary health care and can refer patients to specialists.
 * <p>
 * In addition to the common health professional attributes, a GP has
 * a flag indicating whether they can prescribe medications.
 */
public class GeneralPractitioner extends HealthProfessional {

    private final boolean canPrescribe;

    /**
     * Constructs a GeneralPractitioner with all attributes.
     *
     * @param id            the numeric ID (digits only)
     * @param name          the full name
     * @param workingDays   the working days of the week
     * @param canPrescribe  true if the GP can prescribe medications
     */
    public GeneralPractitioner(String id, String name,
                               String[] workingDays, boolean canPrescribe) {
        super(id, name, workingDays);
        this.canPrescribe = canPrescribe;
    }

    /**
     * Constructs a GeneralPractitioner with default Monday–Friday schedule
     * and prescribing capability.
     *
     * @param id   the numeric ID (digits only)
     * @param name the full name
     */
    public GeneralPractitioner(String id, String name) {
        super(id, name);
        this.canPrescribe = true;
    }

    /**
     * Returns whether this GP can prescribe medications.
     *
     * @return true if prescribing is allowed
     */
    public boolean canPrescribe() {
        return canPrescribe;
    }

    @Override
    public String getType() {
        return "General Practitioner";
    }

    @Override
    public String toString() {
        return super.toString() + " (Can Prescribe: " + canPrescribe + ")";
    }
}
