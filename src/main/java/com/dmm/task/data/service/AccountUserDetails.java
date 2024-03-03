package com.dmm.task.data.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.dmm.task.data.entity.Users;

public class AccountUserDetails implements UserDetails {

	private Users user;

	public AccountUserDetails(Users user) {
		this.user = user;
	}

	// 【ログインを要求した誰か（以下「そいつ」）】の権限リストのゲッター
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + user.getRoleName());
	}

	// 【そいつ】のパスワードのゲッター
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 【そいつ】のユーザー名のゲッター
	@Override
	public String getUsername() {
		return user.getUserName();
	}

	// 【そいつ】のアカウントの有効期限を、
	@Override
	public boolean isAccountNonExpired() {
		return true; //判定（チェック）する
	}

	// 【そいつ】のアカウントはロックされているかどうかを、
	@Override
	public boolean isAccountNonLocked() {
		return true; // 判定する
	}

	// 【そいつ】の資格情報の有効期限を、
	@Override
	public boolean isCredentialsNonExpired() {
		return true; //判定する
	}

	// 【そいつ】は実在するかを、
	@Override
	public boolean isEnabled() {
		return true;//判定する
	}

	// 【そいつ】のエンティティのゲッター
	public Users getUser() {
		return user;
	}

	// 【そいつ】自身の名前のゲッター
	public String getName() {
		return user.getName();
	}

}
