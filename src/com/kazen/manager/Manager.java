//$Id$
package com.kazen.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kazen.dao.ServerConnection;
import com.kazen.model.User;
import com.kazen.util.DateUtil;

public class Manager {

	private Map<Long, User> users;
	private ServerConnection connection;

	private static Manager manager = new Manager();

	private Manager() {
		intialize();
	}

	private void setConnection(ServerConnection connection) {
		this.connection = connection;
	}

	private ServerConnection getConnection() {
		return this.connection;
	}

	public static Manager getInstance() {
		return manager;
	}

	private void intialize() {
		ServerConnection connection = ServerConnection.getInstance();
		setConnection(connection);
		Map<Long, User> users = getConnection().getUsers();
		this.users = users;

	}

	public Boolean addUser(String userName) {
		if (isUserExists(userName)) {
			return Boolean.FALSE;
		}
		User user = new User(userName);
		Boolean status = getConnection().addUser(user);
		if (status) {
			this.users.put(user.getUserId(), user);
		} else {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public List<Map<String, Object>> getUsers() {
		List<Map<String, Object>> userslist = new ArrayList<Map<String, Object>>();
		for (Entry<Long, User> entry : users.entrySet()) {
			Map<String, Object> user = new HashMap<String, Object>();
			user.put("user_id", entry.getValue().getUserId());
			user.put("user_name", entry.getValue().getUserName());
			user.put("created_time", DateUtil.getTimeAsString(entry.getValue().getUserId()));
			userslist.add(user);
		}
		return userslist;
	}
	private Boolean isUserExists(String userName) {
		for (Entry<Long, User> entry : users.entrySet()) {
			if (entry.getValue().getUserName().equalsIgnoreCase(userName)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
