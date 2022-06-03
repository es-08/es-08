package jp.ac.hcs.s3a216.zipcode;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 郵便番号検索機能を表す。
 * 
 * <p>本機能は、ユーザから入力された郵便番号から
 * 合致する住所、住所カナを表示します。
 * 
 * <p>入力チェックを実施し、正しい郵便番号のみ処理します。
 * クライアント側でも入力チェックを実施することを推奨します。
 * 
 * <p>利用には<code>getZipCode</code>をご覧ください。
 * 
 * @author 情報太郎
 *
 */
@Controller
public class ZipCodeController {
	
	@Autowired
	private ZipCodeService zipCodeService;
/**
 * 郵便番号から住所を検索し、結果画面を表示します。
 * 
 * <p>本機能は、郵便番号検索APIを内部で呼び出して結果を表示します。<br>
 * 仕様については、下記のドキュメントを参照してください。
 * <p>https://zipcloud.ibsnet.co.jp/doc/api
 * 
 * @param zipcode 郵便番号の文字列(7桁)を格納(null不可)
 * @param principal ログイン中のユーザ情報を格納(null不可)
 * @param model Viewに値を渡すオブジェクト(null不可)
 * @return 郵便番号結果画面へのパス(nul不可l)
 */
	
	@PostMapping("/zip")
	public String getZipCode(@RequestParam("zipcode")String zipcode,
			Principal principal,Model model){
		
		ZipCodeEntity zipCodeEntity = zipCodeService.execute(zipcode);
		model.addAttribute("results", zipCodeEntity);
		
		return "zipcode/result";
	}
}
