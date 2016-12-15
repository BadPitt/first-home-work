package ru.innopolis.course3.task;

/**
 * Created by Danil Popov course-3.
 */
public class CounterValidatorImpl implements CounterValidator {
    @Override
    public boolean validateInt(int i) {
        return i > 0 && i % 2 == 0;
    }
}
