package mathpar.web.learning.account.controllers.api;

import io.swagger.annotations.Api;
import mathpar.web.learning.account.services.RestorationService;
import mathpar.web.learning.account.utils.PublicApi;
import mathpar.web.learning.account.utils.dto.payloads.ChangePasswordPayload;
import mathpar.web.learning.account.utils.dto.payloads.RestoreCredentialsPayload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static mathpar.web.learning.account.utils.AuthenticationUrls.CHANGE_PASSWORD_URL;
import static mathpar.web.learning.account.utils.AuthenticationUrls.RESTORE_CREDENTIALS_URL;

@PublicApi
@RestController
@Api(tags = "Restoration")
public class RestorationController {
    private final RestorationService restorationService;

    public RestorationController(RestorationService restorationService) {
        this.restorationService = restorationService;
    }

    @PostMapping(RESTORE_CREDENTIALS_URL)
    public void startRestoreCredentials(@RequestBody RestoreCredentialsPayload payload){
        restorationService.createPasswordRestorationRequest(payload.getEmail());
    }

    @PostMapping(CHANGE_PASSWORD_URL)
    public void restoreCredentials(@RequestBody ChangePasswordPayload payload){
        restorationService.restoreCredentials(payload.getToken(), payload.getNewPassword());
    }
}
