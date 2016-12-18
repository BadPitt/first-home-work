package ru.innopolis.course3.task;

/**
 * @author Popov Danil
 *
 * Class for parsing string
 */
public class IntParser implements Parser {

    private Summator summator;
    private CounterValidator validator;
    private ThreadInterrupter interrupter;

    /**
     * @param interrupter Thread interrupter which can check
     *                    is threads interrupted or not.
     *                    And it can set interrupt-flag to true
     *                    for interrupting other threads
     * @param summator    Class for containing share sum with add-method
     * @param validator   Class which validates split strings
     */
    public IntParser(ThreadInterrupter interrupter,
                     Summator summator, CounterValidator validator) {
        this.interrupter = interrupter;
        this.summator = summator;
        this.validator = validator;
    }

    /**
     * Splits string into array and validates them
     * and then add them to share sum
     *
     * @param s Input string which contains integer values
     */
    @Override
    public void parseString(String s) {
        String[] array = s.split(" ");
        for (String str : array) {
            if (interrupter.isThreadInterrupted()) {
                return;
            }
            if (validator.validateInt(str)) {
                summator.addInt(Integer.parseInt(str));
            }
        }
    }
}
