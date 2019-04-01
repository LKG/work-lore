package im.heart.core.plugins.captcha;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MapCaptchaStore implements CaptchaStore {
	Map<String,CaptchaAndLocale> store;

	public MapCaptchaStore() {
		this.store = new HashMap<String,CaptchaAndLocale>();
	}


	public boolean hasCaptcha(String id) {
		return store.containsKey(id);
	}

	public void storeCaptcha(String id, Captcha captcha)
			throws CaptchaServiceException {
		store.put(id, new CaptchaAndLocale(captcha));
	}

	public void storeCaptcha(String id, Captcha captcha, Locale locale)
			throws CaptchaServiceException {
		store.put(id, new CaptchaAndLocale(captcha, locale));
	}


	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		Object captchaAndLocale = store.get(id);
		return captchaAndLocale != null ? ((CaptchaAndLocale) captchaAndLocale)
				.getCaptcha() : null;
	}


	public Locale getLocale(String id) throws CaptchaServiceException {
		Object captchaAndLocale = store.get(id);
		return captchaAndLocale != null ? ((CaptchaAndLocale) captchaAndLocale)
				.getLocale() : null;
	}


	public boolean removeCaptcha(String id) {
		if (store.get(id) != null) {
			store.remove(id);
			return true;
		}
		return false;
	}

	/**
	 * get the size of this store
	 */
	public int getSize() {
		return this.store.size();
	}

	/**
	 * Return all the contained keys
	 */
	public Collection<String> getKeys() {
		return this.store.keySet();
	}

	/**
	 * Empty the store
	 */
	public void empty() {
		this.store =new HashMap<String,CaptchaAndLocale>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#initAndStart()
	 */
	public void initAndStart() {
		// Nothing to do with map implementations
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.captcha.service.captchastore.CaptchaStore#shutdownAndClean()
	 */
	public void cleanAndShutdown() {
		store.clear();
	}
}
