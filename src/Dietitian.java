/**
 * Represents a Dietitian — a health professional who gives advice on
 * nutrition and diet management, particularly for patients with
 * diet-related conditions.
 * <p>
 * In addition to the common health professional attributes, a Dietitian
 * has a flag indicating whether they specialise in paediatric nutrition.
 */
public class Dietitian extends HealthProfessional {

    private final boolean paediatricSpecialist;

    /**
     * Constructs a Dietitian with all attributes.
     *
     * @param id                    the numeric ID (digits only)
     * @param name                  the full name
     * @param workingDays          the working days of the week
     * @param paediatricSpecialist  true if the dietitian specialises in
     *                              paediatric nutrition
     */
    public Dietitian(String id, String name, String[] workingDays,
                     boolean paediatricSpecialist) {
        super(id, name, workingDays);
        this.paediatricSpecialist = paediatricSpecialist;
    }

    /**
     * Constructs a Dietitian with a default Monday–Friday schedule.
     *
     * @param id                    the numeric ID (digits only)
     * @param name                  the full name
     * @param paediatricSpecialist  true if the dietitian specialises in
     *                              paediatric nutrition
     */
    public Dietitian(String id, String name, boolean paediatricSpecialist) {
        super(id, name);
        this.paediatricSpecialist = paediatricSpecialist;
    }

    /**
     * Returns whether this dietitian specialises in paediatric nutrition.
     *
     * @return true if paediatric specialisation applies
     */
    public boolean isPaediatricSpecialist() {
        return paediatricSpecialist;
    }

    @Override
    public String getType() {
        return "Dietitian";
    }

    @Override
    public String toString() {
        return super.toString() + " (Paediatric Nutrition Specialist: "
                + paediatricSpecialist + ")";
    }
}
