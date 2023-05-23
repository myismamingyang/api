import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5 {
    public static String toMD5(String StringData){
        byte[] secreByte = null;
        try{
            secreByte = MessageDigest.getInstance("md5").digest(StringData.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("!!! md5算法错误 !!!");
        }
        String md5Code = new BigInteger(1, secreByte).toString(16);
        for (int i = 0; i < 32 -md5Code.length(); i++) {
            md5Code = "0" + md5Code;
        }
        return md5Code;
    }
}
