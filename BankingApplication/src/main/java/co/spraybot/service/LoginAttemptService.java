package co.spraybot.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {
	private final Integer MAX_ATTEMPTS = 10;
	private LoadingCache<String, Integer> loginAttemptCache;
	
	public LoginAttemptService() {
		super();
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>(){
			@Override
			public Integer load(String key) { // key is the ip address of client(login attemptee)
				return 0;
			}
		});	
	}
	
	public void loginSucceeded(String key) {
		loginAttemptCache.invalidate(key); // remove key count because login succeeded 
	}
	
	public void loginFailed(String key) {
		int attempts = 0;
		try {
			attempts = loginAttemptCache.get(key);
		}catch(ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		loginAttemptCache.put(key, attempts); // update the current # of attempts taken 
		
	}
	
	public boolean isBlocked(String key) {
		try {
			return loginAttemptCache.get(key) >= MAX_ATTEMPTS; // if # of attempt taken > max allowed => return false, else return true 
		}catch(ExecutionException e) {
			return false;
		}
	}
	
	
		
}
