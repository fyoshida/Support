package model;

public class RepositorySample {
	 
    /**
     * 長さを保存する。
     * 今回はダミー実装で標準出力に出力するだけ。
     * @param length
     */
    public int saveLength(String target) {
        // 本当はDBアクセスとかをする
        System.out.println("saveLength");
        return saveLengthImpl(target);
    }
 
    /**
     * saveLengthの実装。Spyを試すために無意味な形で実装した。
     * @param target
     * @return
     */
    public int saveLengthImpl(String target) {
        // 本当はDBアクセスとかをする
        var length = target.length();
        System.out.println("saveLengthImpl");
        return length;
    }
 
}
