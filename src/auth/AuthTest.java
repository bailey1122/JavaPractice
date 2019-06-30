package auth;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

//this is an example of how authentication works
public class AuthTest {
    public static void main(String[] args) throws LoginException {
        System.setProperty("java.security.policy", "src/auth/AuthTest.policy"); //path to file with policy
        System.setProperty("java.security.auth.login.config", "src/auth/jaas.config"); //path to file with config

        System.setSecurityManager(new SecurityManager());
        try {



            LoginContext context = new LoginContext("Login1");
            context.login();
            Subject subject = context.getSubject();
            System.out.println("Authentication successful.");
            System.out.println("subject=" + subject);
            PrivilegedAction<String> action = new SysPropAction("java.version"); //if you not want SysProp... you should turn off this
            String result = Subject.doAsPrivileged(subject, action, null); //and this turn off
            System.out.println(result);
//           if you don't want to use SysPropAction.java you can to use code below
//           Subject.doAsPrivileged(subject, new PrivilegedAction<Object>() {
//                @Override
//                public Object run() {
//                    System.out.println(System.getProperty("java.version"));
//                    return null;
//                }
//            }, null);
            context.logout();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
