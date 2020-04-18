package mathpar.web.learning.gateway.utils;

import mathpar.web.learning.account.utils.EncryptionUtils;
import mathpar.web.learning.account.utils.TokenUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EncryptionUtilsTest {

    @Test
    void generateStringTest() {
        int triesAmount = 20, stringLength = 20;
        List<String> generatedStrings = new ArrayList<>(triesAmount);
        for(int i=0; i<triesAmount; i++){
            String randomString = EncryptionUtils.generateString(stringLength);
            assert !generatedStrings.contains(randomString);
            assert randomString.length()==stringLength;
            generatedStrings.add(randomString);
        }
    }

    @Test
    void createHash() {
        String randomString = "RandomString", randomString2 = "RandomString2";
        assert !EncryptionUtils.createHash(randomString).equals(EncryptionUtils.createHash(randomString2));
        assert EncryptionUtils.createHash(randomString).equals(EncryptionUtils.createHash(randomString));
    }

    @Test
    void createToken() {
        assert TokenUtils.createToken(1).length()>=27;
        assert TokenUtils.createToken(2).startsWith("2_");
        assert !TokenUtils.createToken(1).equals(TokenUtils.createToken(1));
    }
}