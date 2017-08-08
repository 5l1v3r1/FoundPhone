<?php

	function login($username, $password) {
		global $config;
		$username = _hash($username.$config['account']['salt']);
		$password = _hash($password.$config['account']['salt']);
		$userHash = _hash($config['account']['user'].$config['account']['salt']);
		$passHash = _hash($config['account']['pass'].$config['account']['salt']);
		if($username==$userHash&&$password==$passHash) {
			_setcookie("username", $userHash, $config['account']['time']);
			_setcookie("password", $passHash, $config['account']['time']);
			return true;
		}
		return false;
	}
	
	function logout() {
		_deletecookie("username");
		_deletecookie("password");
	}

	function isLoggedIn() {
		global $config;
		$userHash = _hash($config['account']['user'].$config['account']['salt']);
		$passHash = _hash($config['account']['pass'].$config['account']['salt']);
		$userHashCookie = _cookie("username");
		$passHashCookie = _cookie("password");
		if($userHash==$userHashCookie&&$passHash==$passHashCookie) {
			return true;
		}
		return false;
	}
	
	function generateKey() {
		global $config;
		$letters = "abcdefghijklmnopqrstuvwxyz0123456789";
		$code = "";
		for($i=0; $i<strlen($config['key']['shema']); $i++) {
			switch($config['key']['shema'][$i]) {
				case "D":
					$code.= $letters[rand(0, strlen($letters)-1)];
					break;
				default:
					$code.=$config['key']['shema'][$i];
			}
		}
		return strtoupper($code);
	}
	
	function getUsers() {
		$result = mysql_query("SELECT * FROM `keys` WHERE NOT hwid='' ORDER BY created DESC");
		echo mysql_error();
		$return = array("id", "hwid", "registered");
		$arr = array();
		for($i=0; $i<mysql_num_rows($result); $i++) {
			foreach($return as $k=>$v) {
				$arr[$i][$v] = mysql_result($result, $i, $v);
			}
		}
		return $arr;
	}
	
	function getKeys() {
		$result = mysql_query("SELECT * FROM `keys` WHERE hwid='' ORDER BY created DESC");
		echo mysql_error();
		$return = array("id", "key", "created");
		$arr = array();
		for($i=0; $i<mysql_num_rows($result); $i++) {
			foreach($return as $k=>$v) {
				$arr[$i][$v] = mysql_result($result, $i, $v);
			}
		}
		return $arr;
	}

	function addKey() {
		mysql_query("INSERT INTO `keys` (`key`) VALUES ('".generateKey()."')");
	}
	
	function deleteKey($id) {
		mysql_query("DELETE FROM `keys` WHERE id = ".$id);
	}

	function resetKey($id) {
		mysql_query("UPDATE `keys` SET hwid = '' WHERE id = ".$id);
	}

	/* API */
	function canUse($hwid) {
		if(!$hwid) { return false; }
		$result = mysql_query("SELECT * FROM `keys` WHERE `hwid` = '".$hwid."'");
		return mysql_num_rows($result)>0;
	}
	
	function isFree($key) {
		if(!$key) { return false; }
		$result = mysql_query("SELECT * FROM `keys` WHERE `key` = '".$key."' AND `hwid` = ''");
		return mysql_num_rows($result)>0;
	}
	
	function isKey($key) {
		$result = mysql_query("SELECT * FROM `keys` WHERE `key` = '".$key."'");
		return mysql_num_rows($result)>0;
	}
	
	function registerKey($key, $hwid) {
		if(!$hwid) { return "error: wrong hwid"; }
		if(!$key) { return "error: wrong key"; }
		if(!isKey($key)) { return "error: wrong key"; }
		if(!isFree($key)) { return "error: key is already in use"; }
		mysql_query("UPDATE `keys` SET `hwid` = '".$hwid."', `registered` = '".date("Y-m-d H:i:s", time())."' WHERE `key` = '".$key."'");
		return canUse($hwid);
	}

?>