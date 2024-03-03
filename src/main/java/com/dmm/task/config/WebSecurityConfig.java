package com.dmm.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dmm.task.data.service.AccountUserDetailsService;

@Configuration // 設定（コンフィグ）を行うクラスであると宣言
@EnableWebSecurity // SpringSecurityをアクティブにする
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccountUserDetailsService userDetailsService;

	// パスワードを暗号（ハッシュ）化する
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// オーゼンティケーションマネージャービルダー(?)【auth】に、
	// アカウントユーザーディティールサービス（実装済み）を設定する
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		super.configure(auth);
	}
	
	// 「認可の設定」と「ログインの設定」と「ログアウトの設定」をするメソッド（命名：ログイン欲張りセット）
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// 認可の設定
		http.exceptionHandling() // アクセス拒否時に案内する場所は、
		.accessDeniedPage("/accessDeniedPage") // ここ。
		.and() // それから、
		.authorizeRequests() // 以下については、認可を要求する
		.antMatchers("/loginForm") //("/loginForm")に対しては、
		.permitAll() // 誰でも入ってよし。
		.anyRequest() //そのほか【("/loginForm")以外の場所】については、
		.authenticated(); // 認可を得た者だけ入ってよし。
		// だと思う。
		
		
		// ログインの設定
		http.formLogin() // フォームを使って認証する
		.loginPage("/loginForm") // ログインフォームを表示する場所はここ【("/loginForm")】
		.loginProcessingUrl("/authenticate") // フォームの認証を処理する場所はここ【("/authenticate")】
		.usernameParameter("userName") // ユーザー名として入力された値の名前("userName")
		.passwordParameter("password") // パスワードとして入力された値の名前("password")
		.defaultSuccessUrl("/main") // 認証に成功したら案内する場所("/main")
		.failureUrl("/loginForm?error=true"); // 認証に失敗したら案内する場所("/loginForm?error=true")
		
		// ログアウトの設定
		http.logout() // ログアウト処理をする
		.logoutSuccessUrl("/loginForm") // ログアウトしたら案内する場所("/loginForm")は、
		.permitAll(); // 誰でも入ってよし。
		
	}
	
	 @Override
		public void configure(WebSecurity web) throws Exception {
			// 画像、JavaScript、cssは認可の対象外とする
			web.debug(false).ignoring().antMatchers("/images/**", "/js/**", "/css/**");
		}

}

// WebSecurityConfigurerAdapterは非推奨というか
// すでに削除されてるんだって。むぇー…( ﾟДﾟ)新しいのも覚えなきゃ…