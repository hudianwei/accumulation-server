import com.accumulation.controller.authentication.GitHubAuthentication;
import com.accumulation.controller.authentication.MyAuthenticationToken;
import org.junit.Test;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/29 16:53
 */
public class getGithubTokenTest {
    @Test
    public void getGithubToken() {
        GitHubAuthentication gitHubAuthentication=new GitHubAuthentication();
        gitHubAuthentication.getUserId("f6e782eb20a6b18c25fe");
    }
}
