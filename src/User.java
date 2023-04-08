import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User {

    private String name;
    private String password;
    private boolean clubMember;
    private boolean admin;

    public static String hashPassword ( String password ) {
        byte [ ] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8) ;
        byte [ ] md5Digest = new byte[0] ;
        try {
            md5Digest = MessageDigest.getInstance("MD5").digest(bytesOfPassword ) ;
        } catch (NoSuchAlgorithmException e) {
            return null ;
        }
        return Base64.getEncoder( ).encodeToString(md5Digest) ;
    }


    User(){}

    public User(String name, String password, boolean clubMember, boolean admin) {
        this.name = name;
        this.password = password;
        this.clubMember = clubMember;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isClubMember() {
        return clubMember;
    }

    public void setClubMember(boolean clubMember) {
        this.clubMember = clubMember;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
