package riccardo.BACKEND.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceLocator {
    @Autowired
    private ApplicationContext applicationContext;

    // Returns a service of the specified type.
    public <T> T getService(Class<T> serviceType) {
        return applicationContext.getBean(serviceType);
    }
}
