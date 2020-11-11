package listener;

import io.cucumber.junit.Cucumber;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class CustomCucumber extends Cucumber {
    public CustomCucumber(Class clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addFirstListener(new JUnitExecutionListener());
        super.run(notifier);
    }
}
