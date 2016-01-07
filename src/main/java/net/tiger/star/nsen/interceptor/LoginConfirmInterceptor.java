package net.tiger.star.nsen.interceptor;

import javax.annotation.Resource;

import net.tiger.star.nsen.dto.UserDataDto;
import net.tiger.star.nsen.service.NsenUserService;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

public class LoginConfirmInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;

    /**
     * セッションに保持されているデータ。
     */
    @Resource
    protected UserDataDto userDataDto;

    @Resource
    protected NsenUserService nsenUserService;

    /**
     * AbstractInterceptorを継承する際に、実装する必要のあるメソッド。
     * 割り込ませる処理を記述。
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 両方の条件を満たしていない場合、Loginページへ飛ばす。
        if (!isExecuteMethod(invocation) || isLoggedIn()) {
             return invocation.proceed();
        } else {
            return "/login";
        }
    }

    /**
     * 実行されたActionに@Executeがついていたかどうか。
     * @param invocation
     * @return アノテーションがついていればtrue
     */
    private boolean isExecuteMethod(MethodInvocation invocation) {
        return invocation.getMethod().isAnnotationPresent(Execute.class);
    }

    /**
     * セッション上にDtoがあるか、あった場合その中にuserIDは保持されているか。
     * アクセストークンは設定されているか
     * @return 上記の条件を両方満たしていればtrue
     */
    private boolean isLoggedIn() {
        try {
            return nsenUserService.isActiveUser(userDataDto.nsenUser.userId);
        } catch(Exception e) {
            return false;
        }
    }
}
