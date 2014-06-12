package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageBlocklist;
import com.monopond.api.wrapper.model.Blocklists;

public class BlocklistsTranslator implements
		Translator<Blocklists, ApiFaxMessageBlocklist> {

	@Override
	public ApiFaxMessageBlocklist toApi(Blocklists blocklist) {
		if (blocklist == null) {
			return null;
		}
		ApiFaxMessageBlocklist apiBlocklist =
			new ApiFaxMessageBlocklist();
		apiBlocklist.setDncr(blocklist.isDncr());
		apiBlocklist.setFps(blocklist.isFps());
		apiBlocklist.setSmartblock(blocklist.isSmartblock());
		return apiBlocklist;
	}

	@Override
	public Blocklists toWrapper(ApiFaxMessageBlocklist apiBlocklist) {
		if (apiBlocklist == null) {
			return null;
		}
		Blocklists blocklists = new Blocklists();
		blocklists.setDncr(apiBlocklist.getDncr());
		blocklists.setFps(apiBlocklist.getFps());
		blocklists.setSmartblock(apiBlocklist.getSmartblock());
		return blocklists;
	}

}
