package ru.innopolis.course3.task;

/**
 * Created by Danil Popov course-3.
 */
public class IntParser implements Parser {

    private Summator summator;
    private CounterValidator validator;
    private ThreadInterrupter interrupter;

    public IntParser(ThreadInterrupter interrupter,
                     Summator summator, CounterValidator validator) {
        this.interrupter = interrupter;
        this.summator = summator;
        this.validator = validator;
    }

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
