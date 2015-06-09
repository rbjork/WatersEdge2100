package org.watersedge.relayrun.auth;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

public class ClientAndUserDetailsService implements UserDetailsService, ClientDetailsService {

	private final ClientDetailsService clients_;

	private final UserDetailsService users_;
	
	private final ClientDetailsUserDetailsService clientDetailsWrapper_;

	public ClientAndUserDetailsService(ClientDetailsService clients,
			UserDetailsService users) {
		super();
		clients_ = clients;
		users_ = users;
		clientDetailsWrapper_ = new ClientDetailsUserDetailsService(clients_);
	}

	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		return clients_.loadClientByClientId(clientId);
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = null;
		try{
			user = users_.loadUserByUsername(username);
		}catch(UsernameNotFoundException e){
			user = clientDetailsWrapper_.loadUserByUsername(username);
		}
		return user;
	}

}
