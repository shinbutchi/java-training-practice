package vn.smartdev.javatrainingpractice.springmvcpractice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;
import vn.smartdev.javatrainingpractice.springmvcpractice.service.IUserAttemptsService;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	IUserAttemptsService userAttemptsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			if(userAttemptsService.findByUsername(authentication.getName())!=null)
				userAttemptsService.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {

			userAttemptsService.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			String error = "";
			UserAttempts userAttempts = userAttemptsService.findByUsername(authentication.getName());
			if (userAttempts != null) {
				error = "Your account is locked, please contact your administrator";
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}

	}


}